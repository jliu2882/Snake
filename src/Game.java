import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Game {
    private Board board;
    private Pane[][] cells;

    private Snake root;
    private ArrayList<Entity> entities;

    private int score;
    private boolean forceTick;

    public Game(Pane[][] cells) {
        // Construct Board
        Tile[][] fBoard = new Tile[Constants.BOARD_X][Constants.BOARD_Y];
        IntStream.range(0, Constants.BOARD_X).forEach(r -> {
            IntStream.range(0, Constants.BOARD_Y).forEach(c -> {
                fBoard[r][c] = new Tile(r, c);
            });
        });

        this.board = new Board(fBoard);
        this.cells = cells;

        // Generate default Entities
        Food food = new Food(Constants.FOOD_INITIAL_X, Constants.FOOD_INITIAL_Y);
        this.board.tileAt(Constants.FOOD_INITIAL_X, Constants.FOOD_INITIAL_Y).setEntity(food);

        Snake snake = new Snake(Constants.SNAKE_INITIAL_X, Constants.SNAKE_INITIAL_Y);
        this.board.tileAt(Constants.SNAKE_INITIAL_X, Constants.SNAKE_INITIAL_Y).setEntity(snake);

        this.root = snake; // This is just for convenience's sake, it should just be the first Snake in entities anyway
        this.entities = new ArrayList<>(Arrays.asList(food, snake));
    }

    /**
     * Generates a random position (including conflicts)
     *
     * @return the randomly generated Position
     */
    private static Position generateRandomPosition() {
        int x = (int) (Math.random() * Constants.BOARD_X);
        int y = (int) (Math.random() * Constants.BOARD_Y);

        return new Position(x, y);
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Randomly generates a position (excluding conflicts)
     *
     * @return the generated Position
     */
    public Position generatePosition() {
        Position pos = Game.generateRandomPosition();

        while (this.board.tileAt(pos).isOccupied()) {
            pos = Game.generateRandomPosition();
        }
        return pos;
    }

    /**
     * Updates the "backend" part of the board
     */
    public void update() {
        IntStream.rangeClosed(1, this.entities.size()).forEach(i -> {
            Entity entity = this.entities.get(this.entities.size() - i);

            // We really don't care about Food
            if (entity instanceof Snake) {
                Snake snake = (Snake) entity;
                Position lastPosition = snake.getPosition();
                Position newPosition = lastPosition.shift(snake.getDirection());

                if (this.board.tileAt(newPosition).getEntity() instanceof Food) {
                    score++;
                    //TODO implement a longer snek
                    Position pos = generatePosition();
                    while (this.board.tileAt(pos).isOccupied()) {
                        pos = generatePosition();
                    }
                    Food food = new Food(generateRandomPosition());
                    this.board.tileAt(food.getPosition()).setEntity(food);
                }

                this.board.tileAt(lastPosition).setEntity(null);
                this.board.tileAt(newPosition).setEntity(snake);
                snake.setPosition(newPosition);
                if (snake.getLastPart() != null) {
                    snake.setDirection(snake.getLastPart().getDirection());
                }
            }
        });
    }

    /**
     * Refreshes the graphical components, basically updating color
     */
    public void refresh() {
        IntStream.range(0, this.cells.length).forEach(r -> {
            Pane[] row = this.cells[r];
            IntStream.range(0, row.length).forEach(c -> {
                Pane cell = row[c];
                Tile cellTile = this.board.tileAt(r, c);

                cell.getStyleClass().removeAll("has-food", "has-snake");
                if (cellTile.isOccupied()) {
                    cell.getStyleClass().add(cellTile.getEntity() instanceof Food ? "has-food" : "has-snake");
                }
            });
        });
    }

    /**
     * And thus the game begins...
     */
    public void run() {
        // Preserve current scope before entering new one
        Game _this = this;

        new AnimationTimer() {
            private long tick;

            @Override
            public void handle(long now) {
                if (this.tick == 0) this.tick = now;
                final long dt = now - tick;

                if (_this.forceTick || dt > 1 / Constants.FRAMES_PER_SECOND * Constants.NANO_CONVERSION_RATIO) {
                    _this.update();
                    _this.refresh();

                    this.tick = now;
                    _this.forceTick = false;
                }
            }
        }.start();
    }

    /**
     * Register a key press and change directions if it's an arrow key
     *
     * @param keyCode - the key that was pressed down
     */
    public void onKeyPressed(KeyCode keyCode) {
        Direction newDirection;

        if (keyCode == KeyCode.UP) newDirection = Directions.UP;
        else if (keyCode == KeyCode.DOWN) newDirection = Directions.DOWN;
        else if (keyCode == KeyCode.LEFT) newDirection = Directions.LEFT;
        else if (keyCode == KeyCode.RIGHT) newDirection = Directions.RIGHT;
        else newDirection = this.root.getDirection();

        if (!newDirection.equals(Direction.inverseOf(this.root.getDirection()))) {
            this.root.setDirection(newDirection);
            // this.forceTick = true;
        }
    }
}
