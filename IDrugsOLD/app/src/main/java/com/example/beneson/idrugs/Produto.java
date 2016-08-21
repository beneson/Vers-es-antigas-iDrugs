package com.example.beneson.idrugs;

import java.util.Date;

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
}
