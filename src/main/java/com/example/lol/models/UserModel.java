package com.example.lol.models;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.example.lol.services.DBService;
import org.json.JSONObject;


public class UserModel {
    private int id;
    private String username;
    private String password;
    private boolean admin;

    public UserModel(int id, String username, String password, boolean admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public UserModel(JSONObject jsonUser) throws UserDoesNotExistException {
        if(DBService.checkUser(jsonUser.getString("username"), Integer.toString(jsonUser.getInt("id")),
                jsonUser.getBoolean("admin"), jsonUser.getString("password")) == DBService.NULL_ID)
            throw new UserDoesNotExistException();
        this.id = jsonUser.getInt("id");
        this.username = jsonUser.getString("username");
        this.password = jsonUser.getString("password");
        this.admin = jsonUser.getBoolean("admin");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    //this and the below method obtained from: https://www.geeksforgeeks.org/sha-256-hash-in-java/
    private static byte[] getBytes(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String hash(String input){
        byte [] hash;
        try {
             hash = getBytes(input);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64)
            hexString.insert(0, '0');

        return hexString.toString();
    }

    public String json(){
        JSONObject user = new JSONObject();
        user.put("id", id);
        user.put("username", username);
        user.put("password", password);
        user.put("admin", admin);
        return user.toString();
    }
}
