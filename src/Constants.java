public class Constants {
    public static final int SCREEN_X = 1000;
    public static final int SCREEN_Y = 1000;

    public static final int BOARD_X = 20;
    public static final int BOARD_Y = 20;

    public static final int SNAKE_INITIAL_X = Constants.BOARD_X / 5;
    public static final int SNAKE_INITIAL_Y = Constants.BOARD_Y / 2;

    public static final int FOOD_INITIAL_X = Constants.SNAKE_INITIAL_X * 4;
    public static final int FOOD_INITIAL_Y = Constants.SNAKE_INITIAL_Y;

    public static final double SCALE_X = ((double) Constants.SCREEN_X) / ((double) BOARD_X);
    public static final double SCALE_Y = ((double) Constants.SCREEN_Y) / ((double) BOARD_Y);
}
