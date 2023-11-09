package main.java.chattingSystem.entities.User;

import main.java.chattingSystem.entities.Password.Password;

import java.time.LocalDateTime;

public interface UserFactory {
    /**
     * @param username
     * @param userid
     * @param password
     */
    User create(String username, String userid, String password, LocalDateTime creationTime);
}