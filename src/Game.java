import javafx.scene.layout.Pane;

import java.util.stream.IntStream;

public class Game {
    private Board board;
    private Pane[][] cells;

    public Game(Pane[][] cells) {
        Tile[][] fBoard = new Tile[Constants.BOARD_X][Constants.BOARD_Y];
        IntStream.range(0, Constants.BOARD_X).forEach(r -> {
            IntStream.range(0, Constants.BOARD_Y).forEach(c -> {
                fBoard[r][c] = new Tile(r, c);
            });
        });

        this.board = new Board(fBoard);
        this.cells = cells;
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

    public void update() {
        // Update graphical components
        IntStream.range(0, this.cells.length).forEach(r -> {
            Pane[] row = this.cells[r];
            IntStream.range(0, row.length).forEach(c -> {
                Pane cell = row[c];
                Tile cellTile = this.board.tileAt(r, c);

                cell.getStyleClass().removeAll();
                if (cellTile.isOccupied()) {
                    cell.getStyleClass().add(cellTile.getEntity() instanceof Food ? "has-food" : "has-snake");
                }
            });
        });
    }
}
