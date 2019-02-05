public class Snake extends Entity {
    private Snake lastPart;

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
}