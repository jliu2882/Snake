public class Snake extends Entity {
    private Snake nextPart;
    private Direction direction = Directions.RIGHT;

    public Snake(Position position) {
        super(position);
    }

    public Snake(int x, int y) {
        super(x, y);
    }

    public Snake getNextPart() {
        return nextPart;
    }

    public void setNextPart(Snake nextPart) {
        this.nextPart = nextPart;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}