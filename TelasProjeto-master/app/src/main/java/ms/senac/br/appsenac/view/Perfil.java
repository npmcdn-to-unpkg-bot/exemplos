package ms.senac.br.appsenac.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import ms.senac.br.appsenac.R;
import ms.senac.br.appsenac.activity.BaseActivity;
import ms.senac.br.appsenac.model.PostComentario;
import ms.senac.br.appsenac.model.Usuario;
import ms.senac.br.appsenac.service.PostService;
import ms.senac.br.appsenac.service.UsuarioService;
import ms.senac.br.appsenac.utils.AlertUtils;
import ms.senac.br.appsenac.utils.ImageResizeUtils;
import ms.senac.br.appsenac.utils.Prefs;
import ms.senac.br.appsenac.utils.SDCardUtils;

public class Perfil extends BaseActivity {
    private static final int SELECT_PICTURE = 1;

    // Caminho para pegar o arquivo da foto
    private String caminhoDaImagemSelecionada;
    // Caminho para salvar o arquivo da foto
    private File file;
    private ImageView imgView;
    private Usuario usuario = new Usuario();
    private Button btnAlterarFoto;
    private EditText txtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        String cpf = Prefs.getString(getApplicationContext(), "cpf");
        String senha = Prefs.getString(getApplicationContext(), "senha");
        if (cpf != null && senha != null){
            new UsuarioTask().execute(cpf, senha);
        }

        imgView = (ImageView) findViewById(R.id.imagem);
        btnAlterarFoto = (Button) findViewById(R.id.btnAlterarFoto);
        txtNome = (EditText) findViewById(R.id.txtNome);

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
                file = SDCardUtils.getPrivateFile(getBaseContext(), "foto_perfil.jpg", Environment.DIRECTORY_PICTURES);
                // Chama a intent informando o arquivo para salvar a foto
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(i, 0);
            }
        });
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
            usuario.setFotoPerfil(encodeTobase64(bitmap));
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

    public void alterarFoto(View view) {

    }

    private class UsuarioTask extends AsyncTask<String, Void, Usuario> {

        @Override
        protected Usuario doInBackground(String... strings) {
            UsuarioService service = new UsuarioService();
            try {
                String cpf = strings[0];
                String senha = strings[1];

                return service.logar(cpf, senha);
            } catch (IOException e) {
                Log.e("ERRO", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Usuario usuario) {
            Toast.makeText(getAppCompatActivity(), "Entrou", Toast.LENGTH_LONG).show();
            if (usuario != null && usuario.getUsuarioId() != 0){
                Perfil.this.usuario = usuario;
                Perfil.this.txtNome.setText(usuario.getNome());

                if (usuario.getFotoPerfil() != null) {
                    byte[] decodedByte = Base64.decode(usuario.getFotoPerfil(), 0);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
                    Perfil.this.imgView.setImageBitmap(bitmap);
                }

                Perfil.this.btnAlterarFoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                UsuarioService service = new UsuarioService();
                                try {
                                    service.atualizarUsuario(Perfil.this.usuario);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            finish();
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                });
            }
        }
    }

}