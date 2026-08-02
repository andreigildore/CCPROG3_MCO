package view;

import controller.LibraryController;
import model.MediaEntry;
import model.StatusMapper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainFrame extends JFrame {
    private final LibraryController controller;
    private DefaultTableModel tableModel;
    private JTable libraryTable;

    public MainFrame(LibraryController controller) {
        this.controller = controller;

        // --- Frame Configuration ---
        setTitle("Media Vault");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // --- Main Tabbed Pane ---
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add Tabs
        tabbedPane.addTab("My Library", createLibraryPanel());
        tabbedPane.addTab("Add Media", createAddMediaPanel());
        tabbedPane.addTab("Dashboard", createDashboardPanel());

        // --- UX Feature: Keyboard Shortcuts ---
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_L); // Alt + L
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_A); // Alt + A
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_D); // Alt + D

        add(tabbedPane, BorderLayout.CENTER);

        // Initial data load
        refreshTable();
    }

    /**
     * Creates the Library tab featuring a JTable, Live Search, Color-Coding, and Action Buttons.
     */
    private JPanel createLibraryPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Search Bar Setup ---
        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        searchPanel.add(new JLabel("Search Library:"), BorderLayout.WEST);
        JTextField searchField = new JTextField();
        searchPanel.add(searchField, BorderLayout.CENTER);
        panel.add(searchPanel, BorderLayout.NORTH);

        // --- Table Setup ---
        String[] columnNames = {"Type", "Title", "Genre", "Status", "Rating"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        
        libraryTable = new JTable(tableModel);
        libraryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        libraryTable.getTableHeader().setReorderingAllowed(false);
        
        // --- Activate the Live Sorter ---
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        libraryTable.setRowSorter(sorter);
        
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            private void updateFilter() {
                String text = searchField.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text)); // Case-insensitive
                }
            }
            @Override public void insertUpdate(DocumentEvent e) { updateFilter(); }
            @Override public void removeUpdate(DocumentEvent e) { updateFilter(); }
            @Override public void changedUpdate(DocumentEvent e) { updateFilter(); }
        });

        // --- Custom Column Rendering (Color-coded Status) ---
        libraryTable.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = (String) value;
                
                if (!isSelected) {
                    if ("Completed".equals(status)) {
                        c.setForeground(new Color(34, 139, 34)); // Forest Green
                        c.setFont(c.getFont().deriveFont(Font.BOLD));
                    } else if ("In Progress".equals(status)) {
                        c.setForeground(new Color(204, 102, 0)); // Dark Orange
                        c.setFont(c.getFont().deriveFont(Font.BOLD));
                    } else {
                        c.setForeground(Color.GRAY);
                        c.setFont(c.getFont().deriveFont(Font.PLAIN));
                    }
                } else {
                    c.setForeground(table.getSelectionForeground()); 
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(libraryTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // --- Action Buttons Panel (Bottom) ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnLoadSample = new JButton("Load Sample Data");
        JButton btnUpdateStatus = new JButton("Update Status / Rate");
        JButton btnDelete = new JButton("Delete Selected");

        // --- UX Feature: Tooltips ---
        btnLoadSample.setToolTipText("Instantly load sample media to test the library.");
        btnUpdateStatus.setToolTipText("Change status or add a rating/review (Double-click a row to quick-access).");
        btnDelete.setToolTipText("Permanently remove the selected entry from your vault.");

        buttonPanel.add(btnLoadSample);
        buttonPanel.add(btnUpdateStatus);
        buttonPanel.add(btnDelete);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // --- UX Feature: Double-Click to Edit ---
        libraryTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2 && libraryTable.getSelectedRow() != -1) {
                    btnUpdateStatus.doClick(); // Simulates a button press
                }
            }
        });

        // --- Event Listeners ---
        btnLoadSample.addActionListener(e -> {
            // Updated to status 1 (In Progress) or 0 (Planned) to respect Model constraints
            controller.addVideoGame("Forza Horizon 5", "Racing", 1, 40, "Playground Games", "PC");
            controller.addVideoGame("Valorant", "Tactical Shooter", 1, 200, "Riot Games", "PC");
            controller.addAnime("Attack on Titan", "Action", 1, "MAPPA", "Hajime Isayama", 75, true);
            controller.addTVSeries("Breaking Bad", "Crime", 1, "Vince Gilligan", 62, false);
            controller.addMusicSingle("Nonsense", "Pop", 0, "Sabrina Carpenter", "Island");
            refreshTable();
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = libraryTable.getSelectedRow();
            if (selectedRow >= 0) {
                // Convert visual row to underlying model row to account for active search filters
                int modelRow = libraryTable.convertRowIndexToModel(selectedRow);
                List<MediaEntry> entries = controller.getAllEntries();
                MediaEntry selectedEntry = entries.get(modelRow);
                
                controller.removeEntry(selectedEntry);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Please select an entry to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnUpdateStatus.addActionListener(e -> {
            int selectedRow = libraryTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Please select a media entry from the table first.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Convert visual row to underlying model row
            int modelRow = libraryTable.convertRowIndexToModel(selectedRow);
            MediaEntry entry = controller.getAllEntries().get(modelRow);

            // UI Elements for the popup
            String[] statuses = {"Planned", "In Progress", "Completed"};
            JComboBox<String> statusCombo = new JComboBox<>(statuses);
            statusCombo.setSelectedIndex(entry.getStatus());

            JTextField ratingField = new JTextField(entry.getRating() > 0 ? String.valueOf(entry.getRating()) : "");
            JTextArea reviewArea = new JTextArea(4, 20);
            reviewArea.setText(entry.getReview() != null ? entry.getReview() : "");
            
            // Initial state based on current status
            boolean isCompleted = entry.getStatus() == 2;
            ratingField.setEnabled(isCompleted);
            reviewArea.setEnabled(isCompleted);

            // Dynamic Listener: Only enable rating/review if "Completed" is selected
            statusCombo.addActionListener(ev -> {
                boolean completedSelected = statusCombo.getSelectedIndex() == 2;
                ratingField.setEnabled(completedSelected);
                reviewArea.setEnabled(completedSelected);
                if (!completedSelected) {
                    ratingField.setText("");
                    reviewArea.setText("");
                }
            });

            Object[] dialogContent = {
                "Update Status for: " + entry.getTitle(), statusCombo,
                "Rating (1-10) [Completed Only]:", ratingField,
                "Review [Completed Only]:", new JScrollPane(reviewArea)
            };

            int option = JOptionPane.showConfirmDialog(this, dialogContent, "Update Media", JOptionPane.OK_CANCEL_OPTION);
            
            if (option == JOptionPane.OK_OPTION) {
                int newStatus = statusCombo.getSelectedIndex();
                controller.updateStatus(entry, newStatus); 

                // If completed, attempt to save the rating and review
                if (newStatus == 2) {
                    try {
                        if (!ratingField.getText().trim().isEmpty()) {
                            int rating = Integer.parseInt(ratingField.getText().trim());
                            controller.rate(entry, rating); 
                        }
                        if (!reviewArea.getText().trim().isEmpty()) {
                            controller.review(entry, reviewArea.getText().trim());
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Rating must be a whole number from 1 to 10.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                }
                refreshTable(); 
            }
        });

        return panel;
    }

    /**
     * Creates the Add Media tab with dynamic form inputs.
     */
    private JPanel createAddMediaPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top: Universal Fields
        JPanel topPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        topPanel.add(new JLabel("Media Type:"));
        String[] types = {"Video Game", "Anime", "TV Series", "Music Album", "Music Single"};
        JComboBox<String> typeCombo = new JComboBox<>(types);
        topPanel.add(typeCombo);

        topPanel.add(new JLabel("Title:"));
        JTextField titleField = new JTextField();
        topPanel.add(titleField);

        topPanel.add(new JLabel("Genre:"));
        JTextField genreField = new JTextField();
        topPanel.add(genreField);

        panel.add(topPanel, BorderLayout.NORTH);

        // Center: Dynamic Fields using CardLayout
        JPanel dynamicCards = new JPanel(new CardLayout());

        // Card 1: Video Game
        JPanel gameCard = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField playtimeField = new JTextField();
        JTextField devField = new JTextField();
        JTextField platformField = new JTextField();
        gameCard.add(new JLabel("Playtime (hrs):")); gameCard.add(playtimeField);
        gameCard.add(new JLabel("Developer:")); gameCard.add(devField);
        gameCard.add(new JLabel("Platform:")); gameCard.add(platformField);
        dynamicCards.add(gameCard, "Video Game");

        // Card 2: Anime
        JPanel animeCard = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField studioField = new JTextField();
        JTextField creatorField = new JTextField();
        JTextField episodesField = new JTextField();
        JCheckBox subbedBox = new JCheckBox("Is Subbed?");
        animeCard.add(new JLabel("Animation Studio:")); animeCard.add(studioField);
        animeCard.add(new JLabel("Creator:")); animeCard.add(creatorField);
        animeCard.add(new JLabel("No. of Episodes:")); animeCard.add(episodesField);
        animeCard.add(new JLabel("Format:")); animeCard.add(subbedBox);
        dynamicCards.add(animeCard, "Anime");

        // Card 3: TV Series
        JPanel tvCard = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField tvCreatorField = new JTextField();
        JTextField tvEpisodesField = new JTextField();
        JCheckBox realityBox = new JCheckBox("Is Reality TV?");
        tvCard.add(new JLabel("Creator:")); tvCard.add(tvCreatorField);
        tvCard.add(new JLabel("No. of Episodes:")); tvCard.add(tvEpisodesField);
        tvCard.add(new JLabel("Genre Modifier:")); tvCard.add(realityBox);
        dynamicCards.add(tvCard, "TV Series");

        // Card 4: Music Album
        JPanel albumCard = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField albumArtistField = new JTextField();
        JTextField albumLabelField = new JTextField();
        JTextField tracksField = new JTextField();
        albumCard.add(new JLabel("Artist:")); albumCard.add(albumArtistField);
        albumCard.add(new JLabel("Record Label:")); albumCard.add(albumLabelField);
        albumCard.add(new JLabel("No. of Tracks:")); albumCard.add(tracksField);
        dynamicCards.add(albumCard, "Music Album");

        // Card 5: Music Single
        JPanel singleCard = new JPanel(new GridLayout(2, 2, 10, 10));
        JTextField singleArtistField = new JTextField();
        JTextField singleLabelField = new JTextField();
        singleCard.add(new JLabel("Artist:")); singleCard.add(singleArtistField);
        singleCard.add(new JLabel("Record Label:")); singleCard.add(singleLabelField);
        dynamicCards.add(singleCard, "Music Single");

        panel.add(dynamicCards, BorderLayout.CENTER);

        // Listener to swap cards when type changes
        typeCombo.addActionListener(e -> {
            CardLayout cl = (CardLayout) (dynamicCards.getLayout());
            cl.show(dynamicCards, (String) typeCombo.getSelectedItem());
        });

        // Bottom: Submit Button
        JButton btnSubmit = new JButton("Add to Library");
        panel.add(btnSubmit, BorderLayout.SOUTH);

        btnSubmit.addActionListener(e -> {
            try {
                String title = titleField.getText().trim();
                String genre = genreField.getText().trim();
                String selectedType = (String) typeCombo.getSelectedItem();
                
                if (title.isEmpty() || genre.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Title and Genre are required.");
                    return;
                }

                if ("Video Game".equals(selectedType)) {
                    controller.addVideoGame(title, genre, 0, Integer.parseInt(playtimeField.getText().trim()), devField.getText(), platformField.getText());
                } else if ("Anime".equals(selectedType)) {
                    controller.addAnime(title, genre, 0, studioField.getText(), creatorField.getText(), Integer.parseInt(episodesField.getText().trim()), subbedBox.isSelected());
                } else if ("TV Series".equals(selectedType)) {
                    controller.addTVSeries(title, genre, 0, tvCreatorField.getText(), Integer.parseInt(tvEpisodesField.getText().trim()), realityBox.isSelected());
                } else if ("Music Album".equals(selectedType)) {
                    controller.addMusicAlbum(title, genre, 0, albumArtistField.getText(), albumLabelField.getText(), Integer.parseInt(tracksField.getText().trim()));
                } else if ("Music Single".equals(selectedType)) {
                    controller.addMusicSingle(title, genre, 0, singleArtistField.getText(), singleLabelField.getText());
                }
                
                // Clear universal fields and switch to library tab
                titleField.setText(""); genreField.setText("");
                JOptionPane.showMessageDialog(this, title + " added to library!");
                refreshTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please ensure numeric fields (like Playtime or Episodes) contain valid numbers.");
            }
        });

        return panel;
    }

    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea statsArea = new JTextArea();
        statsArea.setEditable(false);
        statsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        statsArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnRefreshStats = new JButton("Refresh Statistics");
        btnRefreshStats.addActionListener(e -> {
            var summary = controller.getSummary();
            statsArea.setText(String.format(
                "=== LIBRARY SUMMARY ===\n\n" +
                "Total Entries: %d\n\n" +
                "--- Status Breakdown ---\n" +
                "Planned:      %d\n" +
                "In Progress:  %d\n" +
                "Completed:    %d\n\n" +
                "--- Media Breakdown ---\n" +
                "Anime:        %d\n" +
                "TV Series:    %d\n" +
                "Video Games:  %d\n" +
                "Music Albums: %d\n" +
                "Singles:      %d\n\n" +
                "Average Rating (Completed): %.2f / 10",
                summary.total, summary.planned, summary.inProgress, summary.completed,
                summary.animeCount, summary.tvSeriesCount, summary.videoGameCount,
                summary.musicAlbumCount, summary.musicSingleCount,
                summary.averageRating
            ));
        });

        panel.add(new JScrollPane(statsArea), BorderLayout.CENTER);
        panel.add(btnRefreshStats, BorderLayout.SOUTH);
        return panel;
    }

    public void refreshTable() {
        tableModel.setRowCount(0); // Clear existing rows
        List<MediaEntry> entries = controller.getAllEntries();

        for (MediaEntry entry : entries) {
            String status = StatusMapper.getStatusString(entry.getStatus());
            String rating = (entry.getStatus() == 2 && entry.getRating() > 0) ? String.valueOf(entry.getRating()) : "N/A";
            
            tableModel.addRow(new Object[]{
                entry.getTypeLabel(),
                entry.getTitle(),
                entry.getGenre(),
                status,
                rating
            });
        }
    }
}
