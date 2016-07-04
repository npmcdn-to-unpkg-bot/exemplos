package ms.senac.br.appsenac.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ms.senac.br.appsenac.R;
import ms.senac.br.appsenac.utils.AlertUtils;
import ms.senac.br.appsenac.utils.PermissionUtils;
import ms.senac.br.appsenac.utils.Prefs;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Lista de permissões necessárias.
        String permissions[] = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_NETWORK_STATE,
        };

        // Valida lista de permissões.
        boolean ok = PermissionUtils.validate(this, 0, permissions);

        if (ok) {
            // Tudo OK, pode entrar.
            int usuarioId = Prefs.getInteger(getApplicationContext(), "usuarioId");
            if (usuarioId != 0) {
                startActivity(new Intent(this, PaginaIncial.class));
                finish();
            }else{
                startActivity(new Intent(this, Login.class));
                finish();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                // Negou a permissão. Mostra alerta e fecha.
                AlertUtils.alert(getApplicationContext(), R.string.app_name, R.string.mensagem_erro, R.string.ok, new Runnable() {
                    @Override
                    public void run() {
                        // Negou permissão. Sai do app.
                        finish();
                    }
                });
                return;
            }
        }

        int usuarioId = Prefs.getInteger(getApplicationContext(), "usuarioId");
        if (usuarioId != 0) {
            startActivity(new Intent(this, PaginaIncial.class));
            finish();
        }else{
            startActivity(new Intent(this, Login.class));
            finish();
        }
    }
}
