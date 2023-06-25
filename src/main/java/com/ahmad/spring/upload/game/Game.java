package com.ahmad.spring.upload.game;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "game")
public class Game{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "metacritic")
  private Integer metacritic;

  @Column(name = "released")
  private String released;

  @Column(name ="playtime")
  private Integer playtime;

  @Column(name ="platforms")
  private String platforms;

  @Column(name ="developers")
  private String developers;

  public Game() {

  }

  public Game( String name, Integer metacritic, String released, Integer playtime, String platforms, String developers) {
    this.name = name;
    this.metacritic = metacritic;
    this.released = released;
    this.playtime = playtime;
    this.platforms = platforms;
    this.developers = developers;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


}
