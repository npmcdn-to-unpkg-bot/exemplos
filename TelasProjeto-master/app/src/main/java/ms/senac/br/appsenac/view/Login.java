package ms.senac.br.appsenac.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ms.senac.br.appsenac.R;
import ms.senac.br.appsenac.adapter.AdapterPost;
import ms.senac.br.appsenac.model.Post;
import ms.senac.br.appsenac.model.Usuario;
import ms.senac.br.appsenac.service.PostService;
import ms.senac.br.appsenac.service.UsuarioService;
import ms.senac.br.appsenac.utils.AlertUtils;
import ms.senac.br.appsenac.utils.AndroidUtils;
import ms.senac.br.appsenac.utils.Prefs;

public class Login extends AppCompatActivity {
    private EditText txtCpf, txtSenha;
    private Button btnLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtCpf = (EditText) findViewById(R.id.txtCpf);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        btnLogar = (Button) findViewById(R.id.btnLogar);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cpf = txtCpf.getText().toString();
                String senha = txtSenha.getText().toString();

                if(AndroidUtils.isNetworkAvailable(getApplicationContext())) {
                    new UsuarioTask().execute(cpf, senha);
                }else{
                    AlertUtils.alertSupport(getAppCompatActivity(), "Alerta", "Você precisa estar conectado na internet para usar esse aplicativo!", null);
                }
            }
        });
    }

    private class UsuarioTask extends AsyncTask<String, Void, Usuario> {

        @Override
        protected Usuario doInBackground(String... strings) {
            UsuarioService service = new UsuarioService();
            try {
                String cpf = strings[0];
                String senha = strings[1];

                return service.logar(cpf, senha);
            } catch (IOException e) {
                Log.e("ERRO", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Usuario usuario) {
            if (usuario != null && usuario.getUsuarioId() != 0){
                // Salva id e nome do usuário
                Prefs.setInteger(getApplicationContext(), "usuarioId", usuario.getUsuarioId());
                Prefs.setString(getApplicationContext(), "nomeUsuario", usuario.getNome());
                Prefs.setString(getApplicationContext(), "cpf", usuario.getCpf());
                Prefs.setString(getApplicationContext(), "senha", usuario.getSenha());

                startActivity(new Intent(getApplicationContext(), PaginaIncial.class));
                finish();
            }else{
                AlertUtils.alertSupport(getAppCompatActivity(), "Alerta", "Usuário não esta cadastrado!", null);
            }
        }
    }

    public AppCompatActivity getAppCompatActivity() {
        return this;
    }
}
