package com.example.beneson.idrugs.Classes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Beneson on 03/04/2016.
 */
public class Ofertado {

    private Produto produto;
    private Estabelecimento estabelecimento;
    private float preco;
    private float qtdApresentacao;

    public int getEstoqueOfertado() {
        return estoqueOfertado;
    }

    public void setEstoqueOfertado(int estoqueOfertado) {
        this.estoqueOfertado = estoqueOfertado;
    }

    private int estoqueOfertado;

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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
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
        result.put("estabelecimento", estabelecimento.getObjectId());
        result.put("produto", produto.getIdProduto());
        return result;
    }
}
