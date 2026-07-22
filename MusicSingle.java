/**
 * Represents a music single entry in the user's library.
 */
public class MusicSingle extends MediaEntry{
    private String artist;
    private String recordLabel;

    /**
     * Creates a new music single with default status of 0 (planned).
     * 
     * @param title the title of the music single
     * @param genre the genre of the music single
     * @param artist the artist of the music single
     * @param recordLabel the record label of the music single
     * Pre-condition: None.
     * Post-condition: A new MusicSingle object is created with a planned status.
     */
    public MusicSingle(String title, String genre, int initialStatus, String artist, String recordLabel) {
        super(title, genre, initialStatus);
        this.artist = artist;
        this.recordLabel = recordLabel;
    }

    @Override
    /**
     * Formats and returns a string including the MusicSingle's details.
     * 
     * @return a formatted string containing the music single's details
     * Pre-condition: None.
     * Post-condition: A formatted string representation of the music single is returned.
     */
    public String displayInfo() {
        /* 
        String ratingStr = (rating > 0) ? rating + "/10" : "NotRated";
        String reviewStr = (review != null && !review.isBlank()) ? review : "No review";
        return String.format("""
        Title  : %s
        Artist : %s
        Record Label : %s
        Genre : %s
        Status : %s
        Rating : %s
        Review : %s
        """,
        title,
        artist,
        recordLabel,
        genre,
        StatusMapper.getStatusString(status),
        ratingStr,
        reviewStr
        );
        */
       return "";
    }
}
