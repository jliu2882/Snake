public class Position {
    private final int x;
    private final int y;

    /**
     * Represents the coordinate (x, y)
     *
     * @param x - the x coordinate
     * @param y - the y coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Position b) {
        return this.x == b.getX() && this.y == b.getY();
    }
}
