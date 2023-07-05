package dev.gamesapi.csv;

import dev.gamesapi.game.Game;
import dev.gamesapi.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class CSVService{
    @Autowired
    GameRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Game> games = CSVHelper.csvToGames( file.getInputStream() );
            repository.saveAll( games );
        } catch (IOException e) {
            throw new RuntimeException( "fail to store csv data: " + e.getMessage() );
        }
    }
}