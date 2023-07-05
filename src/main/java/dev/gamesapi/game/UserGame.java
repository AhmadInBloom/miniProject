package dev.gamesapi.game;

import dev.gamesapi.user.User;
import jakarta.persistence.*;

@Entity
public class UserGame{


    @EmbeddedId
    UserGameKey id;

    @ManyToOne
    @MapsId( "userId" )
    @JoinColumn( name = "user_id" )
    User user;

    @ManyToOne
    @MapsId(  "gameId" )
    @JoinColumn( name =  "game_id" )
    Game game;

    String status;

    // standard constructors, getters, and setters
}

