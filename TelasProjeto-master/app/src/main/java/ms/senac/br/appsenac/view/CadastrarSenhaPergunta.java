package ms.senac.br.appsenac.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ms.senac.br.appsenac.R;

public class CadastrarSenhaPergunta extends AppCompatActivity {
    EditText novaSenha, repitaSenha, respostaPergunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_senha_pergunta);

        Button btnProximo = (Button) findViewById(R.id.btnProximoEnviar);
        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                novaSenha = (EditText) findViewById(R.id.novaSenha);
                String senha = novaSenha.getText().toString();
                repitaSenha = (EditText) findViewById(R.id.repitaSenha);
                String confirmSenha = repitaSenha.getText().toString();
                respostaPergunta = (EditText) findViewById(R.id.respostaPergunta);

                if (senha.equals(confirmSenha)) {
                    Intent pgPrincipal = new Intent(CadastrarSenhaPergunta.this,PaginaIncial.class);
                    startActivity(pgPrincipal);
                    finish();
                } else {
                    Toast.makeText(CadastrarSenhaPergunta.this, "Senhas não são iguais", Toast.LENGTH_LONG);
                }

            }
        });
    }
}
