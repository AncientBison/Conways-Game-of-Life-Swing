public class CellsStringConverter {

    public static String cellsToString(boolean[][] cells) {
        String result = cells.length + "x" + cells[0].length + "//";

        for (int y = 0; y < cells[0].length; y++) {
            for (int x = 0; x < cells.length; x++) {
                result += (cells[x][y] ? "1" : "0");
            }
        }

        return result;
    }

    public static boolean[][] stringToCells(String string) {
        String sizeString = string.split("//")[0];
        String dataString = string.split("//")[1];

        int width = Integer.parseInt(sizeString.split("x")[0]);
        int height = Integer.parseInt(sizeString.split("x")[1]);
        
        boolean[][] cells = new boolean[width][height];

        int position = 0;
    
        for (char cellValue : dataString.toCharArray()) {
            cells[position % width][position / width] = (cellValue == '1');

            position++;
        }

        return cells;
    }
}