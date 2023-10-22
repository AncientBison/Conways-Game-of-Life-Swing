import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;

public class CellGraphics extends JPanel {
    public CellGraphics(final Main main) {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();

        Grid grid = new Grid(main);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(new EmptyBorder(30, (screenWidth - Constants.GRID_WIDTH) / 2, 30, ((screenWidth - Constants.GRID_WIDTH) / 2) - 1));
        this.add(grid);
        this.add(new Controls(main));
        grid.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int targetCellX = e.getX() / Constants.CELL_SIZE;
                int targetCellY = e.getY() / Constants.CELL_SIZE;
                if (targetCellX < 0 || targetCellX >= main.getCells().length) {
                    return;
                }
                if (targetCellY < 0 || targetCellY >= main.getCells()[0].length) {
                    return;
                }
                main.getCells()[e.getX() / Constants.CELL_SIZE][e.getY() / Constants.CELL_SIZE] = !main.getCells()[e.getX() / Constants.CELL_SIZE][e.getY() / Constants.CELL_SIZE];
                main.repaintMain();
            }
        });
    }
}