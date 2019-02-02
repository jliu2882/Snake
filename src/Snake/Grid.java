package Snake;

public class Grid {
    Object[][] board;
    Snake snake;

    public Grid(){
        board = new Object[Constants.HEIGHT][Constants.WIDTH];
        snake = new Snake(Constants.RIGHT);
        board[Constants.HEIGHT/2][Constants.WIDTH/2] = snake;
    }

    public void update(){

    }
}
