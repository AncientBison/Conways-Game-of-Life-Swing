public class Constants {
    public static float SCALE = 1;
    public static int CELL_SIZE = 20; //optionally passed in through CLI
    public static int GRID_WIDTH = 2000;
        public static int GRID_HEIGHT = 1000;

    public static void rescale() {
        CELL_SIZE = (int)(CELL_SIZE * SCALE);
        GRID_WIDTH = (int)(GRID_WIDTH * SCALE);
        GRID_HEIGHT = (int)(GRID_HEIGHT * SCALE);
    }
}