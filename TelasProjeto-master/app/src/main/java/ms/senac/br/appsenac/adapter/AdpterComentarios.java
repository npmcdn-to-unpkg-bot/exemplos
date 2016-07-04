package ms.senac.br.appsenac.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import ms.senac.br.appsenac.R;
import ms.senac.br.appsenac.model.Comentario;
import ms.senac.br.appsenac.model.Post;

/**
 * Created by antonio on 25/06/16.
 */
public class AdpterComentarios extends BaseAdapter{
    private Context context;
    private List<Comentario> comentarios;
    private LayoutInflater inflater;

    public AdpterComentarios(Context context, List<Comentario> comentarios) {
        this.comentarios = comentarios;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public void novosDados(List<Comentario> comentarios){
        this.comentarios = comentarios;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return comentarios.size();
    }

    @Override
    public Object getItem(int position) {
        return comentarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Comentario comentario = comentarios.get(position);

        View v = inflater.inflate(R.layout.adapter_comentarios, null);

        ImageView imgDonoComentario = (ImageView) v.findViewById(R.id.imgDonoComentario);
        if (comentario.getUsuario().getFotoPerfil() != null && false){
            Log.d("TAGGGGGG", " " + comentario.getUsuario().getFotoPerfil());
            Bitmap bitmap = redimencionaImgem(comentario.getUsuario().getFotoPerfil(), imgDonoComentario);
            imgDonoComentario.setImageBitmap(bitmap);
        }

        TextView txtNomeComentador = (TextView) v.findViewById(R.id.txtNomeComentador);
        txtNomeComentador.setText(comentario.getUsuario().getNome());

        TextView txtDataComentario = (TextView) v.findViewById(R.id.txtDataComentario);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        txtDataComentario.setText(format.format(comentario.getDataDoComentario().getTime()));

        TextView txtTextoComentario = (TextView) v.findViewById(R.id.txtTextoComentario);
        txtTextoComentario.setText(comentario.getTexto());

        return v;
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
}
