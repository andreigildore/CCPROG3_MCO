package view;

import controller.LibraryController;
import model.MediaEntry;
import model.StatusMapper;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private final LibraryController controller;
    private final DefaultListModel<String> listModel;
    private final JList<String> mediaList;

    public MainFrame(LibraryController controller) {
        this.controller = controller;
        
        setTitle("Media Vault");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        listModel = new DefaultListModel<>();
        mediaList = new JList<>(listModel);
        mediaList.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(mediaList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton btnAddSample = new JButton("Add Sample Media");
        JButton btnRefresh = new JButton("Refresh List");
        JButton btnSummary = new JButton("Show Summary");

        buttonPanel.add(btnAddSample);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnSummary);
        
        add(buttonPanel, BorderLayout.SOUTH);

        
        btnAddSample.addActionListener(e -> {
            controller.addVideoGame("Persona 5", "RPG", 0, 0, "Atlus", "PlayStation 4");
            controller.addAnime("Attack on Titan", "Action", 1, "MAPPA", "Hajime Isayama", 75, true);
            refreshList();
        });

        btnRefresh.addActionListener(e -> refreshList());

        btnSummary.addActionListener(e -> {
            var summary = controller.getSummary();
            String summaryText = String.format(
                "Total Entries: %d\nPlanned: %d\nIn Progress: %d\nCompleted: %d\n\nAverage Rating: %.2f",
                summary.total, summary.planned, summary.inProgress, summary.completed, summary.averageRating
            );
            JOptionPane.showMessageDialog(this, summaryText, "Library Summary", JOptionPane.INFORMATION_MESSAGE);
        });

        refreshList();
    }

  
    private void refreshList() {
        listModel.clear();
        List<MediaEntry> entries = controller.getAllEntries();
        
        for (MediaEntry entry : entries) {
            String statusText = StatusMapper.getStatusString(entry.getStatus());
            String displayText = String.format("[%s] %s - %s", 
                entry.getTypeLabel(), entry.getTitle(), statusText);
            
            listModel.addElement(displayText);
        }
    }
}
