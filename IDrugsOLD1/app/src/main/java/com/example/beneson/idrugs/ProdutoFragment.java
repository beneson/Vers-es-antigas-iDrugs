package com.example.beneson.idrugs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

@SuppressLint("ValidFragment")
public class ProdutoFragment extends Fragment implements RecyclerViewOnClickListenerHack{

    private RecyclerView mRecyclerView;
    private List<Produto> listaRecebida;
    
    @SuppressLint("ValidFragment")
    public ProdutoFragment(List<Produto> listaRecebida){
        this.listaRecebida = listaRecebida;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
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
        Toast.makeText(getActivity(), "Position: "+position, Toast.LENGTH_SHORT).show();

        String idProduto = listaRecebida.get(position).getObjectId();

        Intent intent = new Intent(getActivity(), ListDrugstoreActivity.class);
        intent.putExtra("idProduto", idProduto);
        startActivity(intent);
    }
}
