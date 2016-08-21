package com.example.beneson.idrugs.Classes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Beneson on 03/04/2016.
 */
public class Proprietario extends Usuario {

    private String documentacao;
    private Users user;

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


    public Proprietario() {
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getDocumentacao() {
        return documentacao;
    }

    public void setDocumentacao(String documentacao) {
        this.documentacao = documentacao;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("user", user.getObjectId());
        result.put("documentacao", documentacao);
        return result;
    }
}
