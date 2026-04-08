import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // TODO REMOVE: get test file path
        byte[] buffer = Files.readAllBytes(Paths.get("testFilePath.txt"));
        String testFilePath = new String(buffer).trim();

        // open image
        List<Map.Entry<String, String>> headers = FitsReader.getHeaders(testFilePath);
        Map<String, String> headerMap = new HashMap<>();
        for (Map.Entry<String, String> entry : headers) {
            headerMap.put(entry.getKey(), entry.getValue());
        }
        int width = Integer.parseInt(headerMap.get("NAXIS1"));
        int height = Integer.parseInt(headerMap.get("NAXIS2"));
        Image image = new Image(width, height);
        System.out.println(image);

        // identify stars

        // find brightest star

        // look at 4 nearest stars to that star and measure distances between
        // them (hash code)

        // repeat these steps for the stars in the star database

        // star database hash codes will be compared with the image hash codes until
        // some matches are found.

        // Once some matches are found it is possible to calculate the precise position
        // of the image with the matching database stars.
    }
}