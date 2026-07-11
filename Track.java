/**
 * Represents a single track in a music album.
 */
public class Track {
    private final int trackNumber;
    private int rating;
    private boolean isFavorite;
    private boolean isListenedTo;

    /**
     * creates a new unlistened, unrated track
     */
    public Track(int trackNumber) {
        this.trackNumber = trackNumber;
        this.rating = 0;
        this.isFavorite = false;
        this.isListenedTo = false;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public int getRating() {
        return rating;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public boolean hasListenedTo() {
        return isListenedTo;
    }

    /**
     * 
     * Marks this track as listened to.
     * 
     * Pre-condition: None.
     * Post-condition: isListenedTo is set to true.
     */
    public void markListened() {
        isListenedTo = true;
    }

    /**
     * Rates the track from 1-10.
     * 
     * @param rating the rating score to assign
     * Pre-condition: rating must be between 1 and 10, and the track must have been listened to.
     * Post-condition: The track rating is updated if conditions are met.
     */
    public void rate (int rating) {
        if (rating >= 1 && rating  <= 10) {
            // must be listened to before rating
            if (isListenedTo) {
                this.rating = rating;
                System.out.println("Track rated successfully.");
            }
            else
                System.out.println("Track has not been listened to.");
        }
        else
            System.out.println("Invalid rating score");
    }

    /**
     * Toggles the favorite status of the track.
     * 
     * Pre-condition: None.
     * Post-condition: isFavorite is set between true and false, depending on its state beforehand.
     */
    public void toggleFavorite() {
        isFavorite = !isFavorite;
        System.out.println(isFavorite ? "Track marked as favorite." : "Track removed from favorites.");
    }

    /**
     * Returns a formatted string of the track's details.
     * 
     * @return a formatted string containing all details of the track
     * Pre-condition: None.
     * Post-condition: Returns the formatted string.
     */
    public String displayInfo() {
        return String.format("""
        Track %-3d | Listened : %3s | Favorite : %3s | Rating : %s
        """,
        trackNumber,
        isListenedTo ? "Yes" : "No",
        isFavorite ? "Yes" : "No",
        rating >= 1 ? rating + "/10" : "Not rated"
        );
    }
}
