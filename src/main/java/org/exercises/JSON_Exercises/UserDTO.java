package org.exercises.JSON_Exercises;

public class UserDTO {

    String firstName;
    String lastName;

    AccountDTO account;
    AddressDTO address;

    @Override
    public String toString() {
        return "UserDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", accountDTO=" + account +
                ", addressDTO=" + address +
                '}';
    }
}
