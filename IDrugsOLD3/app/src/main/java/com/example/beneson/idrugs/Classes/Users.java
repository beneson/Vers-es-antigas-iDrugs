package com.example.beneson.idrugs.Classes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Beneson on 09/04/2016.
 */
public class Users {

    private String email;
    private String name;
    private String password;
    private String socialAccount;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSocialAccount() {
        return socialAccount;
    }

    public void setSocialAccount(String socialAccount) {
        this.socialAccount = socialAccount;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("socialAccount", socialAccount);
        result.put("password", password);
        result.put("name", name);
        result.put("email", email);
        return result;
    }
}
