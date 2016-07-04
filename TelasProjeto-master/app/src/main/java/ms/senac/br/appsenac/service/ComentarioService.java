package ms.senac.br.appsenac.service;

import android.util.Log;

import com.google.gson.Gson;

import ms.senac.br.appsenac.model.Comentario;
import ms.senac.br.appsenac.model.Post;
import ms.senac.br.appsenac.utils.HttpHelper;

/**
 * Created by antonio on 26/06/16.
 */
public class ComentarioService {
    private String url;

    public ComentarioService(){
        url = Configuracao.URL+"/Comentario";
    }

    public void inserirComentario(Comentario comentario) throws Exception {
        String json = new Gson().toJson(comentario);

        HttpHelper helper = new HttpHelper();
        helper.setContentType("application/json");
        helper.doPost(url, json.getBytes(), "UTF-8");
    }
}
