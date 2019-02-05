public class Entity {
    private Position position;

    public Entity(Position position) {
        this.position = position;
    }

    public Entity(int x, int y) {
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }
}
