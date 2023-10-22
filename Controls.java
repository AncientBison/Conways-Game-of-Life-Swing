import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Controls extends JPanel {
    private boolean playing = false;

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    JPanel buttonPanel = new JPanel();
    JButton runButton = new JButton("Play");
    JButton oneGenerationButton = new JButton("One Generation");
    JButton clearButton = new JButton("Clear");
    JButton importButton = new JButton("Import");
    JButton exportButton = new JButton("Export");

    JPanel sliderPanel = new JPanel();
    JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 250);
    JLabel speedSliderLabel = new JLabel("Generation Speed");

    public Controls(final Main main) {
        Runnable generationRunnable = new Runnable() {
            public void run() {
                main.setCells(GameLogic.processCells(main.getCells()));
                repaint();
            }
        };

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        speedSlider.setMajorTickSpacing(250);
        speedSlider.setMinorTickSpacing(50);
        speedSlider.setSnapToTicks(true);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        sliderPanel.add(speedSliderLabel);
        sliderPanel.add(speedSlider);
        speedSliderLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));

        speedSlider.addChangeListener(e -> {
            if (!playing) {
                return;
            }
            if (speedSlider.getValueIsAdjusting()) {
                return;
            }
            executor.shutdown();
            executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(generationRunnable, 0, Math.max(speedSlider.getValue(), 1), TimeUnit.MILLISECONDS);
        });

        oneGenerationButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                generationRunnable.run();
            }
        });
        
        runButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                playing = !playing;
                if (playing) {
                    executor = Executors.newScheduledThreadPool(1);
                    executor.scheduleAtFixedRate(generationRunnable, 0, Math.max(speedSlider.getValue(), 1), TimeUnit.MILLISECONDS);
                } else {
                    executor.shutdown();
                }
                updateRunButtonText();
            }
        });
        
        clearButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                main.setCells(GameLogic.resetCells());
                stopPlaying();
            }
        });
        
        importButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                stopPlaying();
                String importCode = FileManager.importFromFile();
                if (importCode == null) {
                    return;
                }
                boolean[][] importedCells = CellsStringConverter.stringToCells(importCode);
                if (importedCells.length == main.getCells().length && importedCells[0].length == main.getCells()[0].length) {
                    main.setCells(importedCells);
                } else {
                    System.out.println("Error: Attempted to load with incorrect grid size");
                }
            }
        });
        
        exportButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                FileManager.exportToFile(CellsStringConverter.cellsToString(main.getCells()));
            }
        });
        
        buttonPanel.add(runButton);
        buttonPanel.add(oneGenerationButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(importButton);
        buttonPanel.add(exportButton);

        this.add(buttonPanel);
        this.add(sliderPanel);
    }

    public void updateRunButtonText() {
        runButton.setText(playing ? "Pause" : "Play");
        repaint();
    }

    public void stopPlaying() {
        if (playing) {
            executor.shutdown();
            playing = false;
            updateRunButtonText();
        }
    }
}