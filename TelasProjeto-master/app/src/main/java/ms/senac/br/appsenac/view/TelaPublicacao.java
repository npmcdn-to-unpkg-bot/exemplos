package ms.senac.br.appsenac.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

import ms.senac.br.appsenac.R;
import ms.senac.br.appsenac.activity.BaseActivity;
import ms.senac.br.appsenac.model.Post;
import ms.senac.br.appsenac.model.Usuario;
import ms.senac.br.appsenac.service.PostService;
import ms.senac.br.appsenac.utils.AlertUtils;
import ms.senac.br.appsenac.utils.ImageResizeUtils;
import ms.senac.br.appsenac.utils.Prefs;
import ms.senac.br.appsenac.utils.SDCardUtils;

public class TelaPublicacao extends BaseActivity {
    private static final int SELECT_PICTURE = 1;

    // Caminho para pegar o arquivo da foto
    private String caminhoDaImagemSelecionada;
    // Caminho para salvar o arquivo da foto
    private File file;

    private EditText txtTitulo, txtTexto;
    private ImageView imgView;
    private Post post = new Post();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_publicacao);
        setUpToolbar();
        setUpNavigationView();

        if (savedInstanceState != null) {
            // Se girou a tela recupera o estado
            file = (File) savedInstanceState.getSerializable("file");
            showImage(file);
        }

        txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        txtTexto = (EditText) findViewById(R.id.txtTexto);
        imgView = (ImageView) findViewById(R.id.imagem);

        ImageButton btAbrirGaleria  = (ImageButton) findViewById(R.id.btAbrirGaleria);
        btAbrirGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }
        });

        ImageButton btAbrirCamera = (ImageButton) findViewById(R.id.btAbrirCamera);
        btAbrirCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria o caminho do arquivo no sdcard
                file = SDCardUtils.getPrivateFile(getBaseContext(), "foto.jpg", Environment.DIRECTORY_PICTURES);
                // Chama a intent informando o arquivo para salvar a foto
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(i, 0);
            }
        });
    }

    public void inserirPost(View view) {
        int usuarioId = Prefs.getInteger(getApplicationContext(), "usuarioId");
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(usuarioId);

        post.setTexto(txtTexto.getText().toString());
        post.setTitulo(txtTitulo.getText().toString());
        post.setUsuario(usuario);

        new Thread(new Runnable() {
            @Override
            public void run() {
                PostService service = new PostService();
                try {
                    service.inserirPost(post);
                    finish();
                }catch (Exception e){
                    Log.e("AAA", e.getMessage(), e);
                    AlertUtils.alertSupport(getAppCompatActivity(), "Alerta", "Aconteceu um erro ao tentar inserir um post!", null);

                }
            }
        }).start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Salvar o estado caso gire a tela
        outState.putSerializable("file", file);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Imagem da Camera
        if (resultCode == RESULT_OK && file != null) {
            showImage(file);
        }
        // Imagem da Galeria
        if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE) {
            Uri selectedImageUri = data.getData();
            caminhoDaImagemSelecionada = getPath(selectedImageUri);
            showImage(new File(caminhoDaImagemSelecionada));
        }
    }

    // Atualiza a imagem na tela
    private void showImage(File file) {
        if (file != null && file.exists()) {
            Log.d("foto", file.getAbsolutePath());

            int w = imgView.getWidth();
            int h = imgView.getHeight();
            Bitmap bitmap = ImageResizeUtils.getResizedImage(Uri.fromFile(file), 0, 0, false);

            imgView.setImageBitmap(bitmap);

            post.setImagem(encodeTobase64(bitmap));
        }
    }


    // auxiliar para saber o caminho de uma imagem URI
    public String getPath(Uri uri) {
        if( uri == null ) {
            AlertUtils.alertSupport(getAppCompatActivity(), "Alerta", "Nenhuma foto selecionada!", null);
            return null;
        }

        // Tenta recuperar a imagem da media store primeiro
        // Isto só irá funcionar para as imagens selecionadas da galeria
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }
}



