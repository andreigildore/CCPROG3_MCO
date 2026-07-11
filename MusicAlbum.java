import java.util.ArrayList;

/**
 * represents a music album entry in the user's media list
 */
public class MusicAlbum {
    private String title;
    private String genre;
    private int status;
    private int rating;
    private String review;
    private String artist;
    private String recordLabel;
    private ArrayList<Track> tracks;
    private int noOfTracks;

    /**
     * creates a music album with its details and generates track objects
     */
    public MusicAlbum(String title, int noOfTracks, String genre, String artist, String recordLabel) {
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.status = 0;
        this.tracks = new ArrayList<>();
        // generate track objects numbered 1 to noOfTracks
        for (int i = 1; i <= noOfTracks; i++) {
            Track track = new Track(i);
            tracks.add(track);
        }
        this.noOfTracks = tracks.size();
    }

    public String getTitle() {
        return this.title;
    }

    public int getStatus() {
        return this.status;
    }

    public int getRating() {
        return this.rating;
    }

    public String getReview() {
        return this.review;
    }

    /**
     * updates the listening status of the album
     */
    public void updateStatus(int newStatus) {
        if(newStatus >= 0 && newStatus <= 2)
            status = newStatus;
        else
            System.out.println("Invalid status.");
    }

    /**
     * marks all tracks up to the given track number as listened
     */
    public void updateProgress(int trackInput) {
        // mark all tracks from 1 up to trackInput as listened
        for (Track track : tracks) {
            if (track.getTrackNumber() <= trackInput)
                track.markListened();
        }
        System.out.println("Updated listened tracks to track " + trackInput + ".");
    }

    /**
     * prints all tracks and their details
     */
    public void displayTracks() {
        System.out.println("Tracks for " + title + "\n\n");
        for (Track track : tracks)
            System.out.println(track.displayInfo());
    }

    /**
     * finds and returns a track by its number, or null if not found
     */
    public Track findTrack(int trackNumber) {
        for (Track track : tracks) {
            if (track.getTrackNumber() == trackNumber)
                return track;
        }
        return null;
    }

    /**
     * rates a specific track by its number
     */
    public void rateTrack(int trackNumber, int rating) {
        Track track = findTrack(trackNumber);
        if (track != null) {
            track.rate(rating);
        }
        else {
            System.out.println("Track " + trackNumber + " not found in " + title + ".");
        }
    }

    /**
     * toggles the favorite status of a specific track
     */
    public void favoriteTrack(int trackNumber) {
        Track track = findTrack(trackNumber);
        if (track != null) {
            track.toggleFavorite();
        }
        else {
            System.out.println("Track " + trackNumber + " not found in " + title + ".");
        }
    }

    /**
     * rates the album from 1-10, only if completed
     */
    public void rate(int rating) {
        if (rating >= 1 && rating  <= 10) {
            // only completed media can be rated
            if (this.status == 2) {
                this.rating = rating;
                System.out.println("Rating saved successfully.");
            } else {
                System.out.println("Cannot rate an unfinished album.");
            }
        }
        else
            System.out.println("Invalid rating score");
    }

    /**
     * adds a text review for the album, only if completed
     */
    public void review(String text) {
        if (text != null && !text.isBlank()){
            // only completed media can be reviewed
            if (this.status == 2) {
                this.review = text;
                System.out.println("Review saved successfully.");
            } else {
                System.out.println("Cannot review an unfinished album.");
            }
        }
        else
            System.out.println("Review has no content or is null");
    }

    /**
     * returns a formatted string of all album details
     */
    public String displayInfo() {
        String ratingStr = (rating > 0) ? rating + "/10" : "NotRated";
        String reviewStr = (review != null && !review.isBlank()) ? review : "No review";
        return String.format("""
        Title  : %s
        Artist : %s
        Record Label : %s
        Genre: %s
        No. Of Tracks : %d
        Status : %s
        Rating : %s
        Review : %s
        """,
        title,
        artist,
        recordLabel,
        genre,
        noOfTracks,
        StatusMapper.getStatus(status),
        ratingStr,
        reviewStr
        );
    }
    
}
