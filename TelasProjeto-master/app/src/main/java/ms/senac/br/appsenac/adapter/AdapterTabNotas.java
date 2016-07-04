package ms.senac.br.appsenac.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ms.senac.br.appsenac.R;

/**
 * Created by Gustavo on 07/03/2016.
 */
public class AdapterTabNotas extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    List<String> bloco;
    List<String> notas;

    /**
     * Contrutor para criar o adapter customizado para notas
     * @param context
     * @param bloco do tipo List<String>
     * @param notas do tipo List<String>
     */
    public AdapterTabNotas(Context context,List<String> bloco, List<String> notas) {
        this.context = context;
        this.bloco = bloco;
        this.notas = notas;
        this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return bloco.size();
    }

    @Override
    public Object getItem(int position) {
        return bloco.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.adapter_tab_notas,null);

        // Recuperando os componentes de View
        TextView txtBloco = (TextView) v.findViewById(R.id.txtBloco);
        txtBloco.setText(bloco.get(position));

        TextView txtNota = (TextView) v.findViewById(R.id.txtNota);
        txtNota.setText(notas.get(position));

        LinearLayout ll = (LinearLayout) v.findViewById(R.id.linhaAdapterNotas);

        // Alternando a cor de acordo com a posição se impar ou par
        if(position % 2 == 1){
            ll.setBackgroundColor(context.getResources().getColor(R.color.colorCinza));
        }else {
            ll.setBackgroundColor(context.getResources().getColor(R.color.colorBranco));
        }
        return v;
    }
}
