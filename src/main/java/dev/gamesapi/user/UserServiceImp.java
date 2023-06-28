package dev.gamesapi.user;


import dev.gamesapi.DTOs.MessageResponse;
import dev.gamesapi.user.userInfo.UserInfoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> addUser(User user) {
        if ( userInfoRepository.findByUsername( user.getUsername() ).isPresent() ) {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST).body( "user already created" );

        }
        else {
            user.setPassword( passwordEncoder.encode( user.getPassword() ) );
            userInfoRepository.save( user );
            return ResponseEntity.status( HttpStatus.CREATED )
                    .body( new MessageResponse( "new user created" ) );
        }

    }

    @Override
    public ResponseEntity<?> updateUser(int id, User newUser) {
        Optional<User> optionalUser = userInfoRepository.findById( id );
        if ( optionalUser.isPresent() ) {
            User user = optionalUser.get();
            user.setEmail( newUser.getEmail() );
            user.setPassword( newUser.getPassword() );
            user.setUsername( newUser.getUsername() );
            userInfoRepository.save( user );
            return ResponseEntity.status( HttpStatus.OK )
                    .body( "user has been updated" );
        }
        return ResponseEntity.status( HttpStatus.NOT_FOUND )
                .body( "user not found" );
    }

    @Override
    public ResponseEntity<?> getUser(String username) {
        if (userInfoRepository.findByUsername( username ).isPresent()){
            return ResponseEntity.status( HttpStatus.FOUND).body( userInfoRepository.findByUsername( username ) );
        }
        return ResponseEntity.status( HttpStatus.NOT_FOUND).body( "user not found" );

    }


    public ResponseEntity<?> delete(Integer id) {
        Optional<User> user = userInfoRepository.findById( id );
        if ( user.isPresent() ) {
            userInfoRepository.deleteById( id );
            return ResponseEntity.status( HttpStatus.NO_CONTENT).body( "user has been deleted" );
        }
        return ResponseEntity.status( HttpStatus.NOT_FOUND )
                .body( "user not found" );

    }
}

