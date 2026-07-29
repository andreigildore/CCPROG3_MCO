import java.util.ArrayList;

/**
 * Stores and manages all media entries in the user's library.
 */
public class Library {
    private ArrayList<MediaEntry> entries;

    public static class LibrarySummary {
        public int total, planned, inProgress, completed;
        public int animeCount, tvSeriesCount, videoGameCount, musicSingleCount, musicAlbumCount;
        public double averageRating;
    }
	
	public Library() {
        entries = new ArrayList<>();
	}

    /**
     * edit
     */
    public boolean addMedia(MediaEntry entry) {
        if (entry == null)
            return false;
        entries.add(entry);
        return true;
    }

    /**
     * edit
     */
    public boolean removeMedia(MediaEntry entry) {
        return entries.remove(entry);
    }

    /**
     * edit
     */
    public MediaEntry getMediaEntryByTitle(String title) {
        for (MediaEntry entry : entries) {
            if (entry.getTitle().equalsIgnoreCase(title)) {
                return entry;
            }
        }
        return null;
    }

    /**
     * Finds a media entry by type and title, then updates its status.
     * 
     * @param type the type of media to search for
     * @param mediaTitle the title of the media to update
     * @param newStatus the new status to set
     * Pre-condition: The type and mediaTitle are valid strings, newStatus is a valid status integer.
     * Post-condition: The status of the matching media entry is updated if found.
     */
    public boolean updateEntryStatus(String type, String mediaTitle, int newStatus) {
        MediaEntry entry = getMediaEntryByTitle(mediaTitle);
        if (entry == null)
            return false;
        return entry.updateStatus(newStatus);
    }
    
    /**
     * Displays all entries across every media type.
     * 
     * Pre-condition: The library has been initialized.
     * Post-condition: All entries in the library are printed to the console.
     */
    public void displayAllEntries() {
        for (MediaEntry entry : entries) 
            System.out.println(entry.displayInfo());
    }

    /**
     * Displays only entries that match the given status.
     *
     * @param status the status to filter by
     * Pre-condition: The status is a valid status integer.
     * Post-condition: All matching entries are printed to the console.
     */
    public void filterByStatus(int status) {
        for (MediaEntry entry : entries) {
            if (entry.getStatus() == status)
                System.out.println(entry.displayInfo());
        }
    }

    /**
     * Displays only entries of the given media type.
     *
     * @param type the type of media to filter by
     * Pre-condition: The type is a valid string.
     * Post-condition: All matching entries are printed to the console.
     */
    public void filterByType(String type) {
        for (MediaEntry entry : entries) {
            if (entry.getClass().getSimpleName().equals(type)) 
                System.out.println(entry.displayInfo());
        }
    }

    /**
     * Prints a summary of library stats including counts and average rating.
     *
     * Pre-condition: The library has been initialized.
     * Post-condition: A summary of the library is printed to the console.
     */
    public void displaySummary() {
        int total =  entries.size();
        int planned = 0; 
        int inProgress = 0;
        int completed = 0;
        int ratedCount = 0;
        double ratingSum = 0;

        for (MediaEntry entry : entries) {
            switch(entry.getStatus()) {
                case 0:
                    planned++;
                    break;
                case 1:
                    inProgress++;
                    break;
                case 2:
                    completed++;
                    if (entry.getRating() > 0) {
                        ratedCount++;
                        ratingSum += entry.getRating();
                    }
                    break;
            }
        }

    }
}

/*
// Average rating only from completed entries that have been rated.
        String avgRatingText = ratedCount == 0 ? "N/A" : String.format("%.2f", (double) ratingSum / ratedCount);

        System.out.println(String.format("""

            Library Summary 
        Total Entries   : %d
        Anime           : %d
        Video Games     : %d
        TV Series       : %d
        Music Singles   : %d
        Music Albums    : %d
        ------------------------
        Planned         : %d
        In Progress     : %d
        Completed       : %d
        ------------------------
        Avg Rating (Completed) : %s
        
        """,
        total,
        animes.size(),
        videoGames.size(),
        tvSeries.size(),
        musicSingles.size(),
        musicAlbums.size(),
        planned,
        inProgress,
        completed,
        avgRatingText
        ));
*/
