package com.example.beneson.idrugs.Classes;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Beneson on 09/04/2016.
 */
public class Pedidos {

    private Usuario usuario;
    private List<Pedido> pedidos;
    private String formaPagamento;
    private String formaEntrega;
    private boolean foiEntregue;
    private Time tempoEntrega;
    private double enderecoEntregaLon;
    private double enderecoEntregaLat;


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

    public Pedidos() {
    }

    public boolean isFoiEntregue() {
        return foiEntregue;
    }

    public void setFoiEntregue(boolean foiEntregue) {
        this.foiEntregue = foiEntregue;
    }

    public String getFormaEntrega() {
        return formaEntrega;
    }

    public void setFormaEntrega(String formaEntrega) {
        this.formaEntrega = formaEntrega;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Time getTempoEntrega() {
        return tempoEntrega;
    }

    public void setTempoEntrega(Time tempoEntrega) {
        this.tempoEntrega = tempoEntrega;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getEnderecoEntregaLat() {
        return enderecoEntregaLat;
    }

    public void setEnderecoEntregaLat(double enderecoEntregaLat) {
        this.enderecoEntregaLat = enderecoEntregaLat;
    }

    public double getEnderecoEntregaLon() {
        return enderecoEntregaLon;
    }

    public void setEnderecoEntregaLon(double enderecoEntregaLon) {
        this.enderecoEntregaLon = enderecoEntregaLon;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("usuario", usuario.getObjectId());
        result.put("tempoEntrega", tempoEntrega);

        result.put("formaPagamento", formaPagamento);
        result.put("formaEntrega", formaEntrega);
        result.put("foiEntregue", foiEntregue);
        result.put("enderecoEntregaLon", enderecoEntregaLon);
        result.put("enderecoEntregaLat", enderecoEntregaLat);

        List<String> objectIds = null;
        for (int position=0; position<pedidos.size(); position++){
            objectIds.add(pedidos.get(position).getObjectId());
            result.put("pedidos", objectIds);
        }

        return result;
    }
}
