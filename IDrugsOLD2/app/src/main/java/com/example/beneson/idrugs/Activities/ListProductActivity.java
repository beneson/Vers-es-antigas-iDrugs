package com.example.beneson.idrugs.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.example.beneson.idrugs.Adapters.FilterAdapterCategoria;
import com.example.beneson.idrugs.Adapters.FilterAdapterFormaFarmaceutica;
import com.example.beneson.idrugs.Classes.Produto;
import com.example.beneson.idrugs.Fragments.BlankFragment;
import com.example.beneson.idrugs.Fragments.ProdutoFragment;
import com.example.beneson.idrugs.R;

import java.util.ArrayList;
import java.util.List;

public class ListProductActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private String pesquisaMedicamento;

    private ProgressBar spinner;
    private DrawerLayout drawerLayoutFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Toolbar toolbar;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        toolbar = (Toolbar) findViewById(R.id.toolbar_list_product);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);

        spinner = (ProgressBar) findViewById(R.id.progress_bar_list_product);
        spinner.setVisibility(View.VISIBLE);

        drawerLayoutFilter = (DrawerLayout) findViewById(R.id.layout_drawer_filter);
        drawerLayoutFilter.isFocusableInTouchMode();

        Intent intent = getIntent();
        pesquisaMedicamento = intent.getStringExtra("medicamento");

        try {
            productSearch(pesquisaMedicamento);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void productSearch(String pesquisaMedicamento) throws InterruptedException {



        spinner.setVisibility(View.VISIBLE);
    }



    public void continueCreate(List<Produto> resultado) {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.cart:
                Intent intent = new Intent(ListProductActivity.this, CartActivity.class);
                startActivity(intent);
                return true;
            case R.id.filter:
                drawerLayoutFilter.openDrawer(GravityCompat.END);
                return true;
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_product, menu);

        final MenuItem searchItem = menu.findItem(R.id.product_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String searchQuery) {
                try {
                    loadTransaction();
                    searchView.clearFocus();
                    productSearch(searchQuery);
                    return false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            public boolean onQueryTextChange(String newSearch) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}