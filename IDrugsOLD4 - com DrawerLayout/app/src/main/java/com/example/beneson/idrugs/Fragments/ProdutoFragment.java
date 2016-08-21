package com.example.beneson.idrugs.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.beneson.idrugs.Activities.ListDrugstoreActivity;
import com.example.beneson.idrugs.Adapters.ProdutoAdapter;
import com.example.beneson.idrugs.Classes.Produto;
import com.example.beneson.idrugs.Others.RecyclerViewOnClickListenerHack;
import com.example.beneson.idrugs.R;

import java.util.List;

public class ProdutoFragment extends Fragment implements RecyclerViewOnClickListenerHack {

    private RecyclerView mRecyclerView;
    private List<Produto> listaRecebida;

    public void setListaRecebida(List<Produto> listaRecebida){
        this.listaRecebida = listaRecebida;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("tagx", "Hello Word");

        super.onCreateView(inflater, container, savedInstanceState);
        Log.i("tagx", "Hello Word 7");

        View view = inflater.inflate(R.layout.fragment_products, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                /*super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                ProdutoAdapter adapter = (ProdutoAdapter) mRecyclerView.getAdapter();

                if (listaRecebida.size() == llm.findFirstCompletelyVisibleItemPosition() + 1) {
                    //List<Ofertado> listAux = getOfertados(10);
                    List<Ofertado> listAux = null;

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), listaRecebida.size());
                    }
                }*/
            }
        });

        Log.i("tagx", "Hello Word 8");

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        ProdutoAdapter adapter = new ProdutoAdapter(getActivity(), listaRecebida);
        adapter.setmRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClickListener(View v, int position) {
        Toast.makeText(getActivity(), "Position: "+listaRecebida.get(position).getNomeComercial(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), ListDrugstoreActivity.class);
        intent.putExtra("idProduto", listaRecebida.get(position).getIdProduto());
        intent.putExtra("nomeComercial", listaRecebida.get(position).getNomeComercial());
        intent.putExtra("nomeQuimico", listaRecebida.get(position).getNomeQuimico());
        intent.putExtra("laboratorio", listaRecebida.get(position).getLaboratorio());
        intent.putExtra("registroMS", listaRecebida.get(position).getRegistroMS());
        intent.putExtra("pedeReceita", listaRecebida.get(position).isPedeReceita());
        intent.putExtra("categoria", listaRecebida.get(position).getCategoria());
        intent.putExtra("formaFisica", listaRecebida.get(position).getFormaFisica());

        startActivity(intent);
    }
}
