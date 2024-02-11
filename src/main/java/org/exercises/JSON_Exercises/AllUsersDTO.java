package org.exercises.JSON_Exercises;

import java.util.List;

public class AllUsersDTO {

    public List<UserDTO> listOfAllUsers;


    public List<UserDTO> getUserDTOList() {
        return listOfAllUsers;
    }

    public void setUserDTOList(List<UserDTO> userDTOList) {
        this.listOfAllUsers = userDTOList;
    }
}
