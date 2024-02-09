package org.exercises.Thread_Exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ThreadExercises {

    /* SPØRGSMÅL:

        1) Kan jeg sørge for at computeren rent faktisk bruger de fysiske threads i computerens CPU?

    */
    public static void main(String[] args) {


        // ############### Exercise 1 #######################################################################

        List<String> alphabetList = new ArrayList<>();

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            alphabetList.add(String.valueOf(ch));
        }


        int timeBeforeKillingUnusedThread = 3;

        ExecutorService executorService = Executors.newFixedThreadPool(4);


        int cores = 4;
        int coreMax = 4;

        ExecutorService executorService1 =
                new ThreadPoolExecutor(
                        cores,
                        coreMax,
                        timeBeforeKillingUnusedThread,
                        TimeUnit.MILLISECONDS,
                        new ArrayBlockingQueue<>(200)
                        );

        //ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        //ExecutorService executorService3 = Executors.newCachedThreadPool();

        System.out.println("Creating tasks for executorService...");


        for (String s : alphabetList) {
            System.out.println("Submitting task for executorService");
            /* executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(s + s + s);
                }
            });

             */

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(s + s + s);
                }
            });
        }


        System.out.println("Waiting for executorService to finish...");
        executorService.shutdown();
        System.out.println("Done");


        // ############### Exercise 2 ##############################################################################

        // The Counter class presented is already thread safe: The increment method is synchronized


        // ############### Exercise 3 #############################################################################

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








        // ############### Exercise 4 ##########################################################################

        // Trying to put all my computer's core to work (see also the class CallableTask - this task, and the for
        // loop here in main with 9000000 loops of creating Futures, makes the computer work)

        //ExecutorService executorService5 = Executors.newFixedThreadPool(999999999);
        ExecutorService executorService5 = Executors.newCachedThreadPool();

        List<Future<Double>> futureList = new ArrayList<>();

        System.out.println("Second for loop starts...");

        for (int count = 0; count < 9000000; count++) {

            Double countToDouble = Integer.valueOf(count).doubleValue();

            Future future = executorService5.submit(new CallableTask(countToDouble));
            futureList.add(future);

        }

        for (Future<Double> fut : futureList) {
            try {
                System.out.println(fut.isDone());
                System.out.println("Count multiplied: " + fut.get());
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }


        System.out.println("Main is done");
        executorService5.shutdown();
        //executorService6.shutdown();


    }
}
