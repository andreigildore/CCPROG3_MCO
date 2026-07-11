/**
 * represents a single track in a music album
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
     * marks this track as listened to
     */
    public void markListened() {
        isListenedTo = true;
    }

    /**
     * rates the track from 1-10
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

    public void toggleFavorite() {
        isFavorite = !isFavorite;
        System.out.println(isFavorite ? "Track marked as favorite." : "Track removed from favorites.");
    }

    /**
     * returns a formatted string of the track's details
     */
    public String displayInfo() {
        return String.format("""
        Track %-3d Listened : %3s Favorite : %3s Rating : %s
        """,
        trackNumber,
        isListenedTo ? "Yes" : "No",
        isFavorite ? "Yes" : "No",
        rating >= 1 ? rating + "/10" : "Not rated"
        );
    }
}
