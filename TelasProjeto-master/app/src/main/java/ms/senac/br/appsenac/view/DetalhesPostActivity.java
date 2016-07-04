package ms.senac.br.appsenac.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import ms.senac.br.appsenac.R;
import ms.senac.br.appsenac.adapter.AdpterComentarios;
import ms.senac.br.appsenac.model.Comentario;
import ms.senac.br.appsenac.model.Post;
import ms.senac.br.appsenac.model.PostComentario;
import ms.senac.br.appsenac.model.Usuario;
import ms.senac.br.appsenac.service.ComentarioService;
import ms.senac.br.appsenac.service.PostService;
import ms.senac.br.appsenac.utils.AlertUtils;
import ms.senac.br.appsenac.utils.AndroidUtils;
import ms.senac.br.appsenac.utils.ImageResizeUtils;
import ms.senac.br.appsenac.utils.Prefs;

public class DetalhesPostActivity extends AppCompatActivity {
    private PostComentario post;
    private ListView lvComentarios;
    private EditText txtComentario;
    private AdpterComentarios adpterComentarios;
    private ProgressBar bolinha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_post);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            final int id = bundle.getInt("idPost");
            new PostAbertoTask().execute(id);
        }

        bolinha = (ProgressBar) findViewById(R.id.bolinha);
        bolinha.setVisibility(View.VISIBLE);

        txtComentario = (EditText) findViewById(R.id.comentario);
        ImageButton btComentar = (ImageButton) findViewById(R.id.btComentar);
        btComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comentario = txtComentario.getText().toString();
                new PublicaComentarioTask().execute(comentario);

            }
        });
     }

    public void mostrarDetalhesDoPost(){
        ImageView imgPerfilDonoPost = (ImageView) findViewById(R.id.imgPerfilDonoPost);

        TextView txtNomeDonoPost = (TextView) findViewById(R.id.txtNomeDonoPost);
        txtNomeDonoPost.setText(post.getUsuario().getNome());

        TextView txtDataPost = (TextView) findViewById(R.id.txtDataPost);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        //format.setTimeZone(TimeZone.getTimeZone("GMT-0400"));
        txtDataPost.setText(format.format(post.getDataDaPostagem().getTime()));

        TextView txtTituloPost = (TextView) findViewById(R.id.txtTituloPost);
        txtTituloPost.setText(post.getTitulo());

        TextView txtTextoPost = (TextView) findViewById(R.id.txtTextoPost);
        txtTextoPost.setText(post.getTexto());

        ImageView imgPost = (ImageView) findViewById(R.id.imgPost);
        if (post.getImagem() != null) {
            Bitmap bitmap = decodeBase64(post.getImagem());
            imgPost.setImageBitmap(bitmap);
        }
        ImageView imgFotoPerfil = (ImageView) findViewById(R.id.imgFotoPerfil);
        if (post.getUsuario().getFotoPerfil() != null){
            Bitmap bitmap = redimencionaImgem(post.getUsuario().getFotoPerfil(), imgFotoPerfil);
            imgFotoPerfil.setImageBitmap(bitmap);
        }

        TextView txtContadorLike = (TextView) findViewById(R.id.txtContadorLike);
        txtContadorLike.setText("TESTE");

        adpterComentarios = new AdpterComentarios(getApplicationContext(), post.getComentarios());
        lvComentarios = (ListView) findViewById(R.id.lvComentarios);
        lvComentarios.setAdapter(adpterComentarios);
        calculaAlturaDoListViewComItensEhAjustaNaTela(lvComentarios);
    }

    private Bitmap redimencionaImgem(String input, ImageView imageView) {

        // 2º Descobre as dimenções originais da foto
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        byte[] decodedByte = Base64.decode(input, 0);
        BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length, options);

        int widthOriginal = options.outWidth;
        int heightOriginal = options.outHeight;

        // 1º Escolhe o tamanho que a foto precisa ter
        int widthImageView = imageView.getLayoutParams().width;
        int heightImageView = imageView.getLayoutParams().height;

        Log.d("TAGG", "Resize img, w:" + widthOriginal + " / h:" + heightOriginal + ", to w:" + widthImageView + " / h:" + heightImageView);

        // 3º Define a escala da foto
        int scaleFactor = Math.min(widthOriginal / widthImageView, heightOriginal / heightImageView);
        options.inSampleSize = scaleFactor;
        Log.d("TAGG", "inSampleSize:" + options.inSampleSize);

        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length, options);

        Log.d("TAGG", "Resize Reduzida, w:" + bitmap.getWidth() + " / h:" + bitmap.getHeight());

        return bitmap;
    }


    private void calculaAlturaDoListViewComItensEhAjustaNaTela(ListView listView) {
        int totalHeight = 0;
        ListAdapter adapter = listView.getAdapter();
        int lenght = adapter.getCount();
        for (int i = 0; i < lenght; i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
            int tamanhoTexto = post.getComentarios().get(i).getTexto().length();
            if (tamanhoTexto > 121){
                totalHeight += tamanhoTexto;
            }
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = (totalHeight + (listView.getDividerHeight()) * (adapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    private class PostAbertoTask extends AsyncTask<Integer, Void, PostComentario> {

        @Override
        protected PostComentario doInBackground(Integer... params) {
            try {
                return new PostService().buscarporId(params[0]);
            } catch (IOException e) {
                Log.e("AAA", e.getMessage(), e);
                return new PostComentario();
            }
        }

        @Override
        protected void onPostExecute(PostComentario postComentario) {
            if (postComentario.getPostId() == 0){
                AlertUtils.alertSupport(getAppCompatActivity(), "Alerta", "Aconteceu um erro ao tentar abrir post!", null);
            }else {
                DetalhesPostActivity.this.post = postComentario;
                mostrarDetalhesDoPost();
            }
            DetalhesPostActivity.this.bolinha.setVisibility(View.INVISIBLE);
        }
    }

    private class PublicaComentarioTask extends AsyncTask<String, Void, Comentario> {

        @Override
        protected Comentario doInBackground(String... strings) {
            int usuarioId = Prefs.getInteger(getApplicationContext(), "usuarioId");
            String nomeUsuario = Prefs.getString(getApplicationContext(), "nomeUsuario");
            Usuario usuario = new Usuario();
            usuario.setUsuarioId(usuarioId);
            usuario.setNome(nomeUsuario);

            Post post = new Post();
            post.setPostId(DetalhesPostActivity.this.post.getPostId());

            Comentario comentario = new Comentario();
            comentario.setPost(post);
            comentario.setUsuario(usuario);
            comentario.setTexto(strings[0]);

            try {
                new ComentarioService().inserirComentario(comentario);
                return comentario;
            } catch (Exception e) {
                Log.e("ERRO", e.getMessage(), e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(Comentario comentario) {
            if (comentario != null) {
                AndroidUtils.closeVirtualKeyboard(getApplicationContext(), DetalhesPostActivity.this.lvComentarios);
                comentario.setDataDoComentario(Calendar.getInstance());
                DetalhesPostActivity.this.post.getComentarios().add(comentario);
                DetalhesPostActivity.this.adpterComentarios.novosDados(DetalhesPostActivity.this.post.getComentarios());
                DetalhesPostActivity.this.calculaAlturaDoListViewComItensEhAjustaNaTela(DetalhesPostActivity.this.lvComentarios);
                DetalhesPostActivity.this.txtComentario.setText("");
            }else{
                if (AndroidUtils.isNetworkAvailable(getAppCompatActivity())){
                    AlertUtils.alertSupport(getAppCompatActivity(), "Alerta", "Você precisa estar conectado na internet para usar esse aplicativo!", null);
                }else {
                    AlertUtils.alertSupport(getAppCompatActivity(), "Alerta", "Aconteceu um erro no WebService ao tentar inserir o comentário", null);
                }
            }
        }
    }

    public AppCompatActivity getAppCompatActivity() {
        return this;
    }
}
