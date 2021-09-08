package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private static final String SOURCE_DIR = "src/main/resources/";
    public static final String SHIPS = SOURCE_DIR + "shipnames.txt";
    public static final String PIRATES = SOURCE_DIR + "piratenames.txt";

    public static void readFiles() {
        try {
            Reader.buffer(SHIPS);
            Reader.buffer(PIRATES);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void buffer(String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        for (String line; (line = bufferedReader.readLine()) != null; ) {

            loadList(fileName, line);
        }
    }

    public static void loadList(String fileName, String line) {

        if (fileName.equals(SHIPS)) {
            Generator.shipNames.add(line);

        } else if (fileName.equals(PIRATES)) {
            String[] parts = line.split(",");
            for (int i = 0; i < parts.length; i++) {
                parts[i] = (parts[i]).trim();
            }
            Generator.pirateFirstNames.add(parts[0]);
            Generator.pirateLastNames.add(parts[1]);
        }
    }
}
