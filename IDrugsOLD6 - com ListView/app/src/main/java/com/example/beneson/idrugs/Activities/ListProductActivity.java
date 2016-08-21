package com.example.beneson.idrugs.Activities;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.beneson.idrugs.Adapters.ProdutoAdapter;
import com.example.beneson.idrugs.Fragments.BlankFragment;
import com.example.beneson.idrugs.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ListProductActivity extends AppCompatActivity {

    private ProgressBar spinner;
    Toolbar toolbar;

    private DatabaseReference mFirebaseRef;
    private ValueEventListener mConnectedListener;
    private ProdutoAdapter mProdutoAdapter;

    private String mUsername;
    String pesquisaMedicamento;

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
        spinner.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        pesquisaMedicamento = intent.getStringExtra("medicamento");

        mFirebaseRef = FirebaseDatabase.getInstance().getReference().child("produto");
    }

    @Override
    public void onStart() {
        super.onStart();
        // Setup our view and list adapter. Ensure it scrolls to the bottom as data changes
        final ListView listView = (ListView) findViewById(R.id.list);
        // Tell our list adapter that we only want 50 messages at a time
        mProdutoAdapter = new ProdutoAdapter(mFirebaseRef.limitToFirst(30), this, R.layout.cell_product, mUsername);
        listView.setAdapter(mProdutoAdapter);
        mProdutoAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(mProdutoAdapter.getCount() - 1);
            }
        });

        // Finally, a little indication of connection status
        mConnectedListener = mFirebaseRef.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = (Boolean) dataSnapshot.getValue();
                if (connected) {
                    Toast.makeText(ListProductActivity.this, "Connected to Firebase", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ListProductActivity.this, "Disconnected from Firebase", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // No-op
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        mFirebaseRef.getRoot().child(".info/connected").removeEventListener(mConnectedListener);
        mProdutoAdapter.cleanup();
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

    public void loadTransaction() {
        spinner.setVisibility(View.VISIBLE);

        BlankFragment bf = new BlankFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
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
                //productSearch(properCase(searchQuery));
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