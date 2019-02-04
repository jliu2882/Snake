package Snake;

import java.util.ArrayList;

public class Snake {
    public int direction;
    public ArrayList<Body> snake;

    public Snake(int direction){
        this.direction = direction;
        snake = new ArrayList<Body>();
        Body head = new Body(Constants.WIDTH/2,Constants.HEIGHT/2);
    }
    //getters
    public int getDirection() {
        return direction;
    }
    //setters
    public void setDirection(int direction) {
        this.direction = direction;
    }
    //update position of snake by moving the last part to the front
    public void update(int direction){
        snake.get(snake.size()-1).move(direction,this);
    }
}
