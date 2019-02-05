public class Constants {
    public static final int SCREEN_X = 1000;
    public static final int SCREEN_Y = 1000;

    public static final int BOARD_X = 20;
    public static final int BOARD_Y = 20;

    public static final int SNAKE_INITIAL_X = Constants.BOARD_X / 2 - 1;
    public static final int SNAKE_INITIAL_Y = Constants.BOARD_Y / 5 - 1;

    public static final int FOOD_INITIAL_X = Constants.SNAKE_INITIAL_X;
    public static final int FOOD_INITIAL_Y = Constants.BOARD_Y / 5 * 4 - 1;

    public static final double FRAMES_PER_SECOND = 10;
    public static final double NANO_CONVERSION_RATIO = 1e9;

    public static final double SCALE_X = ((double) Constants.SCREEN_X) / ((double) Constants.BOARD_X);
    public static final double SCALE_Y = ((double) Constants.SCREEN_Y) / ((double) Constants.BOARD_Y);
}
