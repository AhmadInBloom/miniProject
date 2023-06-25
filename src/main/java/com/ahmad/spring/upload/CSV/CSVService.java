package com.ahmad.spring.upload.CSV;

import java.io.IOException;
import java.util.List;

import com.ahmad.spring.upload.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ahmad.spring.upload.game.Game;

@Service
public class CSVService {
  @Autowired
  GameRepository repository;

  public void save(MultipartFile file) {
    try {
      List<Game> games = CSVHelper.csvToTutorials(file.getInputStream());
      repository.saveAll( games );
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

//  public ByteArrayInputStream load() {
//    List<Tutorial> tutorials = repository.findAll();
//
//    ByteArrayInputStream in = CSVHelper.tutorialsToCSV(tutorials);
//    return in;
//  }

//  public List<Game> getAllGames() {
//    return repository.findAll();
//  }
}
