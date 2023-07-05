package dev.gamesapi.game;


import dev.gamesapi.game.gameHandler.GameRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService service;

    @Autowired
    public GameController(GameService gameService) {
        this.service = gameService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo
    ) {
        try {
            List<Game> games = service.getGames(pageNo);

            if ( games.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>( games, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<GameRes> getGameById(@PathVariable Long id) {
        try {
            Game game = service.getGameById(id);
            return new ResponseEntity<>(new GameRes("The game is found", game), HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new GameRes( e.getMessage() ), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    ResponseEntity<GameRes> addGame(@RequestBody Game game) {
        try {
            service.addGame(game);
            return new ResponseEntity<>(new GameRes("The game is added"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new GameRes( e.getMessage() ), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    ResponseEntity<GameRes> updateGame(@RequestBody Game game){
        try {
            service.updateGame(game);
            return new ResponseEntity<>(new GameRes("The game is updated"), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new GameRes( e.getMessage() ), HttpStatus.BAD_REQUEST);
        }
    }
}
