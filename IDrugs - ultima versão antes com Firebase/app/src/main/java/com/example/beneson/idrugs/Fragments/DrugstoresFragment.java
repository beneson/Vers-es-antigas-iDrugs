package com.example.beneson.idrugs.Fragments;
/*

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beneson.idrugs.Adapters.OfertadoAdapter;
import com.example.beneson.idrugs.Classes.Ofertado;
import com.example.beneson.idrugs.R;

import java.util.List;

@SuppressLint("ValidFragment")
public class DrugstoresFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Ofertado> listaRecebida;

    @SuppressLint("ValidFragment")
    public DrugstoresFragment(List<Ofertado> listaRecebida){
        this.listaRecebida = listaRecebida;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drugstores, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_listDrugstore);
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
                }
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        OfertadoAdapter adapter = new OfertadoAdapter(getActivity(), listaRecebida);
        mRecyclerView.setAdapter(adapter);

        return view;
    }


}
*/