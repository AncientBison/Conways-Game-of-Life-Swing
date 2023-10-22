public class GameLogic {
    public static boolean[][] processCells(boolean[][] cells) {
        boolean[][] newCellValues = new boolean[cells.length][cells[0].length];

        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[0].length; y++) {
                newCellValues[x][y] = cellIsAliveNextGeneration(cells, x, y);
            }
        }

        return newCellValues;
    }

    public static int getAliveNeighbors(boolean[][] cells, int x, int y) {
        int aliveNeighbors = 0;
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                if (!(xOffset == 0 && yOffset == 0)) {
                    if (x + xOffset < 0 || x + xOffset >= cells.length) {
                        continue;
                    }
                    if (y + yOffset < 0 || y + yOffset >= cells[0].length) {
                        continue;
                    }
                    if (cells[(x + xOffset)][y + yOffset]) {
                        aliveNeighbors++;
                    }
                }
            }
        }
        return aliveNeighbors;
    }

    public static boolean cellIsAlive(boolean[][] cells, int x, int y) {
        return cells[x][y];
    }

    public static boolean cellIsAliveNextGeneration(boolean[][] cells, int x, int y) {
        int aliveNeighbors = getAliveNeighbors(cells, x, y);

        if (cellIsAlive(cells, x, y)) {
            if (aliveNeighbors != 3 && aliveNeighbors != 2) {
                return false;
            }
        } else {
            if (aliveNeighbors == 3) {
                return true;
            }
        }
        return cellIsAlive(cells, x, y);
    }

    public static boolean[][] resetCells() {
        return new boolean[Constants.GRID_WIDTH/Constants.CELL_SIZE][Constants.GRID_HEIGHT/Constants.CELL_SIZE];
    }
}