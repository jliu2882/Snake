package Snake;

public class Board {
    Object[][] board;
    Snake snake;

    public Board(){
        board = new Object[Constants.HEIGHT][Constants.WIDTH];
        snake = new Snake(Directions.RIGHT);
        board[Constants.HEIGHT/2][Constants.WIDTH/2] = snake;
    }
    //updates the board
    public void update(){

    }
}
