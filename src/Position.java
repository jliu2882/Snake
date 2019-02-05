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

    public Position shift(Direction direction) {
        int newX = this.x + direction.getXModifier();
        int newY = this.y + direction.getYModifier();

        return new Position(newX, newY);
    }

    public boolean equals(Position b) {
        return this.x == b.getX() && this.y == b.getY();
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
