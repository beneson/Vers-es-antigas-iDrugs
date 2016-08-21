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
public class ProdutoAdapter  extends FirebaseListAdapter<Produto> {

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
     * @param view A view instance corresponding to the layout we passed to the constructor.
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
