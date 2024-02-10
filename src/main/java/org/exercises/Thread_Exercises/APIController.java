package org.exercises.Thread_Exercises;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.Callable;

public class APIController implements Callable {



    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    String url;
    String api;

    public APIController(String url, String api) {
        this.url = url;
        this.api = api;
    }



    public APIInformation getAPIInformation(String url, String info) {

        String res = getResponseBody(url);

        switch (info){
            case "dad_joke":
                DadJokeDTO dadJoke = gson.fromJson(res, DadJokeDTO.class);
                return dadJoke;

            case "chuck_norris":
                ChuckNorrisJokeDTO chuckNorrisJokeDTO = gson.fromJson(res, ChuckNorrisJokeDTO.class);
                return chuckNorrisJokeDTO;
        }

        return null;
    }

    public String getResponseBody(String url) {
        // Using OkHttp: Can sometime cause program to hang. Then use Apache HttpClient instead.
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .method("GET", null)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String res = response.body().string();
            //System.out.println(res);
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public APIInformation call() throws Exception {

        APIInformation apiInformation = getAPIInformation(this.url, this.api);

        return apiInformation;
    }

}
