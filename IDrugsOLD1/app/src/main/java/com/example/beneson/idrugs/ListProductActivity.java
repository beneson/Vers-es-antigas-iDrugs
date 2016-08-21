package com.example.beneson.idrugs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

import java.util.List;

public class ListProductActivity extends AppCompatActivity {

    String pesquisaMedicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_list_product);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        String ID = "DECFE9AC-8E8D-6ECC-FF16-32C4BD194E00";
        String KEY = "2E005C34-7A11-5485-FF63-49C631E02000";
        String version = "v1";
        Backendless.initApp(this, ID, KEY, version);

        Intent intent = getIntent();
        pesquisaMedicamento = intent.getStringExtra("medicamento");

        Log.i("tagx", "Pesquisando Medicamentos: " + pesquisaMedicamento);
        try {
            carregaLista(pesquisaMedicamento);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void carregaLista(String pesquisaMedicamento) throws InterruptedException {

        String whereClause = "nomeComercial like '%"+pesquisaMedicamento+"%'";
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause(whereClause);

        AsyncCallback<BackendlessCollection<Produto>> callback = new AsyncCallback<BackendlessCollection<Produto>>() {
            @Override
            public void handleResponse(BackendlessCollection<Produto> resultadoPesquisa) {

                final List<Produto> resultado = resultadoPesquisa.getCurrentPage();

                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Fragment
                            ProdutoFragment frag = (ProdutoFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
                            if (frag == null) {
                                frag = new ProdutoFragment(resultado);
                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.rl_fragment_container_product, frag, "mainFrag");
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

        Backendless.Persistence.of(Produto.class).find(dataQuery, callback);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_product, menu);

        MenuItem searchItem = menu.findItem(R.id.product_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        CharSequence charSequence = searchView.getQuery();
        pesquisaMedicamento = charSequence.toString();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.cart:
                intent = new Intent(ListProductActivity.this, CartActivity.class);
                startActivity(intent);
                return true;
            case R.id.product_search:
                try {
                    carregaLista(pesquisaMedicamento);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
