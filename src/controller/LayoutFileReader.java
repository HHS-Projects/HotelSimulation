package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LayoutFileReader {

    public static Object readFile(String layoutFile) {

        try {

            String path = "resources/layout/";
            FileReader fileReader = new FileReader(path + layoutFile);

            JSONParser parser = new JSONParser();
            Object object = parser.parse(fileReader);

            return object;

        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}



