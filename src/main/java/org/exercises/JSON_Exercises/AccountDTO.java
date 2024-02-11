package org.exercises.JSON_Exercises;

public class AccountDTO {

    String id;
    String balance;
    Boolean isActive;

    @Override
    public String toString() {
        return "AccountDTO{" +
                "id='" + id + '\'' +
                ", balance='" + balance + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
