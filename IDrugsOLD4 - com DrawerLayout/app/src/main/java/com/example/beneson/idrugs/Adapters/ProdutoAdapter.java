package com.example.beneson.idrugs.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.beneson.idrugs.Classes.Produto;
import com.example.beneson.idrugs.Others.RecyclerViewOnClickListenerHack;
import com.example.beneson.idrugs.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

/**
 * Created by Beneson on 06/06/2016.
 */
public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {
    private List<Produto> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public ProdutoAdapter(Context c, List<Produto> l) {
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Log.i("logx", "onCreateViewHolder()");
        View v = mLayoutInflater.inflate(R.layout.cell_product, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int position) {
        Log.i("logx", "onBindViewHolder()");
        myViewHolder.nomeComercialCelula.setText(mList.get(position).getNomeComercial());
        myViewHolder.laboratorioCelula.setText(mList.get(position).getLaboratorio());
        myViewHolder.nomeQuimicoCelula.setText(mList.get(position).getNomeQuimico());
        myViewHolder.categoria.setText(mList.get(position).getCategoria());
        myViewHolder.icFormaFarmaceutiva.setImageResource(R.mipmap.ic_tablet_48dp);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://project-1453308271285243406.appspot.com");
        StorageReference produtoRef= storageRef.child("produtos/"+mList.get(position).getRegistroMS()+".jpg");

        final long ONE_MEGABYTE = 270*187;
        produtoRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                myViewHolder.imgProduto.setImageBitmap(bitmap);
                myViewHolder.spinner.setVisibility(View.GONE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                myViewHolder.imgProduto.setImageResource(R.drawable.medicamento);
                myViewHolder.spinner.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setmRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        mRecyclerViewOnClickListenerHack = r;
    }

    public void addListItem(Produto produto, int position) {
        mList.add(produto);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imgProduto;
        public ImageView icFormaFarmaceutiva;
        public TextView nomeComercialCelula;
        public TextView laboratorioCelula;
        public TextView nomeQuimicoCelula;
        public TextView categoria;
        public ProgressBar spinner;

        public MyViewHolder(View itemView) {
            super(itemView);

            imgProduto = (ImageView) itemView.findViewById(R.id.img_produto);
            icFormaFarmaceutiva = (ImageView) itemView.findViewById(R.id.ic_forma_farmaceutica);
            nomeComercialCelula = (TextView) itemView.findViewById(R.id.nome_comercial);
            laboratorioCelula = (TextView) itemView.findViewById(R.id.laboratorio);
            nomeQuimicoCelula = (TextView) itemView.findViewById(R.id.nome_quimico);
            categoria = (TextView) itemView.findViewById(R.id.categoria);

            spinner = (ProgressBar) itemView.findViewById(R.id.progress_bar_cell_product);
            spinner.setVisibility(View.VISIBLE);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerViewOnClickListenerHack != null) {
                mRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());
            }


        }
    }
}
