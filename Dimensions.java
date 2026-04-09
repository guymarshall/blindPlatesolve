public class Dimensions {
    public int width;
    public int height;

    public Dimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return String.format("Dimensions{width:%d, height=%d}", width, height);
    }
}