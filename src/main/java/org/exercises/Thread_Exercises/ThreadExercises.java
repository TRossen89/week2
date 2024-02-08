package org.exercises.Thread_Exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExercises {

    public static void main(String[] args) {

        // ############### Exercise 1 ################

        List<String> alphabetList = new ArrayList<>();

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            alphabetList.add(String.valueOf(ch));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        System.out.println( "Creating tasks for executorService..." );

        for (String s : alphabetList ) {
            System.out.println("Submitting task for executorService");
            executorService.submit( new Runnable() {
                @Override
                public void run() {
                    System.out.println(s+s+s);
                }
            } );
        }
        System.out.println("Waiting for executorService to finish...");
        executorService.shutdown();
        System.out.println("Done");
    }
}
