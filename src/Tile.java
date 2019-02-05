public class Tile {
    private Position position;
    private Entity entity;

    /**
     * Creates a tile at `position`
     *
     * @param position - the position of the tile
     */
    public Tile(Position position) {
        this.position = position;
    }

    /**
     * Creates a tile at (x, y)
     *
     * @param x - the x coordinate
     * @param y - the y coordinate
     */
    public Tile(int x, int y) {
        this.position = new Position(x, y);
    }

    /**
     * Creates a tile at `position` occupied by `entity`
     *
     * @param position - the position of the tile
     * @param entity   - the entity occupying
     */
    public Tile(Position position, Entity entity) {
        this.position = position;
        this.entity = entity;
    }

    /**
     * Creates a tile at (x, y) occupied by `entity`
     *
     * @param x      - the x coordinate
     * @param y      - the y coordinate
     * @param entity - the entity occupying
     */
    public Tile(int x, int y, Entity entity) {
        this.position = new Position(x, y);
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Determines if an entity currently occupies the tile
     *
     * @return whether or not the tile is occupied
     */
    public boolean isOccupied() {
        return this.getEntity() != null;
    }

    @Override
    public String toString() {
        return this.position.toString();
    }
}
