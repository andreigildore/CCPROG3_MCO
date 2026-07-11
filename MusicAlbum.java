import java.util.ArrayList;

/**
 * Represents a music album entry in the user's media list.
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
     * Creates an album with its details and generates track objects.
     * 
     * @param title the title of the album
     * @param noOfTracks the total number of tracks
     * @param genre the genre of the album
     * @param artist the artist of the album
     * @param recordLabel the record label of the album
     * Pre-condition: Title, genre, artist, and recordLabel are valid strings. noOfTracks is a positive integer.
     * Post-condition: A MusicAlbum object is created with the specified details, and track objects are generated.
     */
    public MusicAlbum(String title, int noOfTracks, String genre, String artist, String recordLabel) {
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.status = 0;
        this.tracks = new ArrayList<>();
        // Generate track objects numbered 1 to noOfTracks.
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
     * Updates the listening status of the album.
     * 
     * @param newStatus the new status code
     * Pre-condition: newStatus should ideally be between 0 and 2.
     * Post-condition: The status is updated if valid, otherwise an error message is printed.
     */
    public void updateStatus(int newStatus) {
        if(newStatus >= 0 && newStatus <= 2)
            status = newStatus;
        else
            System.out.println("Invalid status.");
    }

    /**
     * Marks all tracks up to the given track number as listened.
     * 
     * @param trackInput the track number up to which to mark as listened
     * Pre-condition: trackInput is a valid track number.
     * Post-condition: Tracks from 1 to trackInput are marked as listened.
     */
    public void updateProgress(int trackInput) {
        // Mark all tracks from 1 up to trackInput as listened.
        for (Track track : tracks) {
            if (track.getTrackNumber() <= trackInput)
                track.markListened();
        }
        System.out.println("Updated listened tracks to track " + trackInput + ".");
    }

    /**
     * Prints all tracks and their details.
     * 
     * Pre-condition: The album has a list of tracks.
     * Post-condition: The details of all tracks are printed to the console.
     */
    public void displayTracks() {
        System.out.println("Tracks for " + title + "\n\n");
        for (Track track : tracks)
            System.out.println(track.displayInfo());
    }

    /**
     * Finds and returns a track by its number, or null if not found.
     * 
     * @param trackNumber the number of the track to find
     * @return the Track object if found, or null if not found
     * Pre-condition: trackNumber is a valid integer.
     * Post-condition: The corresponding Track object is returned, or null if it does not exist.
     */
    public Track findTrack(int trackNumber) {
        for (Track track : tracks) {
            if (track.getTrackNumber() == trackNumber)
                return track;
        }
        return null;
    }

    /**
     * Rates a specific track by its number.
     * 
     * @param trackNumber the number of the track to rate
     * @param rating the rating to assign to the track
     * Pre-condition: trackNumber corresponds to an existing track.
     * Post-condition: The specified track is rated if it exists.
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
     * Toggles the favorite status of a specific track.
     * 
     * @param trackNumber the number of the track to favorite or unfavorite
     * Pre-condition: trackNumber corresponds to an existing track.
     * Post-condition: The favorite status of the specified track is toggled.
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
     * Rates the album from 1 to 10, only if completed.
     * 
     * @param rating the rating score to assign
     * Pre-condition: The album status must be 2 (Completed), and rating must be between 1 and 10.
     * Post-condition: The rating is saved successfully if conditions are met.
     */
    public void rate(int rating) {
        if (rating >= 1 && rating  <= 10) {
            // Only completed media can be rated.
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
     * Adds a text review for the album, only if completed and left unblanked.
     * 
     * @param text the review content
     * Pre-condition: The album status must be 2 (Completed), and the text must not be null or blank.
     * Post-condition: The review is saved successfully if conditions are met.
     */
    public void review(String text) {
        if (text != null && !text.isBlank()){
            // Only completed media can be reviewed.
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
     * Formats and returns a string containing details of the album
     * 
     * @return a formatted string containing the album's details
     * Pre-condition: None.
     * Post-condition: A string with the album's information is returned.
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
