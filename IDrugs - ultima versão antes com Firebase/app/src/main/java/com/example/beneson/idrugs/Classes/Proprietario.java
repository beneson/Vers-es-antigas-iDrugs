package com.example.beneson.idrugs.Classes;

import android.os.Parcelable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Beneson on 03/04/2016.
 */
public class Proprietario extends Usuario implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(documentacao);
        dest.writeValue(user);
    }

    public Proprietario(android.os.Parcel parcel){
        this.documentacao = parcel.readString();
        this.user = (Users) parcel.readValue(Users.class.getClassLoader());
    }

    public static final Parcelable.Creator<Proprietario> CREATOR = new Parcelable.Creator<Proprietario>(){

        @Override
        public Proprietario createFromParcel(android.os.Parcel source) {
            return new Proprietario(source);
        }

        @Override
        public Proprietario[] newArray(int size) {
            return new Proprietario[size];
        }
    };
}
