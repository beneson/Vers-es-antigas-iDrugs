package com.example.beneson.idrugs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    String pesquisaMedicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        Log.i("tagx", "onCreate");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String ID = "DECFE9AC-8E8D-6ECC-FF16-32C4BD194E00";
        String KEY = "2E005C34-7A11-5485-FF63-49C631E02000";
        String version = "v1";
        Backendless.initApp(this, ID, KEY, version);

        Intent intent = getIntent();
        pesquisaMedicamento = intent.getStringExtra("medicamento");

        //String endereco = intent.getStringExtra("endereco");
        //textEndereco = (TextView) findViewById(R.id.textEndereco);
        //textEndereco.setText(endereco);

        //minhaLista = (ListView) findViewById(R.id.listView);
        //registerForContextMenu(minhaLista);

        Log.i("tagx", "Pesquisando: " + pesquisaMedicamento);
        try {
            carregaLista(pesquisaMedicamento);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void carregaLista(String pesquisaMedicamento) throws InterruptedException {

        String whereClause = "produto.nomeComercial like '%"+pesquisaMedicamento+"%'";
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause(whereClause);

        AsyncCallback<BackendlessCollection<Ofertado>> callback = new AsyncCallback<BackendlessCollection<Ofertado>>() {
            @Override
            public void handleResponse(BackendlessCollection<Ofertado> resultadoPesquisa) {

                final List<Ofertado> resultado = resultadoPesquisa.getCurrentPage();

                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Fragment
                            OfertadoFragment frag = (OfertadoFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
                            if (frag == null) {
                                frag = new OfertadoFragment(resultado);
                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
                                ft.commit();
                            }
                            Log.i("tagx", "Hello Word 1000");
                        }
                    });
                } catch (Exception e) {
                    Log.i("tagx", "Erro no adapter");
                }
                Log.i("tagx", "Hello Word 2");
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.i("tagx", "Erro no Backendless: "+backendlessFault.toString());
                Toast.makeText(getApplicationContext(), "Problemas na Conexão. Verifique se há disponibilidade na rede.", Toast.LENGTH_SHORT).show();
            }
        };

        Backendless.Persistence.of(Ofertado.class).find(dataQuery, callback);
    }


}
