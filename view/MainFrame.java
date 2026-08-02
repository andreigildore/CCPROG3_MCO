package view;

import controller.LibraryController;
import model.MediaEntry;
import model.StatusMapper;
import model.LibrarySummary;

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

/**
 * The main GUI frame for the Media Vault application.
 * Contains tabbed panels for the library view, media addition form, and dashboard statistics.
 */
public class MainFrame extends JFrame {
    private final LibraryController controller;
    private DefaultTableModel tableModel;
    private JTable libraryTable;

    public MainFrame(LibraryController controller) {
        this.controller = controller;

        setTitle("Media Vault");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("My Library", createLibraryPanel());
        tabbedPane.addTab("Add Media", createAddMediaPanel());
        tabbedPane.addTab("Dashboard", createDashboardPanel());

        tabbedPane.setMnemonicAt(0, KeyEvent.VK_L); 
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_A); 
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_D); 

        add(tabbedPane, BorderLayout.CENTER);

        // Load any previously saved entries from file into the library
        controller.loadLibraryData();
        refreshTable();

        // Auto-save the library to file when the user closes the window
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                controller.saveLibraryData();
            }
        });
    }

    private JPanel createLibraryPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        searchPanel.add(new JLabel("Search Library:"), BorderLayout.WEST);
        JTextField searchField = new JTextField();
        searchPanel.add(searchField, BorderLayout.CENTER);
        panel.add(searchPanel, BorderLayout.NORTH);

        String[] columnNames = {"Type", "Title", "Genre", "Status", "Rating"};
        // Override isCellEditable to prevent users from directly editing table cells
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        
        libraryTable = new JTable(tableModel);
        libraryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        libraryTable.getTableHeader().setReorderingAllowed(false);
        
        // TableRowSorter enables column sorting and row filtering on the table
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        libraryTable.setRowSorter(sorter);
        
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            private void updateFilter() {
                String text = searchField.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null); // Show all rows when search is empty
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text)); 
                }
            }
            @Override public void insertUpdate(DocumentEvent e) { updateFilter(); }
            @Override public void removeUpdate(DocumentEvent e) { updateFilter(); }
            @Override public void changedUpdate(DocumentEvent e) { updateFilter(); }
        });

        // Custom cell renderer for the "Status" column (index 3).
        libraryTable.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = (String) value;
                
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(245, 245, 250) : Color.WHITE); // Alternating row colors
                    
                    if ("Completed".equals(status)) {
                        c.setForeground(new Color(34, 139, 34)); // Green
                        c.setFont(c.getFont().deriveFont(Font.BOLD));
                    } else if ("In Progress".equals(status)) {
                        c.setForeground(new Color(204, 102, 0)); // Orange
                        c.setFont(c.getFont().deriveFont(Font.BOLD));
                    } else {
                        c.setForeground(Color.GRAY); // Planned
                        c.setFont(c.getFont().deriveFont(Font.PLAIN));
                    }
                } else {
                    c.setForeground(table.getSelectionForeground()); 
                    c.setBackground(table.getSelectionBackground());
                }
                return c;
            }
        });

        panel.add(new JScrollPane(libraryTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnLoadSample = new JButton("Load Sample Data");
        JButton btnUpdateStatus = new JButton("Update Status / Rate");
        JButton btnDelete = new JButton("Delete Selected");

        buttonPanel.add(btnLoadSample);
        buttonPanel.add(btnUpdateStatus);
        buttonPanel.add(btnDelete);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Double-clicking a row triggers the "Update Status / Rate" dialog
        libraryTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2 && libraryTable.getSelectedRow() != -1) {
                    btnUpdateStatus.doClick(); 
                }
            }
        });

        btnLoadSample.addActionListener(e -> {
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
                int confirm = JOptionPane.showConfirmDialog(
                    this, 
                    "Are you sure you want to permanently delete this media entry?", 
                    "Confirm Deletion", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE
                );
                
                if (confirm == JOptionPane.YES_OPTION) {
                    // Convert the visual row index to the model index (they differ when sorted/filtered)
                    int modelRow = libraryTable.convertRowIndexToModel(selectedRow);
                    MediaEntry selectedEntry = controller.getAllEntries().get(modelRow);
                    controller.removeEntry(selectedEntry);
                    refreshTable();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select an entry to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnUpdateStatus.addActionListener(e -> {
            int selectedRow = libraryTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Please select a media entry.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Convert visual row to model row to get the correct entry despite sorting/filtering
            int modelRow = libraryTable.convertRowIndexToModel(selectedRow);
            MediaEntry entry = controller.getAllEntries().get(modelRow);

            String[] statuses = {"Planned", "In Progress", "Completed"};
            JComboBox<String> statusCombo = new JComboBox<>(statuses);
            statusCombo.setSelectedIndex(entry.getStatus());

            JTextField ratingField = new JTextField(entry.getRating() > 0 ? String.valueOf(entry.getRating()) : "");
            JTextArea reviewArea = new JTextArea(4, 20);
            reviewArea.setText(entry.getReview() != null ? entry.getReview() : "");
            
            boolean isCompleted = entry.getStatus() == 2;
            ratingField.setEnabled(isCompleted);
            reviewArea.setEnabled(isCompleted);

            statusCombo.addActionListener(ev -> {
                boolean completedSelected = statusCombo.getSelectedIndex() == 2;
                ratingField.setEnabled(completedSelected);
                reviewArea.setEnabled(completedSelected);
                if (!completedSelected) {
                    ratingField.setText(""); // Clear rating/review if status changed away from Completed
                    reviewArea.setText("");
                }
            });

            // Build the dialog content array
            Object[] dialogContent;
            
            if (entry instanceof model.Episodic) {
                JButton btnRateEpisodes = new JButton("Rate Individual Episodes");
                btnRateEpisodes.addActionListener(ev -> openEpisodicRatingDialog((model.Episodic) entry));
                
                dialogContent = new Object[] {
                    "Update Status for: " + entry.getTitle(), statusCombo,
                    "Overall Rating (1-10) [Completed Only]:", ratingField,
                    "Review [Completed Only]:", new JScrollPane(reviewArea),
                    " ", btnRateEpisodes 
                };
            } else {
                dialogContent = new Object[] {
                    "Update Status for: " + entry.getTitle(), statusCombo,
                    "Rating (1-10) [Completed Only]:", ratingField,
                    "Review [Completed Only]:", new JScrollPane(reviewArea)
                };
            }

            if (JOptionPane.showConfirmDialog(this, dialogContent, "Update Media", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                int newStatus = statusCombo.getSelectedIndex();
                controller.updateStatus(entry, newStatus); 

                // Only save rating and review if the entry is being marked as Completed.
                // Status must be set first so setRating/setReview precondition checks pass.
                if (newStatus == 2) {
                    try {
                        if (!ratingField.getText().trim().isEmpty()) {
                            controller.rate(entry, Integer.parseInt(ratingField.getText().trim())); 
                        }
                        if (!reviewArea.getText().trim().isEmpty()) {
                            controller.review(entry, reviewArea.getText().trim());
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Rating must be a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                }
                refreshTable(); 
            }
        });

        return panel;
    }

    private void openEpisodicRatingDialog(model.Episodic media) {
        JDialog dialog = new JDialog(this, "Rate Episodes", true);
        dialog.setSize(350, 450);
        dialog.setLocationRelativeTo(this);

        String[] cols = {"Episode Number", "Rating (1-10)"};
        int epCount = media.getEpisodeCount();
        int[] ratings = media.getEpisodeRatings();

        // Only the "Rating" column (index 1) is editable; episode numbers are read-only
        DefaultTableModel epModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1; 
            }
        };

        for (int i = 0; i < epCount; i++) {
            String existingRating = (ratings[i] > 0) ? String.valueOf(ratings[i]) : "";
            epModel.addRow(new Object[]{"Episode " + (i + 1), existingRating});
        }

        JTable epTable = new JTable(epModel);
        epTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE); // Commit edits when clicking away from a cell
        dialog.add(new JScrollPane(epTable), BorderLayout.CENTER);

        JButton btnSave = new JButton("Save Episode Ratings");
        btnSave.addActionListener(e -> {
            // Force-commit any cell that is still being edited when the user clicks Save
            if (epTable.isEditing()) {
                epTable.getCellEditor().stopCellEditing(); 
            }
            for (int i = 0; i < epCount; i++) {
                Object val = epModel.getValueAt(i, 1);
                if (val != null && !val.toString().trim().isEmpty()) {
                    try {
                        int r = Integer.parseInt(val.toString().trim());
                        controller.rateEpisode(media, i, r);
                    } catch (NumberFormatException ex) {
                        // Ignores bad input
                    }
                } else {
                    controller.rateEpisode(media, i, 0); 
                }
            }
            dialog.dispose();
            refreshTable();
        });

        dialog.add(btnSave, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private JPanel createAddMediaPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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

        // CardLayout stacks multiple panels (one per media type) and shows only the active one.
        // Each card is registered with its media type name as the key.
        JPanel dynamicCards = new JPanel(new CardLayout());

        JPanel gameCard = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField playtimeField = new JTextField();
        JTextField devField = new JTextField();
        JTextField platformField = new JTextField();
        gameCard.add(new JLabel("Playtime (hrs):")); gameCard.add(playtimeField);
        gameCard.add(new JLabel("Developer:")); gameCard.add(devField);
        gameCard.add(new JLabel("Platform:")); gameCard.add(platformField);
        dynamicCards.add(gameCard, "Video Game");

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

        JPanel tvCard = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField tvCreatorField = new JTextField();
        JTextField tvEpisodesField = new JTextField();
        JCheckBox realityBox = new JCheckBox("Is Reality TV?");
        tvCard.add(new JLabel("Creator:")); tvCard.add(tvCreatorField);
        tvCard.add(new JLabel("No. of Episodes:")); tvCard.add(tvEpisodesField);
        tvCard.add(new JLabel("Genre Modifier:")); tvCard.add(realityBox);
        dynamicCards.add(tvCard, "TV Series");

        JPanel albumCard = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField albumArtistField = new JTextField();
        JTextField albumLabelField = new JTextField();
        JTextField tracksField = new JTextField();
        albumCard.add(new JLabel("Artist:")); albumCard.add(albumArtistField);
        albumCard.add(new JLabel("Record Label:")); albumCard.add(albumLabelField);
        albumCard.add(new JLabel("No. of Tracks:")); albumCard.add(tracksField);
        dynamicCards.add(albumCard, "Music Album");

        JPanel singleCard = new JPanel(new GridLayout(2, 2, 10, 10));
        JTextField singleArtistField = new JTextField();
        JTextField singleLabelField = new JTextField();
        singleCard.add(new JLabel("Artist:")); singleCard.add(singleArtistField);
        singleCard.add(new JLabel("Record Label:")); singleCard.add(singleLabelField);
        dynamicCards.add(singleCard, "Music Single");

        panel.add(dynamicCards, BorderLayout.CENTER);

        // Switch the visible card panel when the user selects a different media type
        typeCombo.addActionListener(e -> {
            CardLayout cl = (CardLayout) (dynamicCards.getLayout());
            cl.show(dynamicCards, (String) typeCombo.getSelectedItem());
        });

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
                
                titleField.setText(""); genreField.setText("");
                JOptionPane.showMessageDialog(this, title + " added to library!");
                refreshTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ensure numeric fields contain valid numbers.");
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

        updateDashboardText(statsArea);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnRefreshStats = new JButton("Refresh Statistics");
        JButton btnExportStats = new JButton("Export Report to TXT");

        btnRefreshStats.addActionListener(e -> updateDashboardText(statsArea));
        
        btnExportStats.addActionListener(e -> {
            if (controller.exportDashboardReport()) {
                JOptionPane.showMessageDialog(this, "Report exported successfully as MediaVault_Report.txt");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to export report.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(btnRefreshStats);
        buttonPanel.add(btnExportStats);

        panel.add(new JScrollPane(statsArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void updateDashboardText(JTextArea statsArea) {
        LibrarySummary summary = controller.getSummary();
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
    }

    public void refreshTable() {
        tableModel.setRowCount(0); // Clear all existing rows before re-populating
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
