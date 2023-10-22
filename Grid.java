import java.awt.Color;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class Grid extends JComponent {
    private Main main;

    public Grid(Main main) {
        this.main = main;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graph2 = (Graphics2D) g;

        graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawCells(graph2);
    }

    public void drawCells(Graphics2D graph2) {
        for (int x = 0; x < Constants.GRID_WIDTH - Constants.CELL_SIZE; x += Constants.CELL_SIZE) {
            for (int y = 0; y < Constants.GRID_HEIGHT - Constants.CELL_SIZE; y += Constants.CELL_SIZE) {
                Shape rootRect = new Rectangle2D.Float(x, y, Constants.CELL_SIZE, Constants.CELL_SIZE);

                if (y >= Constants.GRID_HEIGHT || x >= Constants.GRID_WIDTH) {
                    continue;
                }


                if (main.getCells()[x / Constants.CELL_SIZE][y / Constants.CELL_SIZE]) {
                    graph2.setColor(Color.BLACK);
                    graph2.fill(rootRect);
                }
                graph2.setColor(Color.GRAY);
                graph2.draw(rootRect);
            }
        }
    }
}