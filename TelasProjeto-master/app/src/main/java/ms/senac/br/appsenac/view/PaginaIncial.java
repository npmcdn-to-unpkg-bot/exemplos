package ms.senac.br.appsenac.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ms.senac.br.appsenac.R;
import ms.senac.br.appsenac.activity.BaseActivity;
import ms.senac.br.appsenac.adapter.AdapterPost;
import ms.senac.br.appsenac.model.Post;
import ms.senac.br.appsenac.service.PostService;
import ms.senac.br.appsenac.utils.AlertUtils;
import ms.senac.br.appsenac.utils.AndroidUtils;

public class PaginaIncial extends BaseActivity {
    private List<Post> listPosts;
    private ListView lvPosts;
    private ProgressBar bolinha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_incial);
        setUpToolbar();
        setUpNavigationView();

        lvPosts = (ListView) findViewById(R.id.lvPosts);
        bolinha = (ProgressBar) findViewById(R.id.bolinha);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(AndroidUtils.isNetworkAvailable(getApplicationContext())) {
            taskPosts();
        }else{
            AlertUtils.alertSupport(getAppCompatActivity(), "Alerta", "Você precisa estar conectado na internet para usar esse aplicativo!", null);
        }
    }

    public void taskPosts(){
        new GetPostsTask().execute();
    }

    private class GetPostsTask extends AsyncTask<Void, Void, List<Post>>{

        @Override
        protected void onPreExecute() {
            PaginaIncial.this.bolinha.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Post> doInBackground(Void... params) {
            try {
                return new PostService().listarPosts();
            }catch (Exception e){
                Log.e("AAA", e.getMessage(), e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Post> posts) {
            if (posts != null){
                PaginaIncial.this.listPosts = posts;
                lvPosts.setAdapter(new AdapterPost(listPosts, getAppCompatActivity(),getSupportActionBar().getThemedContext(), onCLick()));
            }else{
                AlertUtils.alertSupport(getAppCompatActivity(), "Alerta", "Aconteceu um erro ao tentar carregar o feed", null);
            }
            PaginaIncial.this.bolinha.setVisibility(View.INVISIBLE);
        }
    }

    private AdapterPost.OnClickListener onCLick() {
        return new AdapterPost.OnClickListener() {
            @Override
            public void onClick(View v, Post post) {
                Intent intent = new Intent(getApplicationContext(), DetalhesPostActivity.class);
                intent.putExtra("idPost", post.getPostId());
                startActivity(intent);
            }

            @Override
            public void onClickMenuDeletar(final Post post) {

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new PostService().deletar(post.getPostId());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listPosts.remove(post);
                                    AdapterPost adapter = (AdapterPost) lvPosts.getAdapter();
                                    adapter.novosDados(listPosts);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                AlertUtils.alertSupportExcluirPost(getAppCompatActivity(), "Alerta", "Você realmente deseja excluir esse post?", thread);
            }
        };

    }
}