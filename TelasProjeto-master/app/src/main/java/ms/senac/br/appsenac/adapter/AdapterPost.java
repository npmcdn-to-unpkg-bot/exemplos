package ms.senac.br.appsenac.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.PopupMenu;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import ms.senac.br.appsenac.R;
import ms.senac.br.appsenac.model.Post;
import ms.senac.br.appsenac.service.PostService;
import ms.senac.br.appsenac.utils.AlertUtils;
import ms.senac.br.appsenac.utils.ImageResizeUtils;
import ms.senac.br.appsenac.utils.Prefs;
import ms.senac.br.appsenac.view.DetalhesPostActivity;

/**
 * Created by Gustavo on 08/03/2016.
 */
public class AdapterPost extends BaseAdapter {

    private List<Post> listPosts;
    private Context context;
    private Context themedContext;
    private LayoutInflater inflater;
    private OnClickListener onClickListener;

    public AdapterPost(List<Post> listPosts, Context context, Context themedContext, OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.listPosts = listPosts;
        this.context = context;
        this.themedContext = themedContext;
        this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public interface OnClickListener {
        void onClick(View v, Post post);
        void onClickMenuDeletar(Post post);
    }

    public void novosDados(List<Post> posts){
        this.listPosts = posts;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listPosts.size();
    }

    @Override
    public Object getItem(int position) {
        return listPosts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Post post = listPosts.get(position);

        View v = inflater.inflate(R.layout.adapter_post, null);


            ImageButton btMenuPopup = (ImageButton) v.findViewById(R.id.btMenuPopup);
            btMenuPopup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mostraOpcoes(view, post, post.getUsuario().getUsuarioId());
                }
            });


        // TODO: Dar um jeito de trazer as imagens  08/03/2016
        ImageView imgPerfilDonoPost = (ImageView) v.findViewById(R.id.imgPerfilDonoPost);
        if (post.getUsuario().getFotoPerfil() != null){
            Bitmap bitmap = redimencionaImgem(post.getUsuario().getFotoPerfil(), imgPerfilDonoPost);
            imgPerfilDonoPost.setImageBitmap(bitmap);
        }

        TextView txtNomeDonoPost = (TextView) v.findViewById(R.id.txtNomeDonoPost);
        txtNomeDonoPost.setText(post.getUsuario().getNome());

        TextView txtDataPost = (TextView) v.findViewById(R.id.txtDataPost);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        //format.setTimeZone(TimeZone.getTimeZone("GMT-0400"));
        txtDataPost.setText(format.format(post.getDataDaPostagem().getTime()));

        TextView txtTituloPost = (TextView) v.findViewById(R.id.txtTituloPost);
        txtTituloPost.setText(post.getTitulo());

        TextView txtTextoPost = (TextView) v.findViewById(R.id.txtTextoPost);
        txtTextoPost.setText(post.getTexto());

        // TODO: Dar um jeito de trazer as imagens  08/03/2016
        ImageView imgPost = (ImageView) v.findViewById(R.id.imgPost);
        if (post.getImagem() != null) {
            Bitmap bitmap = redimencionaImgem(post.getImagem());
            imgPost.setImageBitmap(bitmap);
        }else {
            imgPost.setVisibility(View.GONE);
        }

        imgPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v, post);
            }
        });

        TextView txtContadorLike = (TextView) v.findViewById(R.id.txtContadorLike);
        txtContadorLike.setText("TESTE");

        TextView txtContadorComentario = (TextView) v.findViewById(R.id.txtContadorComentario);
        txtContadorComentario.setText("TESTE");

        // TODO: Dar um jeito de trazer as imagens  08/03/2016
        ImageView imgDonoUltimoComentario = (ImageView) v.findViewById(R.id.imgDonoUltimoComentario);

        TextView txtNomeDonoUltimoComentario = (TextView) v.findViewById(R.id.txtNomeDonoUltimoComentario);
        txtNomeDonoUltimoComentario.setText("TESTE");

        TextView txtDataUltimoComentario = (TextView) v.findViewById(R.id.txtDataUltimoComentario);
        txtDataUltimoComentario.setText("TESTE");

        TextView txtUltimoComentario = (TextView) v.findViewById(R.id.txtUltimoComentario);
        txtUltimoComentario.setText("TESTE");

        return v;
    }

    private Bitmap redimencionaImgem(String input) {

        // 2º Descobre as dimenções originais da foto
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        byte[] decodedByte = Base64.decode(input, 0);
        BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length, options);

        int widthOriginal = options.outWidth;
        int heightOriginal = options.outHeight;

        // 1º Escolhe o tamanho que a foto precisa ter
        int widthImageView = widthOriginal / 6;
        int heightImageView = heightOriginal / 6;

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

    private void mostraOpcoes(View ancoraView, final Post post, final int usuarioIdPost) {
        if(ancoraView != null) {
            // Cria o PopupMenu posicionado na âncora
            PopupMenu popupMenu = new PopupMenu(themedContext, ancoraView);

            int usuarioId = Prefs.getInteger(context, "usuarioId");
            if (usuarioId == usuarioIdPost) {
                MenuItem m1 = popupMenu.getMenu().add(0, 0, 0, "Excluir");
                m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                m1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        onClickListener.onClickMenuDeletar(post);
                        return true;
                    }
                });

                MenuItem m2 = popupMenu.getMenu().add(0, 0, 0, "Editar");
                m2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                m2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(context, "Editar " + post.getPostId(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });
            }

            MenuItem m3 = popupMenu.getMenu().add(0, 0, 0, "Denunciar");
            m3.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            m3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Toast.makeText(context, "Denunciar " + post.getPostId(), Toast.LENGTH_LONG).show();
                    return true;
                }
            });

            popupMenu.show();
        }
    }
}
