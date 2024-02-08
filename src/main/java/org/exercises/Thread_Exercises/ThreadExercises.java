package org.exercises.Thread_Exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(s+s+s);
                }
            }
            );
        }
        System.out.println("Waiting for executorService to finish...");
        executorService.shutdown();
        System.out.println("Done");


        // ############### Exercise 2 ################

        // The Counter class presented is already thread safe because the increment method is synchronized


        // ############### Exercise 3 ################

        // * First solution: Using synchronized in IntegerList
        ExecutorService workingJack = Executors.newFixedThreadPool(17);
        System.out.println("Main starts");
        IntegerList integerList = new IntegerList();
        for (int count = 0; count < 1000; count++) {
            workingJack.submit(new TaskToAddCount(integerList, count));
        }
        System.out.println("Main is done");
        workingJack.shutdown();

        // * Second solution: Change the Executors.newFixedThreadPool parameter to 1 so only 1 working thread is
        // created in the ExecutorService.

    }
}
