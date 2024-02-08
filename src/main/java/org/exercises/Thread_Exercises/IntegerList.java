package org.exercises.Thread_Exercises;

import java.util.ArrayList;
import java.util.List;

public class IntegerList {

    private static List<Integer> list = new ArrayList<>();
    public synchronized void addCount(int count) {
        list.add(count);
        System.out.println("Task: " + count + ": List size = " + list.size());
    }
}
