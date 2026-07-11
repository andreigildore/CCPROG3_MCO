/**
 * Represents a video game entry in the user's library.
 */
public class VideoGame {
    private String title;
    private String genre;
    private int status;
    private int rating;
    private String review;
    private int playtime;
    private String developer;
    private String platform;

    /**
     * Creates a new video game with default status of 0 (planned).
     * 
     * @param title the title of the video game
     * @param genre the genre of the video game
     * @param playtime the playtime of the video game in hours
     * @param developer the developer/developer team of the video game
     * @param platform the platform of the video game
     * Pre-condition: None.
     * Post-condition: A new VideoGame object is created with a planned status.
     */
    public VideoGame(String title, String genre, int playtime, String developer, String platform) {
        this.title = title;
        this.genre = genre;
        this.playtime = playtime;
        this.developer = developer;
        this.platform = platform;
        this.status = 0;
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
     * Updates the status if the new value is within valid range.
     * 
     * @param newStatus the new status to be set
     * Pre-condition: newStatus is between 0 and 2.
     * Post-condition: The status is updated to newStatus if valid, otherwise an error message is printed.
     */
    public void updateStatus(int newStatus) {
        if(newStatus >= 0 && newStatus <= 2)
            status = newStatus;
        else
            System.out.println("Invalid status.");
    }

    /**
     * Rates the video game from 1-10.
     * 
     * @param rating the rating score from 1 to 10
     * Pre-condition: The rating must be between 1 and 10, and the status must be completed (2).
     * Post-condition: The rating is saved if valid, otherwise an error message is printed.
     */
    public void rate(int rating) {
        if (rating >= 1 && rating <= 10) {
            // Only completed media can be rated.
            if (this.status == 2) {
                this.rating = rating;
                System.out.println("Rating saved successfully.");
            } else {
                System.out.println("Cannot rate an unfinished Video Game.");
            }
        }
        else
            System.out.println("Invalid rating input");
    }

    /**
     * Adds a text review if the game is completed.
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
            } else {
                System.out.println("Cannot review an unfinished Video Game.");
            }
        }
        else
            System.out.println("Review has no content or is null");
    }

    /**
     * Adds hours to the total playtime.
     * 
     * @param additionalPlaytime the additional playtime in hours
     * Pre-condition: The additionalPlaytime must be greater than 0.
     * Post-condition: The total playtime is increased by the additionalPlaytime.
     */
    public void addPlaytime(int additionalPlaytime) {
        if (additionalPlaytime > 0) {
            this.playtime += additionalPlaytime;
            System.out.println("Playtime updated. Total: " + this.playtime + " hrs.");
        } else {
            System.out.println("Invalid playtime amount.");
        }
    }

    /**
     * Returns a formatted string of the video game's details.
     * 
     * @return a formatted string containing the video game's details
     * Pre-condition: None.
     * Post-condition: A formatted string representation of the video game is returned.
     */
    public String displayInfo() {
        String ratingStr = (rating > 0) ? rating + "/10" : "NotRated";
        String reviewStr = (review != null && !review.isBlank()) ? review : "No review";
        return String.format("""
        Video Game : %s
        Developer : %s
        Platform : %s
        Genre : %s
        Playtime : %d hrs
        Status : %s
        Rating : %s
        Review : %s
        """,
        title,
        developer,
        platform,
        genre,
        playtime,
        StatusMapper.getStatus(status),
        ratingStr,
        reviewStr
        );
    }

}
