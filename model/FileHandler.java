package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "library_data.txt";
    private static final String REPORT_NAME = "MediaVault_Report.txt";

    public static void saveLibrary(List<MediaEntry> entries) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(entries);
            System.out.println("Library successfully saved.");
        } catch (IOException e) {
            System.err.println("Error saving library data: " + e.getMessage());
        }
    }

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
