import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.util.Scanner;

import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;

class FileManager {
    public static String importFromFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Conway", "conway"));
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                Scanner fileReader = new Scanner(file);
                if (fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
                    fileReader.close();
                    return data;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void exportToFile(String string) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Conway", "conway"));
        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String absolutePath = file.getAbsolutePath();

            if (!absolutePath.substring(absolutePath.lastIndexOf(".") + 1).equals("conway"))
                absolutePath += ".conway";

            try {
                FileWriter fileWriter = new FileWriter(absolutePath);
                fileWriter.write(string);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}