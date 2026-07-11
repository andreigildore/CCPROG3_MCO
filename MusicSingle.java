/**
 * Represents a music single entry in the user's library.
 */
public class MusicSingle {
    private String title;
    private String genre;
    private int rating;
    private String review;
    private String artist;
    private String recordLabel;
    private int status;

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
    public MusicSingle(String title, String genre, String artist, String recordLabel) {
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.status = 0;
    }

    public String getTitle() {
        return title;
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
     * Updates the status if the new value is within valid range.
     * 
     * @param newStatus the new status to be set
     * Pre-condition: newStatus is between 0 and 2.
     * Post-condition: The status is updated to newStatus if valid, otherwise an error message is printed.
     */
    public void updateStatus(int newStatus) {
        if (newStatus >= 0 && newStatus <= 2)
            status = newStatus;
        else
            System.out.println("Invalid status.");
    }

    /**
     * Rates the music single from 1-10.
     * 
     * @param rating the rating score from 1 to 10
     * Pre-condition: The rating must be between 1 and 10, and the status must be completed (2).
     * Post-condition: The rating is saved if valid, otherwise an error message is printed.
     */
    public void rate(int rating) {
        if (rating >= 1 && rating  <= 10) {
            // Only completed media can be rated.
            if (this.status == 2) {
                this.rating = rating;
                System.out.println("Rating saved successfully.");
            }
            else
                System.out.println("Cannot rate an unfinished song");
        }
        else
            System.out.println("Invalid rating score");
    }

    /**
     * Adds a text review if the song is completed.
     * 
     * @param text the review text
     * Pre-condition: The text must not be null or blank, and the status must be completed (2).
     * Post-condition: The review is saved if valid, otherwise an error message is printed.
     */
     public void review(String text) {
        if (text != null && !text.isBlank()) {
            if (this.status == 2) {
                this.review = text;
                System.out.println("Review saved successfully.");
            }
            else
                System.out.println("Cannot review an unfinished song.");    
        }
        else
            System.out.println("Review has no content or is null");
    }

    /**
     * Formats and returns a string including the MusicSingle's details.
     * 
     * @return a formatted string containing the music single's details
     * Pre-condition: None.
     * Post-condition: A formatted string representation of the music single is returned.
     */
    public String displayInfo() {
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
        StatusMapper.getStatus(status),
        ratingStr,
        reviewStr
        );
    }
}
