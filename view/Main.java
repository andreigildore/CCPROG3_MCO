package view;

import controller.LibraryController;
import model.Library;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Font;

/**
 * Entry point for the Media Vault application.
 * Initializes the system look, creates the library and controller, and launches the GUI.
 */
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // Override every registered font in the UIManager with a modern SansSerif font.
            Font modernFont = new Font("SansSerif", Font.PLAIN, 13);
            java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
            while (keys.hasMoreElements()) {
                Object key = keys.nextElement();
                Object value = UIManager.get(key);
                if (value instanceof javax.swing.plaf.FontUIResource) {
                    UIManager.put(key, new javax.swing.plaf.FontUIResource(modernFont));
                }
            }
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
