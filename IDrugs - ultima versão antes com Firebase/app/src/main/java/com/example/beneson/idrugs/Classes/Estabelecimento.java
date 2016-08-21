package com.example.beneson.idrugs.Classes;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Beneson on 03/04/2016.
 */
public class Estabelecimento extends ArrayList<Parcelable> implements Parcelable{

    private String nomeEstabelecimento;
    private String cnpj;
    private List<String> formasEntrega;
    private List<String> formasPagamento;
    private List<Proprietario> proprietarios;
    private float velocidadeMedia;
    private double geoLat;
    private double geoLon;
    private float avaliacao;
    private String telefone;

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

    public Estabelecimento() {
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = nomeEstabelecimento;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<String> getFormasEntrega() {
        return formasEntrega;
    }

    public void setFormasEntrega(List<String> formasEntrega) {
        this.formasEntrega = formasEntrega;
    }

    public List<String> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<String> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public List<Proprietario> getProprietarios() {
        return proprietarios;
    }

    public void setProprietarios(List<Proprietario> proprietarios) {
        this.proprietarios = proprietarios;
    }

    public float getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public void setVelocidadeMedia(float velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    public double getGeoLon() {
        return geoLon;
    }

    public void setGeoLon(double geoLon) {
        this.geoLon = geoLon;
    }

    public double getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(double geoLat) {
        this.geoLat = geoLat;
    }

    public float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(float avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("geoLat", geoLat);
        result.put("geoLon", geoLon);
        result.put("velocidadeMedia", velocidadeMedia);
        result.put("formasPagamento", formasPagamento);
        result.put("formasEntrega", formasEntrega);
        result.put("cnpj", cnpj);
        result.put("nomeEstabelecimento", nomeEstabelecimento);
        result.put("avaliacao", avaliacao);
        result.put("telefone", telefone);

        List<String> objectIds = null;
        for (int position=0; position<proprietarios.size(); position++){
            objectIds.add(proprietarios.get(position).getObjectId());
            result.put("proprietarios", objectIds);
        }
        
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        /*dest.writeString(nomeComercial);
        dest.writeString(nomeQuimico);
        dest.writeString(laboratorio);
        dest.writeString(registroMS);
        dest.writeString(categoria);
        dest.writeByte((byte) (pedeReceita ? 1 : 0));
        dest.writeString(formaFisica);*/
    }

    public Estabelecimento(android.os.Parcel parcel){
        /*this.nomeComercial = parcel.readString();
        this.nomeQuimico = parcel.readString();
        this.laboratorio = parcel.readString();
        this.registroMS = parcel.readString();
        this.categoria = parcel.readString();
        this.pedeReceita = parcel.readByte() != 0;
        this.formaFisica = parcel.readString();
    */}

    public static final Parcelable.Creator<Estabelecimento> CREATOR = new Parcelable.Creator<Estabelecimento>(){

        @Override
        public Estabelecimento createFromParcel(android.os.Parcel source) {
            return new Estabelecimento(source);
        }

        @Override
        public Estabelecimento[] newArray(int size) {
            return new Estabelecimento[size];
        }
    };
}
