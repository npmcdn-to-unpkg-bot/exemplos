package ms.senac.br.appsenac.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ms.senac.br.appsenac.R;
import ms.senac.br.appsenac.utils.Prefs;
import ms.senac.br.appsenac.view.AreaAluno;
import ms.senac.br.appsenac.view.Perfil;
import ms.senac.br.appsenac.view.TelaPublicacao;

/**
 * Created by antoniosantana on 24/06/2016.
 */
public class BaseActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    protected Toolbar toolbar;

    public void setUpToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    public void setUpNavigationView(){
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent telaPublicacao = new Intent(getApplicationContext(), TelaPublicacao.class);
                    startActivity(telaPublicacao);
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_paginaIncial) {
            // Handle the camera action

        } else if (id == R.id.nav_perfil) {
            MenuItem menuItem = (MenuItem) findViewById(R.id.nav_cursos);
            Intent telaPerfil = new Intent(getApplicationContext(), Perfil.class);
            startActivity(telaPerfil);
        } else if (id == R.id.nav_cursos) {

        } else if (id == R.id.nav_sobre) {

        } else if (id == R.id.nav_minhas_publicacoes) {

        } else if (id == R.id.nav_curso_01) {
            Intent telaAreaAluno = new Intent(getApplicationContext(), AreaAluno.class);
            startActivity(telaAreaAluno);


        } else if (id == R.id.nav_curso_02) {
            Intent telaAreaAluno = new Intent(getApplicationContext(), AreaAluno.class);
            startActivity(telaAreaAluno);


        } else if (id == R.id.nav_Logout) {
            Prefs.setInteger(getApplicationContext(), "usuarioId", 0);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pagina_incial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filtro) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public AppCompatActivity getAppCompatActivity() {
        return this;
    }
}
