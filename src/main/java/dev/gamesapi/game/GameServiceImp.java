package dev.gamesapi.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GameServiceImp implements GameService{

    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImp(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> getGames(int pageNum) {
        Sort sort = Sort.by(Sort.Direction.DESC,"released");
        Pageable pageable = PageRequest.of(pageNum,50,sort);
        Page<Game> games =  gameRepository.findAll(pageable);
        return games.getContent();
    }

    @Override
    public Game getGameById(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent())
            return game.get();
        throw new NoSuchElementException("The game does not exist!");
    }

    @Override
    public void addGame(Game game) throws Exception {
        Optional<Game> _game = gameRepository.findById(game.getId());
        if (_game.isPresent())
            throw new Exception("The game named: \"" +_game.get().getName().toLowerCase()+ "\" does already exist!");
        gameRepository.save(game);
    }

    @Override
    public void updateGame(Game game) throws NoSuchElementException {
        Optional<Game> _game = gameRepository.findById(game.getId());
        if(_game.isPresent()){
            Game newGame = _game.get();
            newGame.setName(game.getName());
            newGame.setDevelopers(game.getDevelopers());
            newGame.setGenres(game.getGenres());
            newGame.setPlatforms(game.getPlatforms());
            newGame.setMetacritic(game.getMetacritic());
            newGame.setReleased(game.getReleased());
            newGame.setPlaytime(game.getPlaytime());
            gameRepository.save(newGame);
        }
        else
            throw new NoSuchElementException("game does not exist!");
    }
}
