package com.example.beneson.idrugs.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.beneson.idrugs.Adapters.FilterAdapterCategoria;
import com.example.beneson.idrugs.Adapters.ProdutoAdapter;
import com.example.beneson.idrugs.Classes.Produto;
import com.example.beneson.idrugs.Fragments.BlankFragment;
import com.example.beneson.idrugs.Fragments.ProdutoFragment;
import com.example.beneson.idrugs.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListProductActivity extends AppCompatActivity {

    private ProgressBar spinner;
    private DrawerLayout drawerLayoutFilter;
    private boolean isDrawerOpen = false;
    Toolbar toolbar;

    private DatabaseReference mFirebaseRef;
    private ValueEventListener mConnectedListener;
    private ProdutoAdapter mProdutoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_product);
        toolbar = (Toolbar) findViewById(R.id.toolbar_list_product);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);

        drawerLayoutFilter = (DrawerLayout) findViewById(R.id.layout_drawer_filter);

        ListView listViewFilterCategoria = (ListView) findViewById(R.id.list_view_categoria_filter);
        assert listViewFilterCategoria != null;
        listViewFilterCategoria.setAdapter(new FilterAdapterCategoria(this, populaCategorias()));
        //ListView listViewFilterFormaFarmaceutica = (ListView) findViewById(R.id.list_view_forma_farmaceutica_filter);
        //listViewFilterFormaFarmaceutica.setAdapter(new FilterAdapterFormaFarmaceutica(this, listFormaFarmaceutica));

        spinner = (ProgressBar) findViewById(R.id.progress_bar_list_product);
        assert spinner != null;
        spinner.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        String pesquisaMedicamento = intent.getStringExtra("medicamento");

        productSearch(properCase(pesquisaMedicamento));
    }

    private void productSearch(String pesquisaMedicamento) {

        Log.i("tagx", "Pesquisando Medicamentos: " + pesquisaMedicamento);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("produto");
        Log.i("tagx", FirebaseDatabase.getInstance().toString());
        Log.i("tagx", FirebaseDatabase.getInstance().getReference().toString());

        Query produtosComercial = databaseReference
                .orderByChild("nomeComercial")
                .startAt(pesquisaMedicamento);

        Log.i("tagx", "Hello Word 1: " + produtosComercial.toString());

        final List<Produto> resultadoPesquisa = new ArrayList<>();

        produtosComercial.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.i("tagx", "Hello Word 2");

                Log.i("tagx", dataSnapshot.toString());

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Produto produto = d.getValue(Produto.class);
                    produto.setIdProduto(dataSnapshot.getKey());
                    Log.i("tagx", produto.getNomeComercial());
                    resultadoPesquisa.add(produto);
                }

                Log.i("tagx", "Hello Word 3");
                continueCreate(resultadoPesquisa);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Snackbar.make(findViewById(R.id.ac_list_product), "Erro no acesso ao servidor. Tente novamente.", Snackbar.LENGTH_LONG).show();

            }
        });

        Log.i("tagx", "Hello Word 4");
    }


    public void continueCreate(List<Produto> resultado) {

        ProdutoFragment fragmentProdutos = (ProdutoFragment) getSupportFragmentManager().findFragmentByTag("produtoFragment");
        if(fragmentProdutos==null) {

            Log.i("tagx", "Hello Word 5");

            fragmentProdutos = new ProdutoFragment();
            fragmentProdutos.setListaRecebida(resultado);

            Log.i("tagx", "Hello Word 6");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container_product, fragmentProdutos, "produtoFrag");
            ft.commit();
        }

        spinner.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.cart:
                Intent intent = new Intent(ListProductActivity.this, CartActivity.class);
                startActivity(intent);
                return true;
            case R.id.filter:
                if (!isDrawerOpen) {
                    drawerLayoutFilter.openDrawer(GravityCompat.END);
                    isDrawerOpen = true;
                    //drawerLayoutFilter.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                    return true;
                } else {
                    //drawerLayoutFilter.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    isDrawerOpen = false;
                    drawerLayoutFilter.closeDrawer(GravityCompat.END);
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void loadTransaction() {
        spinner.setVisibility(View.VISIBLE);

        BlankFragment bf = new BlankFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.rl_fragment_container_product, bf, "mainFrag");
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_product, menu);

        final MenuItem searchItem = menu.findItem(R.id.product_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String searchQuery) {
                loadTransaction();
                searchView.clearFocus();
                productSearch(properCase(searchQuery));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newSearch) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    private List<String> populaCategorias() {

        List<String> categorias = new ArrayList<>();
        categorias.add("Analgésicos");
        categorias.add("Antianêmicos");
        categorias.add("Antivertinosos");
        categorias.add("Vitaminas");
        categorias.add("Anticonvulsionantes");
        categorias.add("Penicilinas");
        categorias.add("Outros");
        return categorias;

    }


    String properCase(String inputVal) {
// Empty strings should be returned as-is.
        if (inputVal.length() == 0) return "";

// Strings with only one character uppercased.
        if (inputVal.length() == 1) return inputVal.toUpperCase();

// Otherwise uppercase first letter, lowercase the rest.
        return inputVal.substring(0, 1).toUpperCase()
                + inputVal.substring(1).toLowerCase();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}