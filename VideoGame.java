/**
 * represents a video game entry in the user's library
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
     * creates a new video game with default status of 0 (planned)
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
     * updates the status if the new value is within valid range
     */
    public void updateStatus(int newStatus) {
        if(newStatus >= 0 && newStatus <= 2)
            status = newStatus;
        else
            System.out.println("Invalid status.");
    }

    /**
     * rates the video game from 1-10
     */
    public void rate(int rating) {
        if (rating >= 1 && rating <= 10) {
            // only completed media can be rated
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
     * adds a text review if the game is completed
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
     * adds hours to the total playtime
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
     * returns a formatted string of the video game's details
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
