package dev.gamesapi.csv;

import dev.gamesapi.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("admin/csv/")
public class CSVController{

  @Autowired
  CSVService fileService;
  @Autowired
  GameService gameService;
  @PostMapping("upload")
  public ResponseEntity<?> uploadFile(@RequestBody MultipartFile file) {


    if (CSVHelper.hasCSVFormat(file)) {
      try {
        fileService.save(file);
        return ResponseEntity.status(HttpStatus.OK).body("Uploaded the file successfully: " + file.getOriginalFilename());
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload the file: " + file.getOriginalFilename() + "!\n And the exception is " + e.getMessage());
      }
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload a csv file!");
  }




}
