package dev.gamesapi.game;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer metacritic;
    private String developers;
    private String genres;
    private String platforms;
    private Date released;
    private Integer playtime;


    @OneToMany(mappedBy = "game")
    private Set<UserGame> Status;

    public Game(String name, Integer metacritic, String developers, String genres, String platforms, Date released, Integer playtime) {
        this.name = name;
        this.metacritic = metacritic;
        this.developers = developers;
        this.genres = genres;
        this.platforms = platforms;
        this.released = released;
        this.playtime = playtime;
    }
}
