public class Snake extends Entity {
    private Snake lastPart;
    private Direction direction = Directions.RIGHT;

    public Snake(Position position) {
        super(position);
    }

    public Snake(Position position, Snake lastPart) {
        super(position);
        this.lastPart = lastPart;
    }

    public Snake(int x, int y) {
        super(x, y);
    }

    public Snake(int x, int y, Snake lastPart) {
        super(x, y);
        this.lastPart = lastPart;
    }

    public Snake getLastPart() {
        return lastPart;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}