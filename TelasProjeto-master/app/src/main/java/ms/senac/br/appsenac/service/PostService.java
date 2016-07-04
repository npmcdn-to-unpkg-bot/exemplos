package ms.senac.br.appsenac.service;

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ms.senac.br.appsenac.model.Post;
import ms.senac.br.appsenac.model.PostComentario;
import ms.senac.br.appsenac.utils.CalendarDeserializer;
import ms.senac.br.appsenac.utils.HttpHelper;

/**
 * Created by antonio on 24/06/16.
 */
public class PostService {
    private String url;

    public PostService(){
        url = Configuracao.URL+"/Post";
    }

    public void inserirPost(Post post) throws Exception {
        String json = new Gson().toJson(post);

        HttpHelper helper = new HttpHelper();
        helper.setContentType("application/json");
        helper.doPost(url, json.getBytes(), "UTF-8");
    }

    public PostComentario buscarporId(int id) throws IOException {

        HttpHelper helper = new HttpHelper();
        String json = helper.doGet(url + "/" + id);
        Log.i("TAG", json);

        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Calendar.class, new CalendarDeserializer());
        Gson gson = gsonBuilder.create();
        PostComentario post = gson.fromJson(json, PostComentario.class);

        return post;
    }

    public List<Post> listarPosts() throws IOException {

        HttpHelper helper = new HttpHelper();
        String json = helper.doGet(url);

        Type listType = new TypeToken<ArrayList<Post>>() {
        }.getType();
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Calendar.class, new CalendarDeserializer());
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, listType);
    }

    public void deletar(int postId)  throws IOException {
        HttpHelper helper = new HttpHelper();
        helper.setContentType("application/json");
        helper.doDelete(url+"/"+postId);
    }
}
