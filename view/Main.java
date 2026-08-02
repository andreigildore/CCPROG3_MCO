import controller.LibraryController;
import model.Library;
import view.MainFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Library library = new Library();
            LibraryController controller = new LibraryController(library);
            
            MainFrame frame = new MainFrame(controller);
            frame.setVisible(true);
        });
    }
}
