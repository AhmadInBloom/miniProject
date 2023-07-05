package dev.gamesapi.csv;

import dev.gamesapi.game.Game;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CSVHelper{
  public static String TYPE = "text/csv";

  public static boolean hasCSVFormat(MultipartFile file) {

    return TYPE.equals( file.getContentType() );
  }

  public static List<Game> csvToGames(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
         CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Game> games = new ArrayList<>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
        Date stringDate;
        if(csvRecord.isSet( "released" )){
          stringDate = new Date("00/00/0000");
        }else{
         stringDate =  new SimpleDateFormat("dd/MM/yyyy").parse(csvRecord.get( "released" ));
        }
        Game game = new Game(
              csvRecord.get("name"),
                Integer.parseInt(csvRecord.get( "metacritic" )),
                csvRecord.get( "developers" ),
                csvRecord.get("genres"),
                csvRecord.get( "platforms" ),
                stringDate,
                Integer.parseInt(csvRecord.get( "playtime" ))
            );
        games.add( game );
      }

      return games;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    } catch (ParseException e) {
      throw new RuntimeException( e.getCause()  + "  "+ e.getMessage());
    }
  }


}
