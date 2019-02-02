package Snake;

import java.util.ArrayList;

public class Snake {
    public int direction;
    public ArrayList<Body> snake;

    public Snake(int direction){
        this.direction = direction;
        snake = new ArrayList<Body>();
    }
    //getters
    public int getDirection() {
        return direction;
    }
    //setters
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void update(int direction){
        snake.get(snake.size()-1).move(direction,snake);
    }
}
