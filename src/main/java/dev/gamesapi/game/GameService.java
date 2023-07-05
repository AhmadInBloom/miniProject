package dev.gamesapi.game;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface GameService {

    // Returns a list of all the games with a paging of size 50 and a page num var
    List<Game> getGames(int pageNum);
    public Game getGameById(@PathVariable Long id);
    public void addGame(@RequestBody Game game) throws Exception;
    public void updateGame(@RequestBody Game game);
}
