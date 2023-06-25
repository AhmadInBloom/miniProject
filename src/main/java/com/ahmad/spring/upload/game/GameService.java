package com.ahmad.spring.upload.game;

import org.springframework.stereotype.Service;

import java.util.List;

public interface GameService{

// Returns a list of all the games with a paging of size 50 and a page num var
List<Game> getAllGames(int pageNum);
}
