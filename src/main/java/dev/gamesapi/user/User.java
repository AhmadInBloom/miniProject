package dev.gamesapi.user;


import dev.gamesapi.game.UserGame;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userTable")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 
    @Column( nullable = false, unique = true )
    private String username;
    private String email;
    @Column( nullable = false, unique = true )
    private String password;
    @Column( nullable = false )
    private String roles;

    @OneToMany(mappedBy = "user")
    private Set<UserGame> Status;
}
