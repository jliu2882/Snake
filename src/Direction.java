public class Direction {
    private int xModifier;
    private int yModifier;

    public Direction(int xModifier, int yModifier) {
        this.xModifier = xModifier;
        this.yModifier = yModifier;
    }

    public static Direction inverseOf(Direction direction) {
        if (direction.getXModifier() == -1) return Directions.DOWN;
        else if (direction.getXModifier() == 1) return Directions.UP;
        else if (direction.getYModifier() == -1) return Directions.RIGHT;
        else if (direction.getYModifier() == 1) return Directions.LEFT;
        else return null; // Theoretical collapse aka dead
    }

    public int getXModifier() {
        return xModifier;
    }

    public int getYModifier() {
        return yModifier;
    }

    public boolean equals(Direction b) {
        return this.xModifier == b.getXModifier() && this.yModifier == b.getYModifier();
    }
}