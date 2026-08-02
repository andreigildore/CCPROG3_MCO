package view;

import controller.LibraryController;
import model.Library;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Failed to initialize system look and feel.");
        }

        SwingUtilities.invokeLater(() -> {
            Library library = new Library();
            LibraryController controller = new LibraryController(library);
            
            MainFrame frame = new MainFrame(controller);
            frame.setVisible(true);
        });
    }
}
