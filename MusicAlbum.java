/**
 * Represents a music album entry in the user's media list.
 */
public class MusicAlbum extends EpisodicMedia{
    private String artist;
    private String recordLabel;

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
    public MusicAlbum(String title, int noOfTracks, String genre, int initialstatus, String artist, String recordLabel) {
        super(title, genre, initialstatus, noOfTracks, "Track");
        this.artist = artist;
        this.recordLabel = recordLabel;
    }

    @Override
    /**
     * Formats and returns a string containing details of the album
     * 
     * @return a formatted string containing the album's details
     * Pre-condition: None.
     * Post-condition: A string with the album's information is returned.
     */
    public String displayInfo() {
        /* 
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
        */
       return "";
    }
    
}
