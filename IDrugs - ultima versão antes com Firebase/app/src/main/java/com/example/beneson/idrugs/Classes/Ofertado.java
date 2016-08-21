package com.example.beneson.idrugs.Classes;

import android.os.Parcelable;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Beneson on 03/04/2016.
 */

@Parcel
public class Ofertado extends ArrayList<Parcelable> implements Parcelable{

    private String produto;
    private String estabelecimento;
    private float preco;
    private float qtdApresentacao;
    private int estoqueOfertado;


    public int getEstoqueOfertado() {
        return estoqueOfertado;
    }

    public void setEstoqueOfertado(int estoqueOfertado) {
        this.estoqueOfertado = estoqueOfertado;
    }

    private String objectId;
    public String getObjectId() {
        return objectId;
    }

    private Date created;
    public Date getCreated(){
        return created;
    }

    private Date updated;
    public Date getUpdated(){
        return updated;
    }

    public Ofertado() {
    }

    public String getString() {
        return produto;
    }

    public void setString(String produto) {
        this.produto = produto;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
    public float getQtdApresentacao() {
        return qtdApresentacao;
    }

    public void setQtdApresentacao(float qtdApresentacao) {
        this.qtdApresentacao = qtdApresentacao;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("qtdApresentacao", qtdApresentacao);
        result.put("preco", preco);
        result.put("estabelecimento", estabelecimento);
        result.put("produto", produto);
        result.put("estoqueOfertado", estoqueOfertado);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(produto);
        dest.writeString(estabelecimento);
        dest.writeFloat(preco);
        dest.writeFloat(qtdApresentacao);
        dest.writeInt(estoqueOfertado);
    }

    public Ofertado(android.os.Parcel parcel){
        this.produto = parcel.readString();
        this.estabelecimento = parcel.readString();
        this.preco = parcel.readFloat();
        this.qtdApresentacao = parcel.readFloat();
        this.estoqueOfertado = parcel.readInt();
    }

    public static final Parcelable.Creator<Ofertado> CREATOR = new Parcelable.Creator<Ofertado>(){

        @Override
        public Ofertado createFromParcel(android.os.Parcel source) {
            return new Ofertado(source);
        }

        @Override
        public Ofertado[] newArray(int size) {
            return new Ofertado[size];
        }
    };

}
