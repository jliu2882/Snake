public class Constants {
    public static final int SCREEN_X = 1000;
    public static final int SCREEN_Y = 1000;

    public static final int BOARD_X = 20;
    public static final int BOARD_Y = 20;

    public static final int SPAWN_X = Constants.BOARD_X / 5;
    public static final int SPAWN_Y = Constants.BOARD_Y / 2;

    public static final double SCALE_X = ((double) Constants.SCREEN_X) / ((double) BOARD_X);
    public static final double SCALE_Y = ((double) Constants.SCREEN_Y) / ((double) BOARD_Y);
}
