package com.ahmad.spring.upload.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class GameServiceImp implements GameService{

    @Autowired
    GameRepository gameRepository;
    @Override
    public List<Game> getAllGames(int pageNum) {
        Sort sort = Sort.by(Sort.Direction.DESC,"released");
        Pageable pageable = PageRequest.of(pageNum,50,sort);
        Page<Game> games =  gameRepository.findAll(pageable);
        return games.getContent();
    }
}
