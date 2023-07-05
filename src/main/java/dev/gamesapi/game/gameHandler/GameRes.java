package dev.gamesapi.game.gameHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameRes{
    private String message;
    private Object items;

    public GameRes(String message) {
        this.message = message;
    }
}
