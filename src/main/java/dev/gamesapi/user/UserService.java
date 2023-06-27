package dev.gamesapi.user;

import org.springframework.http.ResponseEntity;


public interface UserService{

    //     adding a new user
    public ResponseEntity<?> addUser(User user);

    // update user
    public ResponseEntity<?> updateUser(int id, User newUser);

    public ResponseEntity<?> getUser(String username);



}
