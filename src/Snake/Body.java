package Snake;

import java.util.ArrayList;

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

    public void move(int direction, ArrayList<Body> snake){
        if(direction==Constants.UP){
            this.setY(snake.get(1).getY()+1);
        }
        if(direction==Constants.DOWN){
            this.setY(snake.get(1).getY()-1);
        }
        if(direction==Constants.LEFT){
            this.setX(snake.get(1).getX()+1);
        }
        if(direction==Constants.RIGHT){
            this.setX(snake.get(1).getX()-1);
        }
    }
}
