package ms.senac.br.appsenac.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ms.senac.br.appsenac.R;

public class PrimeiroLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeiro_login);



        Button btnVerificarCpf = (Button) findViewById(R.id.btnVerificarCpf);
        btnVerificarCpf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editCPF = (EditText) findViewById(R.id.editCPF);
                String cpf = editCPF.getText().toString();
                TextView erro = (TextView) findViewById(R.id.txtErro);

                if("12345678910".equals(cpf)){
                    Intent telaCadastrarSenha = new Intent(getApplicationContext(),CadastrarSenhaPergunta.class);
                    startActivity(telaCadastrarSenha);
                    finish();
                }else {
                    erro.setVisibility(View.VISIBLE);
                    erro.setText("Desculpe,mas você não está cadastrado em nosso sistema.\nFaça um curso no senac e aproveite esse app.");
                }
                if("".equals(cpf)) {
                    erro.setText("Insira algum número de CPF!");
                    erro.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
