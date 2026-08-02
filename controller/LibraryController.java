package controller;

import model.*;
import java.util.List;

/**
 * Handles all library-related operations as the intermediary between the view and the model.
 * Provides methods for adding, removing, updating, and persisting media entries.
 */
public class LibraryController {
    private final Library library;

    public LibraryController(Library library) {
        this.library = library;
    }

    public void addVideoGame(String title, String genre, int status, int playtime, String dev, String platform) {
        library.addEntry(new VideoGame(title, genre, status, playtime, dev, platform));
    }

    public void addAnime(String title, String genre, int status, String studio, String creator, int episodes, boolean isSubbed) {
        library.addEntry(new Anime(title, genre, status, studio, creator, episodes, isSubbed));
    }

    public void addTVSeries(String title, String genre, int status, String creator, int episodes, boolean isRealityTV) {
        library.addEntry(new TVSeries(title, genre, status, creator, episodes, isRealityTV));
    }

    public void addMusicAlbum(String title, String genre, int status, String artist, String label, int tracks) {
        library.addEntry(new MusicAlbum(title, genre, status, artist, label, tracks));
    }

    public void addMusicSingle(String title, String genre, int status, String artist, String label) {
        library.addEntry(new MusicSingle(title, genre, status, artist, label));
    }

    public void removeEntry(MediaEntry entry) {
        library.removeEntry(entry);
    }

    public List<MediaEntry> getAllEntries() {
        return library.getAllEntries();
    }

    public void updateStatus(MediaEntry entry, int newStatus) {
        entry.setStatus(newStatus);
    }

    public void rate(MediaEntry entry, int rating) {
        entry.setRating(rating);
    }

    public void review(MediaEntry entry, String review) {
        entry.setReview(review);
    }

    public void rateEpisode(Episodic entry, int episodeIndex, int rating) {
        entry.setEpisodeRating(episodeIndex, rating);
    }

    public void saveLibraryData() {
        FileHandler.saveLibrary(library.getAllEntries());
    }

    /**
     * Loads previously saved library data from file and adds all entries to the current library.
     * 
     * Pre-condition: The data file, if it exists, must contain valid serialized MediaEntry objects.
     * Post-condition: All loaded entries are added to the library. If no file exists, the library remains unchanged.
     */
    public void loadLibraryData() {
        List<MediaEntry> savedData = FileHandler.loadLibrary();
        if (savedData != null) {
            for (MediaEntry entry : savedData) {
                library.addEntry(entry);
            }
        }
    }

    public boolean exportDashboardReport() {
        return FileHandler.exportSummaryReport(getSummary());
    }

    /**
     * Computes and returns a summary of the library's current statistics.
     * 
     * @return a LibrarySummary containing counts by status and media type, and the average rating of completed entries
     * Pre-condition: The library must be initialized.
     * Post-condition: A new LibrarySummary object is returned with all fields populated.
     */
    public LibrarySummary getSummary() {
        LibrarySummary summary = new LibrarySummary();
        List<MediaEntry> entries = library.getAllEntries();
        summary.total = entries.size();
        
        int totalRating = 0;
        int ratedItems = 0;

        // Single pass through all entries to tally status counts, media type counts, and ratings
        for (MediaEntry e : entries) {
            // Count entries by status
            if (e.getStatus() == 0) summary.planned++;
            else if (e.getStatus() == 1) summary.inProgress++;
            else if (e.getStatus() == 2) {
                summary.completed++;
                // Only include rated completed entries in the average calculation
                if (e.getRating() > 0) {
                    totalRating += e.getRating();
                    ratedItems++;
                }
            }

            // Count entries by media type using instanceof checks
            if (e instanceof Anime) summary.animeCount++;
            else if (e instanceof TVSeries) summary.tvSeriesCount++;
            else if (e instanceof VideoGame) summary.videoGameCount++;
            else if (e instanceof MusicAlbum) summary.musicAlbumCount++;
            else if (e instanceof MusicSingle) summary.musicSingleCount++;
        }
        
        // Avoid division by zero when no items have been rated
        summary.averageRating = ratedItems > 0 ? (double) totalRating / ratedItems : 0.0;
        return summary;
    }
}
