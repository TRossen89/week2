package org.exercises.Thread_Exercises;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.Callable;

public class APIController implements Callable {


    String url;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public APIController(String url) {
        this.url = url;
    }

    public ChuckNorrisJokeDTO getJoke(String url) {
        String res = getResponseBody(url);
        ChuckNorrisJokeDTO joke = gson.fromJson(res, ChuckNorrisJokeDTO.class);
        return joke;
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
            System.out.println(res);
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object call() throws Exception {

        ChuckNorrisJokeDTO apiInformation = getJoke(this.url);

        return apiInformation;
    }
}
