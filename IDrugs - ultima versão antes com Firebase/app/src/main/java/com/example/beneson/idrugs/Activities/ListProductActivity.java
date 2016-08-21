package com.example.beneson.idrugs.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.beneson.idrugs.Adapters.ProdutoAdapter;
import com.example.beneson.idrugs.Classes.Produto;
import com.example.beneson.idrugs.Others.RecyclerItemClickListener;
import com.example.beneson.idrugs.R;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;

public class ListProductActivity extends AppCompatActivity {

    private final static String SAVED_ADAPTER_ITEMS = "SAVED_ADAPTER_ITEMS";
    private final static String SAVED_ADAPTER_KEYS = "SAVED_ADAPTER_KEYS";

    private ProgressBar spinner;
    Toolbar toolbar;

    private ProdutoAdapter mProdutoAdapter;
    private ArrayList<Produto> mAdapterItems;
    private ArrayList<String> mAdapterKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_product);

        toolbar = (Toolbar) findViewById(R.id.toolbar_list_product);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        spinner = (ProgressBar) findViewById(R.id.progress_bar_list_product);
        assert spinner != null;
        spinner.setVisibility(ProgressBar.VISIBLE);

        Intent intent = getIntent();
        String pesquisaMedicamento = properCase(intent.getStringExtra("medicamento"));

        handleInstanceState(savedInstanceState);
        setupRecyclerview(properCase(pesquisaMedicamento));

    }


    // Restoring the item list and the keys of the items: they will be passed to the adapter
    private void handleInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(SAVED_ADAPTER_ITEMS) &&
                savedInstanceState.containsKey(SAVED_ADAPTER_KEYS)) {
            mAdapterItems = Parcels.unwrap(savedInstanceState.getParcelable(SAVED_ADAPTER_ITEMS));
            mAdapterKeys = savedInstanceState.getStringArrayList(SAVED_ADAPTER_KEYS);
        } else {
            mAdapterItems = new ArrayList<>();
            mAdapterKeys = new ArrayList<>();
        }
    }

    private void setupRecyclerview(String pesquisaMedicamento) {

        spinner.setVisibility(ProgressBar.VISIBLE);

        Query mQuery = FirebaseDatabase.getInstance().getReference().child("produto").orderByChild("nomeComercial").startAt(pesquisaMedicamento).limitToFirst(30);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_product);
        mProdutoAdapter = new ProdutoAdapter(mQuery, Produto.class, mAdapterItems, mAdapterKeys);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mProdutoAdapter);

        spinner.setVisibility(ProgressBar.GONE);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(ListProductActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {

                        Produto produto = mProdutoAdapter.getItem(position);
                        Intent intent = new Intent(ListProductActivity.this, ListOfferedActivity.class);
                        intent.putParcelableArrayListExtra("produto", produto);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }
                )
        );
    }


    // Saving the list of items and keys of the items on rotation
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_ADAPTER_ITEMS, Parcels.wrap(mProdutoAdapter.getItems()));
        outState.putStringArrayList(SAVED_ADAPTER_KEYS, mProdutoAdapter.getKeys());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProdutoAdapter.destroy();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.cart:
                Intent intent = new Intent(ListProductActivity.this, CartActivity.class);
                startActivity(intent);
                return true;
            case R.id.filter:
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*public void loadTransaction() {
        spinner.setVisibility(View.VISIBLE);
        BlankFragment bf = new BlankFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.commit();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_product, menu);

        final MenuItem searchItem = menu.findItem(R.id.product_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String searchQuery) {
                //loadTransaction();
                searchView.clearFocus();
                setupRecyclerview(properCase(searchQuery));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newSearch) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
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

}