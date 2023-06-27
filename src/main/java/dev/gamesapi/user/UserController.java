package dev.gamesapi.user;


import dev.gamesapi.DTOs.AuthRequest;
import dev.gamesapi.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static dev.gamesapi.user.AdminController.getStringResponseEntity;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService service;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<?> addNewUser(@RequestBody User user) {
        user.setRoles( "USER" );
        return service.addUser( user );
    }



    @PostMapping("/login")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        return getStringResponseEntity( authRequest, authenticationManager, jwtService );

    }



    //TODO:








}
