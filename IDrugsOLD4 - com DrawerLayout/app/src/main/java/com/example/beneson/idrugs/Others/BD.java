package com.example.beneson.idrugs.Others;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.beneson.idrugs.Classes.Ofertado;

public class BD {
    private SQLiteDatabase bd;

    public BD(Context context) {
        BDCore auxBd = new BDCore(context);
        this.bd = auxBd.getWritableDatabase();
    }

    public void inserir(Ofertado ofertado) {

        ContentValues valoresOfertado = new ContentValues();

        valoresOfertado.put("_idOfertado", ofertado.getObjectId());
        //valoresOfertado.put("idProduto", ofertado.getProduto().getIdProduto());
        valoresOfertado.put("idEstabelecimento", ofertado.getEstabelecimento().getObjectId());
        valoresOfertado.put("preco", ofertado.getPreco());
        valoresOfertado.put("qtdApresentacao", ofertado.getQtdApresentacao());
        this.bd.insert("ofertado", null, valoresOfertado);

        ContentValues valoresProduto = new ContentValues();
        valoresProduto.put("_idProduto", ofertado.getProduto().getIdProduto());
        valoresProduto.put("nomeComercial", ofertado.getProduto().getNomeComercial());
        valoresProduto.put("nomeQuimico", ofertado.getProduto().getNomeQuimico());
        valoresProduto.put("laboratorio", ofertado.getProduto().getLaboratorio());
        valoresProduto.put("registroMS", ofertado.getProduto().getRegistroMS());
        //valoresProduto.put("pedeReceita", ofertado.getProduto().isPedeReceita());
        valoresProduto.put("categoria", ofertado.getProduto().getCategoria());
        this.bd.insert("produto", null, valoresProduto);

        ContentValues valoresEstabelecimento = new ContentValues();
        valoresEstabelecimento.put("idEstabelecimento", ofertado.getEstabelecimento().getObjectId());
        valoresEstabelecimento.put("nomeEstabelecimento", ofertado.getEstabelecimento().getNomeEstabelecimento());
        valoresEstabelecimento.put("cnpj", ofertado.getEstabelecimento().getCnpj());
        ContentValues valoresFormasEntrega = new ContentValues();
        for(int i = 0; i < ofertado.getEstabelecimento().getFormasEntrega().size();i++){
            valoresFormasEntrega.put("idEstabelecimento", ofertado.getEstabelecimento().getObjectId());
            valoresFormasEntrega.put("formaEntrega", ofertado.getEstabelecimento().getFormasEntrega().get(i));
            this.bd.insert("formasEntrega", null, valoresFormasEntrega);
            valoresFormasEntrega.clear();
        }
        ContentValues valoresFormasPagamento = new ContentValues();
        for(int i = 0; i < ofertado.getEstabelecimento().getFormasPagamento().size();i++){
            valoresFormasPagamento.put("idEstabelecimento", ofertado.getEstabelecimento().getObjectId());
            valoresFormasPagamento.put("formaPagamento", ofertado.getEstabelecimento().getFormasPagamento().get(i));
            this.bd.insert("formasPagamento", null, valoresFormasPagamento);
            valoresFormasPagamento.clear();
        }
        this.bd.insert("estabelecimento", null, valoresEstabelecimento);

        valoresOfertado.clear();
        valoresProduto.clear();
        valoresEstabelecimento.clear();


    }


}
