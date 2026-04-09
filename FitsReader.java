import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FitsReader {
    public static Image getImage(String path) throws IOException {
        List<Map.Entry<String, String>> headers = new ArrayList<>();
        boolean endReached = false;
        int width = 0;
        int height = 0;

        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(path))) {
            byte[] block = new byte[2880];

            while (!endReached) {
                int read = reader.readNBytes(block, 0, 2880);
                if (read < 2880) {
                    throw new EOFException("Unexpected end of file");
                }

                for (int i = 0; i < 2880; i += 80) {
                    String line = new String(block, i, 80, StandardCharsets.US_ASCII);

                    String keyword = line.substring(0, 8).trim();

                    if (keyword.equals("END")) {
                        endReached = true;
                        break;
                    }

                    int equalPosition = line.indexOf('=');
                    if (equalPosition != -1) {
                        String value = line.substring(equalPosition + 1);

                        int slashPosition = value.indexOf('/');
                        if (slashPosition != -1) {
                            value = value.substring(0, slashPosition);
                        }

                        value = value.trim();
                        headers.add(new AbstractMap.SimpleEntry<>(keyword, value));

                        if (keyword.equals("NAXIS1")) {
                            width = Integer.parseInt(value);
                        }

                        if (keyword.equals("NAXIS2")) {
                            height = Integer.parseInt(value);
                        }

                        if (width != 0 && height != 0) {
                            endReached = true;
                            break;
                        }
                    }
                }
            }
        }

        if (width == 0) {
            System.out.println("Width not found");
            System.exit(1);
        }

        if (height == 0) {
            System.out.println("Height not found");
            System.exit(1);
        }

        return new Image(width, height);
    }
}