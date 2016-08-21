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
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.beneson.idrugs.Classes.Ofertado;
import com.example.beneson.idrugs.Classes.Produto;
import com.example.beneson.idrugs.Fragments.DrugstoresFragment;
import com.example.beneson.idrugs.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListDrugstoreActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private ProgressBar spinner;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_drugstore);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_list_drugstore);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        spinner = (ProgressBar) findViewById(R.id.progress_bar_list_drugstore);
        //spinner.setVisibility(View.VISIBLE);

        try {
            callConnection();
        } catch (Exception e) {
            Log.i("tagx", "Erro na geolocalização");
            Toast.makeText(getApplicationContext(), "Problemas na localização. Verifique seu GPS.", Toast.LENGTH_SHORT).show();
        }

        Intent intent = getIntent();
        Produto produtoBuscado = new Produto();
        produtoBuscado.setIdProduto(intent.getStringExtra("idProduto"));
        produtoBuscado.setNomeComercial(intent.getStringExtra("nomeComercial"));
        produtoBuscado.setNomeQuimico(intent.getStringExtra("nomeQuimico"));
        produtoBuscado.setLaboratorio(intent.getStringExtra("laboratorio"));
        produtoBuscado.setRegistroMS(intent.getStringExtra("registroMS"));
        produtoBuscado.setPedeReceita(intent.getBooleanExtra("pedeReceita", false));
        produtoBuscado.setCategoria(intent.getStringExtra("categoria"));
        produtoBuscado.setFormaFisica(intent.getStringExtra("formaFisica"));

        Log.i("tagx", "Pesquisandondo Farmácias: produto " + produtoBuscado.getIdProduto());
        try {
            carregaListaOfertados(produtoBuscado.getIdProduto());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void carregaListaOfertados(String idProdutoBuscado) throws InterruptedException {

        DrugstoresFragment frag = (DrugstoresFragment) getSupportFragmentManager().findFragmentByTag("mainFragDrugstore");
        if (frag == null) {
            List<Ofertado> aux = new ArrayList<>();
            frag = new DrugstoresFragment(aux);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container_drugstore, frag, "mainFragDrugstore");
            ft.commit();
            spinner.setVisibility(View.GONE);
        }
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

}
