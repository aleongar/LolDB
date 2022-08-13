package com.example.lol.services;

import com.example.lol.models.UserModel;
import org.json.JSONObject;
import java.io.*;

public class JSONService {
    public static final String DIR_PATH = "user/";
    public static final String FILENAME = "userdata.json";
    public static void createJSONFile(UserModel user){
        String json = user.json();
        try {
            File dir = new File(DIR_PATH);
            if(!dir.exists()){
                if(!dir.mkdirs()) //creates the directory, if for any reason this returns false, dir can't be created
                    throw new IOException("Dir can't be created");
                System.out.println("Creado");
            }
            FileWriter fw = new FileWriter(DIR_PATH + FILENAME);
            for (int i = 0; i < json.length(); i++)
                fw.append(json.charAt(i));
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static JSONObject getUserFromJSON(){
        try {
            FileReader fr = new FileReader(DIR_PATH + FILENAME);
            int jsonCharacter = fr.read();
            StringBuilder jsonText = new StringBuilder();
            while(jsonCharacter!=-1){
                jsonText.append((char)jsonCharacter);
                jsonCharacter = fr.read();
            }
            fr.close();
            return new JSONObject(jsonText.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File doesn't exist");
        } catch (IOException e) {
            throw new RuntimeException("File can't be red");
        }
    }

    public static void deleteJSONFile(){
        File json = new File(DIR_PATH + FILENAME);
        if(json.exists())
            json.delete();
    }
}
