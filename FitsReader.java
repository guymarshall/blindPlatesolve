import java.io.*;
import java.nio.charset.StandardCharsets;

public class FitsReader {
    public static Image getImage(String path) throws IOException {
        int width = -1;
        int height = -1;

        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(path))) {
            byte[] card = new byte[80];

            while (true) {
                int bytesRead = reader.readNBytes(card, 0, 80);
                if (bytesRead < 80) {
                    throw new EOFException("Unexpected end of file");
                }

                String line = new String(card, StandardCharsets.US_ASCII);
                String keyword = line.substring(0, 8).trim();

                if ("END".equals(keyword)) {
                    break;
                }

                if ("NAXIS1".equals(keyword)) {
                    width = parseValue(line);
                } else if ("NAXIS2".equals(keyword)) {
                    height = parseValue(line);
                }

                if (width != -1 && height != -1) {
                    break;
                }
            }
        }

        if (width == -1 || height == -1) {
            throw new IllegalStateException("Missing NAXIS1 or NAXIS2 in FITS header");
        }

        return new Image(width, height);
    }

    private static int parseValue(String line) {
        int equalPos = line.indexOf('=');
        if (equalPos == -1) {
            return -1;
        }

        String value = line.substring(equalPos + 1);
        int slashPos = value.indexOf('/');
        if (slashPos != -1) {
            value = value.substring(0, slashPos);
        }

        return Integer.parseInt(value.trim());
    }
}