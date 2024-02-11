package org.exercises.JSON_Exercises;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class APIController {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public AllUsersDTO allUsersToDTO(String file){

        AllUsersDTO allUsersDTO = new AllUsersDTO();
        try{
            JsonReader reader = new JsonReader(new FileReader(file));
            allUsersDTO = gson.fromJson(reader, AllUsersDTO.class);

        }catch (IOException e){
            System.out.println(e);
        }

        return allUsersDTO;
    }


}
