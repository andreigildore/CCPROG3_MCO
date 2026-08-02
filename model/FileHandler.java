package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles file operations for saving, loading, and exporting library data.
 */
public class FileHandler {
    private static final String FILE_NAME = "library_data.txt";
    private static final String REPORT_NAME = "MediaVault_Report.txt";

    /**
     * Saves the list of media entries to a file using object serialization.
     * 
     * @param entries the list of media entries to save
     * Pre-condition: entries must be a non-null list of serializable MediaEntry objects.
     * Post-condition: The list is serialized and written to the file.
     */
    public static void saveLibrary(List<MediaEntry> entries) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(entries);
            System.out.println("Library successfully saved.");
        } catch (IOException e) {
            System.err.println("Error saving library data: " + e.getMessage());
        }
    }

    /**
     * Loads the list of media entries from a file using object deserialization.
     * 
     * @return the list of loaded media entries, or an empty list if the file does not exist or an error occurs
     * Pre-condition: The file, if it exists, must contain a valid serialized list of MediaEntry objects.
     * Post-condition: The deserialized list is returned, or an empty list if loading fails.
     */
    @SuppressWarnings("unchecked")
    public static List<MediaEntry> loadLibrary() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>(); 
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<MediaEntry>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading library data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Exports a summary report of the library to a text file.
     * 
     * @param summary the LibrarySummary object containing the statistics to export
     * @return true if the report was exported successfully, false otherwise
     * Pre-condition: summary must be a non-null LibrarySummary with computed statistics.
     * Post-condition: A formatted text report is written to the report file.
     */
    public static boolean exportSummaryReport(LibrarySummary summary) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(REPORT_NAME))) {
            writer.println("=== MEDIA VAULT : LIBRARY REPORT ===");
            writer.println();
            writer.println("Total Entries: " + summary.total);
            writer.println();
            writer.println("--- Status Breakdown ---");
            writer.println("Planned:      " + summary.planned);
            writer.println("In Progress:  " + summary.inProgress);
            writer.println("Completed:    " + summary.completed);
            writer.println();
            writer.println("--- Media Breakdown ---");
            writer.println("Anime:        " + summary.animeCount);
            writer.println("TV Series:    " + summary.tvSeriesCount);
            writer.println("Video Games:  " + summary.videoGameCount);
            writer.println("Music Albums: " + summary.musicAlbumCount);
            writer.println("Singles:      " + summary.musicSingleCount);
            writer.println();
            writer.printf("Average Rating (Completed): %.2f / 10%n", summary.averageRating);
            return true;
        } catch (IOException e) {
            System.err.println("Error exporting report: " + e.getMessage());
            return false;
        }
    }
}
