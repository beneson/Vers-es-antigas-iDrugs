package com.example.beneson.idrugs.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.beneson.idrugs.Adapters.OfertadoAdapter;
import com.example.beneson.idrugs.Classes.Ofertado;
import com.example.beneson.idrugs.Classes.Produto;
import com.example.beneson.idrugs.Others.RecyclerItemClickListener;
import com.example.beneson.idrugs.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListOfferedActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private final static String SAVED_ADAPTER_ITEMS = "SAVED_ADAPTER_ITEMS";
    private final static String SAVED_ADAPTER_KEYS = "SAVED_ADAPTER_KEYS";

    private ProgressBar spinner;
    private GoogleApiClient googleApiClient;

    private OfertadoAdapter mOfertadoAdapter;
    private ArrayList<Ofertado> mAdapterItems;
    private ArrayList<String> mAdapterKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_offered);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_list_offered);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        spinner = (ProgressBar) findViewById(R.id.progress_bar_list_offered);
        spinner.setVisibility(View.VISIBLE);

        Produto produto = (Produto) getIntent().getParcelableArrayListExtra("produto");

        try {
            callConnection();
        } catch (Exception e) {
            Log.i("tagx", "Erro na geolocalização");
            Toast.makeText(getApplicationContext(), "Problemas na localização. Verifique seu GPS.", Toast.LENGTH_SHORT).show();
        }

        Log.i("tagx", "Pesquisandondo Farmácias: produto " + produto.getIdProduto());
        try {
            carregaListaOfertados(produto.getIdProduto());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void carregaListaOfertados(String idProdutoBuscado) throws InterruptedException {

        spinner.setVisibility(ProgressBar.VISIBLE);

        Query mQuery = FirebaseDatabase.getInstance().getReference().child("ofertado");

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_offered);
        mOfertadoAdapter = new OfertadoAdapter(mQuery, Ofertado.class, mAdapterItems, mAdapterKeys);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mOfertadoAdapter);

        spinner.setVisibility(ProgressBar.GONE);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(ListOfferedActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {

                        //String ofertado = mOfertadoAdapter.getItem(position).getObjectId();

                        String ofertado = "KLAGiYmZDDzfzIGEF98";
                        Intent intent = new Intent(ListOfferedActivity.this, CartActivity.class);
                        intent.putExtra("ofertado", ofertado);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }
                )
        );
    }


    private synchronized void callConnection() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        try {
            googleApiClient.connect();
        } catch (Exception e) {
            Log.i("tagx", "Erro na geolocalização");
            Toast.makeText(getApplicationContext(), "Problemas na localização. Verifique seu GPS.", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onConnected(Bundle bundle) {

        String endereco;
        Location localizacao;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("tagx", "Falha no GPS");
        }
        localizacao = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        List<Address> local = null;

        try {
            local = geocoder.getFromLocation(localizacao.getLatitude(), localizacao.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (local != null && local.size() > 0)
            endereco = local.get(0).getAddressLine(0);
        else endereco = "Localização não encontrada";

        Log.i("tagx", "Latitude: " + localizacao.getLatitude() + " | Longitude: " + localizacao.getLongitude());
        Log.i("Tagx", endereco);

        //Intent intent = null;
        //intent.putExtra("endereco", "Lat: "+localizacao.getLatitude()+" | Long: "+localizacao.getLongitude()+" | "+endereco);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_drugstore, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.cart:
                intent = new Intent(ListOfferedActivity.this, CartActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_ADAPTER_ITEMS, Parcels.wrap(mOfertadoAdapter.getItems()));
        outState.putStringArrayList(SAVED_ADAPTER_KEYS, mOfertadoAdapter.getKeys());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mOfertadoAdapter.destroy();
    }

}
