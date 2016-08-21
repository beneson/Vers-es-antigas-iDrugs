package com.example.beneson.idrugs.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.beneson.idrugs.R;

public class MainActivity extends AppCompatActivity{

    EditText nomeMedicamento;
    Button botaoPesquisa;
    String stringMedicamento;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) toolbar.setLogo(R.mipmap.ic_launcher);

        spinner = (ProgressBar) findViewById(R.id.progress_bar_main);

        nomeMedicamento = (EditText) findViewById(R.id.nome_medicamento);
        botaoPesquisa = (Button) findViewById(R.id.botao_pesquisa);

    }

    protected void pesquisar(View v){

        spinner.setVisibility(View.VISIBLE);

        stringMedicamento = nomeMedicamento.getText().toString();
        Intent intent = new Intent(MainActivity.this, ListProductActivity.class);
        intent.putExtra("medicamento", stringMedicamento);
        startActivity(intent);

        spinner.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
