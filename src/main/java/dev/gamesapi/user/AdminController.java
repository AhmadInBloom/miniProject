package dev.gamesapi.user;

import dev.gamesapi.DTOs.AuthRequest;
import dev.gamesapi.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController{

    @Autowired
    private UserService service;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<?> addNewUser(@RequestBody User user) {
        user.setRoles( "ADMIN" );
        return service.addUser( user );
    }



    @PostMapping("/login")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        return getStringResponseEntity( authRequest, authenticationManager, jwtService );


    }

    static ResponseEntity<String> getStringResponseEntity(@RequestBody AuthRequest authRequest, AuthenticationManager authenticationManager, JwtService jwtService) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            System.out.println(authentication.getAuthorities());
            System.out.println(authRequest);
            return ResponseEntity.status( HttpStatus.OK).body("token: " + token );
        } else {
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED).body( "wrong info" );
        }
    }
}
