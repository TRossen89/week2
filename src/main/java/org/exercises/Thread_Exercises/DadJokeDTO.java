package org.exercises.Thread_Exercises;

public class DadJokeDTO implements APIInformation {


    private String id;
    private String joke;
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void printAPIInfo() {
        System.out.println(joke);
    }
}
