public class Board {
    private Tile[][] board;
    public boolean gameConditionLost = false;

    public Board(Tile[][] board) {
        this.board = board;
    }

    public Tile tileAt(Position pos) {

        if((pos.getX() < 0) || (pos.getY() < 0) || (pos.getX() >= this.board.length) || (pos.getY() >= this.board[0].length))
        {
            gameConditionLost = true;
            return null;
        }
        return this.board[pos.getX()][pos.getY()];
    }

    public Tile tileAt(int x, int y) {
        return this.board[x][y];
    }
}