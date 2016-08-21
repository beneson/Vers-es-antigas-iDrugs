package com.example.beneson.idrugs.Classes;

import android.os.Parcelable;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Beneson on 20/03/2016.
 */

@Parcel
public class Produto extends ArrayList<Parcelable> implements Parcelable{

    private String idProduto;
    private String nomeComercial;
    private String nomeQuimico;
    private String laboratorio;
    private String registroMS;
    private boolean pedeReceita = false;
    private String categoria;
    private String formaFisica;


    public Produto() {
    }

    public String getFormaFisica() {
        return formaFisica;
    }

    public void setFormaFisica(String formaFisica) {
        this.formaFisica = formaFisica;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto){
        this.idProduto = idProduto;
    }

    private Date created;
    public Date getCreated(){
        return created;
    }

    private Date updated;
    public Date getUpdated(){
        return updated;
    }

    public String getNomeComercial() {
        return nomeComercial;
    }

    public void setNomeComercial(String nomeComercial) {
        this.nomeComercial = nomeComercial;
    }

    public String getNomeQuimico() {
        return nomeQuimico;
    }

    public void setNomeQuimico(String nomeQuimico) {
        this.nomeQuimico = nomeQuimico;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getRegistroMS() {
        return registroMS;
    }

    public void setRegistroMS(String registroMS) {
        this.registroMS = registroMS;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isPedeReceita() {
        return pedeReceita;
    }

    public void setPedeReceita(boolean pedeReceita) {
        this.pedeReceita = pedeReceita;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("nomeComercial", nomeComercial);
        result.put("nomeQuimico", nomeQuimico);
        result.put("laboratorio", laboratorio);
        result.put("registroMS", registroMS);
        result.put("categoria", categoria);
        result.put("pedeReceita", pedeReceita);
        result.put("formaFisica", formaFisica);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(nomeComercial);
        dest.writeString(nomeQuimico);
        dest.writeString(laboratorio);
        dest.writeString(registroMS);
        dest.writeString(categoria);
        dest.writeByte((byte) (pedeReceita ? 1 : 0));
        dest.writeString(formaFisica);
    }

    public Produto(android.os.Parcel parcel){
        this.nomeComercial = parcel.readString();
        this.nomeQuimico = parcel.readString();
        this.laboratorio = parcel.readString();
        this.registroMS = parcel.readString();
        this.categoria = parcel.readString();
        this.pedeReceita = parcel.readByte() != 0;
        this.formaFisica = parcel.readString();
    }

    public static final Parcelable.Creator<Produto> CREATOR = new Parcelable.Creator<Produto>(){

        @Override
        public Produto createFromParcel(android.os.Parcel source) {
            return new Produto(source);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };
}
