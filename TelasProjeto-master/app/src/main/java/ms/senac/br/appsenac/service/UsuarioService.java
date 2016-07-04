package ms.senac.br.appsenac.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Calendar;

import ms.senac.br.appsenac.model.Post;
import ms.senac.br.appsenac.model.PostComentario;
import ms.senac.br.appsenac.model.Usuario;
import ms.senac.br.appsenac.utils.CalendarDeserializer;
import ms.senac.br.appsenac.utils.HttpHelper;

/**
 * Created by antonio on 25/06/16.
 */
public class UsuarioService {
    private String url;

    public UsuarioService(){
        url = Configuracao.URL+"/Usuario";
    }

    public Usuario logar(String cpf, String senha) throws IOException {

        HttpHelper helper = new HttpHelper();
        String json = helper.doGet(url+"?cpf="+cpf+"&senha="+senha);

        Gson gson = new Gson();
        return gson.fromJson(json, Usuario.class);
    }

    public void atualizarUsuario(Usuario usuario) throws Exception {
        String json = new Gson().toJson(usuario);

        HttpHelper helper = new HttpHelper();
        helper.setContentType("application/json");
        helper.doPut(url, json.getBytes(), "UTF-8");
    }
}
