package com.example.beneson.idrugs.Adapters;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.beneson.idrugs.Classes.Produto;
import com.example.beneson.idrugs.R;
import com.google.firebase.database.Query;

/**
 * Created by Beneson on 06/06/2016.
 */
public class ProdutoAdapter extends FirebaseListAdapter<Produto> {

    // The mUsername for this client. We use this to indicate which messages originated from this user
    private String mUsername;

    public ProdutoAdapter(Query ref, Activity activity, int layout, String mUsername) {
        super(ref, Produto.class, layout, activity);
        this.mUsername = mUsername;
    }

    /**
     * Bind an instance of the <code>Chat</code> class to our view. This method is called by <code>FirebaseListAdapter</code>
     * when there is a data change, and we are given an instance of a View that corresponds to the layout that we passed
     * to the constructor, as well as a single <code>Chat</code> instance that represents the current data to bind.
     *
     * @param view    A view instance corresponding to the layout we passed to the constructor.
     * @param produto An instance representing the current state of a chat message
     */
    @Override
    protected void populateView(View view, Produto produto) {
        // Map a Chat object to an entry in our listview
        TextView nomeComercial = (TextView) view.findViewById(R.id.nome_comercial);
        nomeComercial.setText(produto.getNomeComercial());
        // If the message was sent by this user, color it differently
    }
}

/*
myViewHolder.nomeComercialCelula.setText(mList.get(position).getNomeComercial());
        myViewHolder.laboratorioCelula.setText(mList.get(position).getLaboratorio());
        myViewHolder.nomeQuimicoCelula.setText(mList.get(position).getNomeQuimico());
        myViewHolder.categoria.setText(mList.get(position).getCategoria());
        myViewHolder.icFormaFarmaceutiva.setImageResource(R.mipmap.ic_tablet_48dp);

        FirebaseStorage storage=FirebaseStorage.getInstance();
        StorageReference storageRef=storage.getReferenceFromUrl("gs://project-1453308271285243406.appspot.com");
        StorageReference produtoRef=storageRef.child("produtos/"+mList.get(position).getRegistroMS()+".jpg");

final long ONE_MEGABYTE=270*187;
        produtoRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>(){
@Override
public void onSuccess(byte[]bytes){
        Bitmap bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        myViewHolder.imgProduto.setImageBitmap(bitmap);
        myViewHolder.spinner.setVisibility(View.GONE);
        }
        }).addOnFailureListener(new OnFailureListener(){
@Override
public void onFailure(@NonNull Exception e){
        myViewHolder.imgProduto.setImageResource(R.drawable.medicamento);
        myViewHolder.spinner.setVisibility(View.GONE);
        }
        });
        }


public ImageView imgProduto;
public ImageView icFormaFarmaceutiva;
public TextView nomeComercialCelula;
public TextView laboratorioCelula;
public TextView nomeQuimicoCelula;
public TextView categoria;
public ProgressBar spinner;
*/
/*
        imgProduto=(ImageView)itemView.findViewById(R.id.img_produto);
        icFormaFarmaceutiva=(ImageView)itemView.findViewById(R.id.ic_forma_farmaceutica);
        nomeComercialCelula=(TextView)itemView.findViewById(R.id.nome_comercial);
        laboratorioCelula=(TextView)itemView.findViewById(R.id.laboratorio);
        nomeQuimicoCelula=(TextView)itemView.findViewById(R.id.nome_quimico);
        categoria=(TextView)itemView.findViewById(R.id.categoria);

        spinner=(ProgressBar)itemView.findViewById(R.id.progress_bar_cell_product);
        spinner.setVisibility(View.VISIBLE);

*/