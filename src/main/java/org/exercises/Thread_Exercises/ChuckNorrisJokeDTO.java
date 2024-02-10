package org.exercises.Thread_Exercises;

import java.util.concurrent.Callable;

public class ChuckNorrisJokeDTO implements APIInformation{


    private String value;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public void printAPIInfo() {
        System.out.println(value);
    }
}
