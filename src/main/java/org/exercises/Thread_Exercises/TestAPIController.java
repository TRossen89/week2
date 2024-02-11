package org.exercises.Thread_Exercises;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.Callable;

public class TestAPIController implements Callable {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    String url;
    String api;

    public TestAPIController(String url, String api) {
        this.url = url;
        this.api = api;
    }

    public MegaDTO setMegaDTO(String res){
        MegaDTO megaDTO = gson.fromJson(res, MegaDTO.class);
        return megaDTO;
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

    @Override
    public String call() throws Exception {

        String apiInformation = getResponseBody(this.url);

        return apiInformation;
    }
}
