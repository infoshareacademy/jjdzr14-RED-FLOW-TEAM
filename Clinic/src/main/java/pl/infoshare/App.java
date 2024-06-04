package pl.infoshare;

import org.json.simple.parser.ParseException;
import pl.infoshare.service.UserService;

import java.io.IOException;


public class App {
    public static void main(String[] args) throws IOException, ParseException {


        UserService.loginMenu();


    }


}