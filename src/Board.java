public class Board {
    private Tile[][] board;

    public Board(Tile[][] board) {
        this.board = board;
    }

    public Tile tileAt(Position pos) {
        return this.board[pos.getX()][pos.getY()];
    }

    public Tile tileAt(int x, int y) {
        return this.board[x][y];
    }
}