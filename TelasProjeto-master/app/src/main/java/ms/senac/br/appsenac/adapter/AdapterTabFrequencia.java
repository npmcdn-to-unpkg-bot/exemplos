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
public class AdapterTabFrequencia extends BaseAdapter {
    List<String> bloco;
    List<String> faltas;
    List<String> faltasToleraveis;
    List<String> cargaHoraria;

    private LayoutInflater inflater;
    private Context context;

    public AdapterTabFrequencia(Context context, List<String> bloco, List<String> faltas, List<String> faltasToleraveis, List<String> cargaHoraria ) {
        this.bloco = bloco;
        this.faltas = faltas;
        this.faltasToleraveis = faltasToleraveis;
        this.cargaHoraria = cargaHoraria;
        this.context = context;
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
        View v = inflater.inflate(R.layout.adapter_tab_frequencia,null);

        // Recuperando os componentes de View
        TextView txtBlocoFreq = (TextView) v.findViewById(R.id.txtBlocoFreq);
        txtBlocoFreq.setText(bloco.get(position));

        TextView txtFaltas = (TextView) v.findViewById(R.id.txtFaltas);
        txtFaltas.setText(faltas.get(position));

        TextView txtFaltasToleraveis = (TextView) v.findViewById(R.id.txtFaltasToleraveis);
        txtFaltasToleraveis.setText(faltasToleraveis.get(position));

        TextView txtCargaHoraria = (TextView) v.findViewById(R.id.txtCargaHoraria);
        txtCargaHoraria.setText(cargaHoraria.get(position));

        LinearLayout ll = (LinearLayout) v.findViewById(R.id.linhaAdapterFrequencia);

        // Alternando a cor de acordo com a posição se impar ou par
        if(position % 2 == 1){
            ll.setBackgroundColor(context.getResources().getColor(R.color.colorCinza));
        }else {
            ll.setBackgroundColor(context.getResources().getColor(R.color.colorBranco));
        }

        return v;
    }
}
