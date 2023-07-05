package dev.gamesapi.game;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class UserGameKey implements Serializable{
        @Column(name = "user_id")
        Long userId;

        @Column(name = "game_id")
        Long gameId;

        // standard constructors, getters, and setters
        // hashcode and equals implementation
    }

