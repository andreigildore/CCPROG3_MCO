/**
 * represents a music single entry in the user's library
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
     * creates a new music single with default status of 0 (planned)
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
     * updates the status if the new value is within valid range
     */
    public void updateStatus(int newStatus) {
        if (newStatus >= 0 && newStatus <= 2)
            status = newStatus;
        else
            System.out.println("Invalid status.");
    }

    /**
     * rates the music single from 1-10
     */
    public void rate(int rating) {
        if (rating >= 1 && rating  <= 10) {
            // only completed media can be rated
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
     * adds a text review if the song is completed
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
     * returns a formatted string of the music single's details
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
