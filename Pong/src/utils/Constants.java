package utils;
public final class Constants {
    private Constants() {
        // prevents instantiation
    }
    public static final int BOARD_WIDTH = 1080;
    public static final int BOARD_HEIGHT = 720;
    public static final String PLAYER_IMAGE_PATH = "resources/paddle.png";
    public static final int PLAYER_WIDTH = 20;
    public static final int PLAYER_HEIGHT = 100;
    public static final int PLAYER_SPEED = 15;
    public static final String BALL_IMAGE_PATH = "resources/ball.png";
    public static final int BALL_WIDTH = 40;
    public static final int BALL_HEIGHT = 40;
    // A delay of 25 milliseconds results in a frame rate of 45 FPS.
    public static final int TICK_DELAY = 25;
    public static final String WALL_IMAGE_PATH = "resources/paddle.png";
    public static final int WALL_WIDTH = 1080;
    public static final int WALL_HEIGHT = 20;
}
