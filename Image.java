import java.util.ArrayList;
import java.util.List;

public class Image {
    public Dimensions dimensions;
    public List<Star> stars;

    public Image(Dimensions dimensions) {
        this.dimensions = dimensions;
        this.stars = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("Image{dimensions:%s, stars=%s}", dimensions, stars);
    }
}