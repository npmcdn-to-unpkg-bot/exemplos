package ms.senac.br.appsenac.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import ms.senac.br.appsenac.R;
import ms.senac.br.appsenac.adapter.AdapterTabFrequencia;


/**
 * A simple {@link Fragment} subclass.
 */
public class FrequenciaFragment extends Fragment {
    ListView lvFrequencia;

    //Listas Fake para testar o Adapter
    List<String> lstFakeBloco = Arrays.asList("Bloco", "Bloco", "Bloco", "Bloco", "Bloco", "Bloco", "Bloco");
    List<String> lstFakeFaltas = Arrays.asList("10", "10", "10", "10", "10", "10", "10", "10", "10", "10");
    List<String> lstFakeFaltasToleraveis = Arrays.asList("20", "20", "20", "20", "20", "20", "20", "20", "20");
    List<String> lstFakeCargaHoraria = Arrays.asList("40", "40", "40", "40", "40", "40", "40", "40", "40", "40", "40");

    public FrequenciaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_frequencia, container, false);

        //Recuperando o ListView
        lvFrequencia = (ListView) v.findViewById(R.id.listFrequencia);

        //Instanciando o adapter customizado para frequencia
        AdapterTabFrequencia adapterTabFrequencia = new AdapterTabFrequencia(getContext(), lstFakeBloco, lstFakeFaltas, lstFakeFaltasToleraveis, lstFakeCargaHoraria);

        // Configurando o adapter na lista
        lvFrequencia.setAdapter(adapterTabFrequencia);
        return v;
    }

}
