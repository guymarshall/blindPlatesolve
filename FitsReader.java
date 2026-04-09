import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FitsReader {
    public static List<Map.Entry<String, String>> getHeaders(String path) throws IOException {
        List<Map.Entry<String, String>> headers = new ArrayList<>();

        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(path))) {
            byte[] block = new byte[2880];

            while (true) {
                int read = reader.readNBytes(block, 0, 2880);
                if (read < 2880) {
                    throw new EOFException("Unexpected end of file");
                }

                for (int i = 0; i < 2880; i += 80) {
                    String line = new String(block, i, 80, StandardCharsets.UTF_8);

                    String keyword = line.substring(0, 8).trim();

                    if (keyword.equals("END")) {
                        return headers;
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
                    }
                }
            }
        }
    }
}