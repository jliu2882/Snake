package Snake;

public class Body {
    public int x,y;

    public Body(int x, int y){
        this.x = x;
        this.y = y;
    }

    //getters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    //setters
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    //moves the snake but first test if direction conflicts with previous direction
    public void move(int direction, Snake snake){
        if(direction * -1 == snake.getDirection()){
            snake.setDirection(snake.getDirection());
        } else {
            snake.setDirection(direction);
        }
        if(snake.getDirection() == Directions.UP) {
            this.setY(snake.snake.get(1).getY() + 1);
        }
        else if(snake.getDirection() == Directions.DOWN) {
            this.setY(snake.snake.get(1).getY() - 1);
        }
        else if(snake.getDirection() == Directions.LEFT) {
            this.setX(snake.snake.get(1).getX() + 1);
        }
        else if(snake.getDirection() == Directions.RIGHT) {
            this.setX(snake.snake.get(1).getX() - 1);
        }
    }
}
