package com.example.beneson.idrugs;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ListDrugstoreActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener  {

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_drugstore);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_list_drugstore);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        try {
            callConnection();
        } catch (Exception e) {
            Log.i("tagx", "Erro na geolocalização");
            Toast.makeText(getApplicationContext(), "Problemas na localização. Verifique seu GPS.", Toast.LENGTH_SHORT).show();
        }

        Intent intent = getIntent();
        String idProdutoBuscado = intent.getStringExtra("idProduto");

        Log.i("tagx", "Pesquisandondo Farmácias: " + idProdutoBuscado);
        try {
            carregaListaOfertados(idProdutoBuscado);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void carregaListaOfertados(String idProdutoBuscado) throws InterruptedException {

        String whereClause = "formaFarmaceutica like '%"+""+"%'";
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
                            Log.i("tagx","Tamanho do resultadoOfertado: "+Integer.toString(resultado.size()));
                            DrugstoresFragment frag = (DrugstoresFragment) getSupportFragmentManager().findFragmentByTag("mainFragDrugstore");
                            if (frag == null) {
                                frag = new DrugstoresFragment(resultado);
                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.rl_fragment_container_drugstore, frag, "mainFragDrugstore");
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
                intent = new Intent(ListDrugstoreActivity.this, CartActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private synchronized void callConnection() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        try {
            googleApiClient.connect();
        }catch(Exception e){
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

}
