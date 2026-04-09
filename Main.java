import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // TODO REMOVE: get test file path
        byte[] buffer = Files.readAllBytes(Paths.get("testFilePath.txt"));
        String testFilePath = new String(buffer).trim();

        // open image
        Image image = FitsReader.getImage(testFilePath);
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