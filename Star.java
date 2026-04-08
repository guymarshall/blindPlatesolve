public class Star {
    public Coordinates coordinates;
    public int size;

    public Star(Coordinates coordinates, int size) {
        this.coordinates = coordinates;
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("Star{coordinates=%s, size=%d}", coordinates, size);
    }
}