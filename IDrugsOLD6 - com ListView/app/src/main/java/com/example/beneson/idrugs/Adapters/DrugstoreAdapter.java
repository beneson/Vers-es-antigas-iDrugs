package com.example.beneson.idrugs.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beneson.idrugs.Classes.Ofertado;
import com.example.beneson.idrugs.R;

import java.util.List;

/**
 * Created by Beneson on 20/06/2016.
 */
public class DrugstoreAdapter extends RecyclerView.Adapter<DrugstoreAdapter.MyViewHolder> {
    private List<Ofertado> mList;
    private LayoutInflater mLayoutInflater;
    //private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public DrugstoreAdapter(Context c, List<Ofertado> l){
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Log.i("logx", "onCreateViewHolder()");
        View v = mLayoutInflater.inflate(R.layout.cell_drugstore, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        Log.i("logx", "onBindViewHolder()");
        //myViewHolder.ivOfertado.setImageResource( mList.get(position).getPhoto() );
        //myViewHolder.estabelecimento.setText(mList.get(position).getEstabelecimento().getNomeEstabelecimento());
        //myViewHolder.precoReais.setText(Float.toString(mList.get(position).getPreco()));
        //myViewHolder.tempoEntrega.setText(mList.get(position));
        //myViewHolder.distanciaEntrega.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void addListItem(Ofertado ofertado, int position){
        mList.add(ofertado);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView estabelecimento;
        public TextView precoReais;
        public TextView precoCentavos;
        public TextView tempoEntrega;
        public TextView distanciaEntrega;

        public MyViewHolder(View itemView) {
            super(itemView);

            estabelecimento = (TextView) itemView.findViewById(R.id.estabelecimento);
            precoReais = (TextView) itemView.findViewById(R.id.preco_reais);
            tempoEntrega = (TextView) itemView.findViewById(R.id.tempo_entrega);
            distanciaEntrega = (TextView) itemView.findViewById(R.id.distancia_entrega);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
