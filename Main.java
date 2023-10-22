import java.awt.Dimension;

import javax.swing.JFrame;

public class Main extends JFrame {
    private boolean[][] cells;

    public static void main(String[] args) {
        if (args.length > 0) {
            if (args.length > 1) {
                System.out.println("Too many arguments given!");
            } else {
                if (args.length == 1) {
                    try {
                        float n = Float.parseFloat(args[0]);
                        if (n <= 0) {
                            System.out.println("Argument must be a positive number > 0!");
                            System.exit(1);
                        } else {
                            Constants.SCALE = n;
                            Constants.rescale();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Argument must be a positive integer");
                        System.exit(1);
                    }
                }
            }
        }
        new Main();
    }

    public Main() {
        cells = GameLogic.resetCells();
        repaintMain();
        this.setSize(Constants.GRID_HEIGHT, Constants.GRID_HEIGHT + 60);
        this.setPreferredSize(new Dimension(Constants.GRID_HEIGHT, Constants.GRID_HEIGHT + 30));
        this.setTitle("Drawing things");
        this.add(new CellGraphics(this));
        this.setVisible(true);
    }

    public void repaintMain() {
        repaint();
    }

    public void setCells(boolean[][] newCells) {
        cells = newCells;
        repaint();
    }

    public boolean[][] getCells() {
        return cells;
    }
}