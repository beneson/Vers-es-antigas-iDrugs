package com.example.beneson.idrugs.Adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.beneson.idrugs.Classes.Ofertado;
import com.example.beneson.idrugs.R;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * Created by Beneson on 20/06/2016.
 */
public class OfertadoAdapter extends FirebaseRecyclerAdapter<OfertadoAdapter.ViewHolder, Ofertado> {


    public static class ViewHolder extends RecyclerView.ViewHolder {


        ProgressBar spinner;
        ImageView imgEstabelecimento;
        TextView nomeEstabelecimento;
        TextView precoReais;
        TextView precoCentavos;
        TextView tempoEntrega;
        TextView distanciaEntrega;


        public ViewHolder(View view) {

            // Map a Chat object to an entry in our listview

            super(view);
            spinner = (ProgressBar) view.findViewById(R.id.progress_bar_cell_offered);
            spinner.setVisibility(ProgressBar.VISIBLE);

            imgEstabelecimento = (ImageView) view.findViewById(R.id.img_estabelecimento);
            nomeEstabelecimento = (TextView) view.findViewById(R.id.nome_estabelecimento);
            precoReais = (TextView) view.findViewById(R.id.preco_reais);
            precoCentavos = (TextView) view.findViewById(R.id.preco_centavos);
            tempoEntrega = (TextView) view.findViewById(R.id.tempo_entrega);
            distanciaEntrega = (TextView) view.findViewById(R.id.distancia_entrega);
        }

    }

    public OfertadoAdapter(Query query, Class<Ofertado> itemClass, @Nullable ArrayList<Ofertado> items,
                          @Nullable ArrayList<String> keys) {
        super(query, itemClass, items, keys);
    }

    @Override
    public OfertadoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_drugstore, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OfertadoAdapter.ViewHolder holder, int position) {

        Ofertado item = getItem(position);
        //holder.nomeEstabelecimento.setText(item.getEstabelecimento().getNomeEstabelecimento());

        //holder.icFormaFarmaceutiva.setImageResource(R.mipmap.ic_tablet_48dp);

        /*
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://project-1453308271285243406.appspot.com");
        StorageReference produtoRef = storageRef.child("produtos/" + item.getRegistroMS() + ".jpg");

        final long ONE_MEGABYTE = 270 * 187;
        produtoRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Image img = new Image(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                holder.imgProduto.setImageBitmap(img.bitmap);
                holder.spinner.setVisibility(ProgressBar.GONE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                holder.imgProduto.setImageResource(R.drawable.medicamento);
                holder.spinner.setVisibility(ProgressBar.GONE);
            }
        });*/

    }

    @Override
    protected void itemAdded(Ofertado item, String key, int position) {
        Log.d("OfertadoAdapter", "Added a new item to the adapter.");
    }

    @Override
    protected void itemChanged(Ofertado oldItem, Ofertado newItem, String key, int position) {
        Log.d("OfertadoAdapter", "Changed an item.");
    }

    @Override
    protected void itemRemoved(Ofertado item, String key, int position) {
        Log.d("OfertadoAdapter", "Removed an item from the adapter.");
    }

    @Override
    protected void itemMoved(Ofertado item, String key, int oldPosition, int newPosition) {
        Log.d("OfertadoAdapter", "Moved an item.");
    }


}
