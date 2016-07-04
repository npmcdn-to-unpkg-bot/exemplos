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
import ms.senac.br.appsenac.adapter.AdapterTabNotas;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotaFragment extends Fragment {

    ListView lvBlocoNotas;

    // Listas fake para alimentar a lista do adapter
    List<String> listaFakeBloco = Arrays.asList("Bloco", "Bloco", "Bloco", "Bloco", "Bloco", "Bloco");
    List<String> listaFakeNotas = Arrays.asList("Nota", "Nota", "Nota", "Nota", "Nota", "Nota");

    public NotaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_nota, container, false);

        //Recuperando o ListView
        lvBlocoNotas = (ListView) v.findViewById(R.id.listNotas);

        // Instanciando o adapter customizado para notas
        AdapterTabNotas adapterTabNotas = new AdapterTabNotas(getContext(), listaFakeBloco, listaFakeNotas);

        // Configurando o adapter na lista
        lvBlocoNotas.setAdapter(adapterTabNotas);

        return v;
    }

}
