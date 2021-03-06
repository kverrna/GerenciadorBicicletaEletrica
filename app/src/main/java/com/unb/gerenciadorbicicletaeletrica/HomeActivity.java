package com.unb.gerenciadorbicicletaeletrica;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class HomeActivity extends ActionBarActivity {

    ConectionFactory conectionFactory = new ConectionFactory();
    String recebimento_servidor;
    private String SHAHash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void habilitarTrava(View view) {

        new Thread() {
            public void run() {
                EditText estacaoEt = (EditText) findViewById(R.id.estacaoTextView);
                final EditText senhaEt = (EditText) findViewById(R.id.senhaTextView);

                final String hashSenha = Util.computeSHAHash(MainActivity.email_logado, senhaEt.getText().toString());

                Log.e("email",hashSenha);

                recebimento_servidor = conectionFactory.postHttpHabilitar(hashSenha, estacaoEt.getText().toString());

                Log.e("email",recebimento_servidor);

            }
        }.start();
    }

    public void retirarBike(View view) {

        new Thread() {
            public void run() {
                EditText estacaoEt = (EditText) findViewById(R.id.estacaoTextView);
                final EditText senhaEt = (EditText) findViewById(R.id.senhaTextView);

                final String hashSenha = Util.computeSHAHash(MainActivity.email_logado, senhaEt.getText().toString());

                Log.e("email",MainActivity.email_logado);

                recebimento_servidor = conectionFactory.postHttpRetirar(hashSenha, estacaoEt.getText().toString());

                Log.e("email",recebimento_servidor);

            }
        }.start();
    }

}
