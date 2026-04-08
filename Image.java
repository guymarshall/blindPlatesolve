import java.util.ArrayList;
import java.util.List;

public class Image {
    public int width;
    public int height;
    public List<Star> stars;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        this.stars = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("Image{width:%d, height=%d, stars=%s}", width, height, stars);
    }
}