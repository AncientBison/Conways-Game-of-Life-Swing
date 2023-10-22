import org.junit.*;

import static org.junit.Assert.*;

public class CellTests {

    @Test
    public void getAliveNeighborsTest1() {
        boolean[][] cells = new boolean[3][3];
        cells[1][1] = true;
        assertEquals(0, GameLogic.getAliveNeighbors(cells, 1, 1));
    }

    @Test
    public void getAliveNeighborsTest2() {
        boolean[][] cells = new boolean[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != 1 || j != 1) {
                    cells[i][j] = true;
                }
            }
        }
        assertEquals(8, GameLogic.getAliveNeighbors(cells, 1, 1));
    }

    @Test
    public void getAliveNeighborsTest3() {
        boolean[][] cells = new boolean[3][3];
        cells[0][0] = true;
        cells[2][2] = true;
        assertEquals(2, GameLogic.getAliveNeighbors(cells, 1, 1));
    }

    @Test
    public void getAliveNeighborsTest4() {
        boolean[][] cells = new boolean[3][3];
        cells[0][0] = true;
        assertEquals(0, GameLogic.getAliveNeighbors(cells, 0, 0));
    }

    @Test
    public void getAliveNeighborsTest5() {
        boolean[][] cells = new boolean[3][3];
        cells[0][2] = true;
        assertEquals(0, GameLogic.getAliveNeighbors(cells, 0, 2));
    }

    @Test
    public void getAliveNeighborsTest6() {
        boolean[][] cells = new boolean[3][3];
        cells[2][0] = true;
        assertEquals(0, GameLogic.getAliveNeighbors(cells, 2, 0));
    }

    @Test
    public void getAliveNeighborsTest7() {
        boolean[][] cells = new boolean[3][3];
        cells[2][2] = true;
        assertEquals(0, GameLogic.getAliveNeighbors(cells, 2, 2));
    }

    @Test
    public void getAliveNeighborsTest8() {
        boolean[][] cells = new boolean[3][3];
        cells[0][0] = true;
        cells[0][1] = true;
        cells[2][2] = true;

        assertEquals(3, GameLogic.getAliveNeighbors(cells, 1, 1));
    }

    @Test
    public void getAliveNeighborsTest9() {
        boolean[][] cells = new boolean[6][6];
        cells[5][0] = true;
        cells[4][0] = true;
        cells[3][0] = true;
        assertEquals(1, GameLogic.getAliveNeighbors(cells, 3, 0));
    }

    @Test
    public void getAliveNeighborsTest10() {
        boolean[][] cells = new boolean[3][3];
        assertEquals(0, GameLogic.getAliveNeighbors(cells, 1, 1));
    }

    @Test
    public void cellsToStringTest1() {
        boolean[][] cells = new boolean[3][3];
        cells[0][0] = true;
        cells[1][2] = true;

        String cellsString = CellsStringConverter.cellsToString(cells);

        boolean[][] decoded = CellsStringConverter.stringToCells(cellsString);
        assertArrayEquals(decoded, cells);
    }

    @Test
    public void cellsToStringTest2() {
        boolean[][] cells = new boolean[5][3];

        String cellsString = CellsStringConverter.cellsToString(cells);

        boolean[][] decoded = CellsStringConverter.stringToCells(cellsString);
        assertArrayEquals(decoded, cells);
    }
}