import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Game {
    private Board board;
    private Pane[][] cells;

    private Snake head;
    private ArrayList<Snake> entities;

    private Direction nextDirection = Directions.RIGHT;

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

        this.head = snake; // This is just for convenience's sake, it should just be the first Snake in entities anyway
        this.entities = new ArrayList<>(Arrays.asList(snake));
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
        // Propagate directions down the body, starts at -1 since tail is the end
        IntStream.rangeClosed(1, this.entities.size()).forEach(i -> {
            Snake snake = this.entities.get(this.entities.size() - i);

            if (snake.getNextPart() != null) {
                snake.getNextPart().setDirection(snake.getDirection());
            }
        });

        // Now we bring the new direction into the head
        this.head.setDirection(this.nextDirection);

        IntStream.range(0, this.entities.size()).forEach(i -> {
            Snake snake = this.entities.get(i);
            Position lastPosition = snake.getPosition();
            Position newPosition = lastPosition.shift(snake.getDirection());

            if (this.board.tileAt(newPosition).getEntity() instanceof Food) {
                // Take advantage of how this.entities is setup
                Snake lastEgg = this.entities.get(this.entities.size() - 1);
                Position futurePosition = lastEgg.getPosition();
                Snake egg = new Snake(futurePosition);

                lastEgg.setNextPart(egg);
                egg.setDirection(lastEgg.getDirection());
                this.board.tileAt(lastPosition).setEntity(egg);
                this.entities.add(egg);

                // Generate new food
                Food food = new Food(this.generatePosition());
                this.board.tileAt(food.getPosition()).setEntity(food);
                score++;
            }

            this.board.tileAt(lastPosition).setEntity(null);
            this.board.tileAt(newPosition).setEntity(snake);
            snake.setPosition(newPosition);
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

                if (_this.board.gameConditionLost) {
                    this.stop();
                    Main.endgame(Main.stage);
                }

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
        else newDirection = this.head.getDirection();

        if (!newDirection.equals(Direction.inverseOf(this.head.getDirection()))) {
            this.nextDirection = newDirection;
            // this.forceTick = true;
        }
    }


    /**
     * Endgame Scene
     * Creates a new scene that serves as a "Game Over" screen;
     */
}
