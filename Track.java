public class Track {
    private final int trackNumber;
    private int rating;
    private boolean isFavorite;
    private boolean isListenedTo;

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

    public void markListened() {
        isListenedTo = true;
    }

    public void rate (int rating) {
        if (rating >= 1 && rating  <= 10) {
            if (isListenedTo) {
                this.rating = rating;
                System.out.println("Track rated succesfully.");
            }
            else
                System.out.println("Track has not been listend to.");
        }
        else
            System.out.println("Invalid rating score");
    }

    public void toggleFavorite() {
        isFavorite = !isFavorite;
        System.out.println(isFavorite ? "Track marked as favorite." : "Track removed from favorites.");
    }

    public String displayInfo() {
        return String.format("""
        Track % -3d Listened : %3s Favorite : %3s Rating : $s
        """,
        trackNumber,
        isListenedTo,
        isFavorite,
        rating >= 1 ? rating + "/10" : "Not rated"
        );
    }
}
