package com.example.beneson.idrugs.Classes;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Beneson on 03/04/2016.
 */
public class Usuario extends ArrayList<Parcelable> implements Parcelable{

    private Users user;
    private double userLat;
    private double userLon;

    public Usuario() {
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public double getUserLat() {
        return userLat;
    }

    public void setUserLat(double userLat) {
        this.userLat = userLat;
    }

    public double getUserLon() {
        return userLon;
    }

    public void setUserLon(double userLon) {
        this.userLon = userLon;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("userLon", userLon);
        result.put("userLat", userLat);
        result.put("user", user.getObjectId());
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(user);
        dest.writeDouble(userLat);
        dest.writeDouble(userLon);
    }

    public Usuario(android.os.Parcel parcel){
        this.user = (Users) parcel.readValue(Users.class.getClassLoader());
        this.userLat = parcel.readDouble();
        this.userLon = parcel.readDouble();
    }

    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>(){

        @Override
        public Usuario createFromParcel(android.os.Parcel source) {
            return new Usuario(source);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
