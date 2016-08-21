package com.example.beneson.idrugs.Classes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Beneson on 20/03/2016.
 */
public class Produto {

    private String nomeComercial;
    private String nomeQuimico;
    private String laboratorio;
    private String registroMS;
    private boolean pedeReceita = false;
    private String categoria;
    private String formaFisica;

    public String getFormaFisica() {
        return formaFisica;
    }

    public void setFormaFisica(String formaFisica) {
        this.formaFisica = formaFisica;
    }

    public String getApresentacao() {
        return apresentacao;
    }

    public void setApresentacao(String apresentacao) {
        this.apresentacao = apresentacao;
    }

    private String apresentacao;

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



    public Produto() {
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
        result.put("apresentacao", apresentacao);
        result.put("formaFisica", formaFisica);
        return result;
    }
}
