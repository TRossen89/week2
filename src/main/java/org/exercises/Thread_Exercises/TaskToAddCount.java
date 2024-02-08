package org.exercises.Thread_Exercises;

public class TaskToAddCount implements Runnable{
    private IntegerList integerList;
    private int count;

    TaskToAddCount(IntegerList integerList, int count) {
        this.integerList = integerList;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((int) Math.random()*800+200);
            integerList.addCount(count);
        } catch (InterruptedException ex) {
            System.out.println("Thread was interrupted");
        }
    }
}

