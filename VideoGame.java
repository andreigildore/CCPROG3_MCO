import java.time.LocalDate;

public class VideoGame {
    private String title;
    private LocalDate dateAdded;
    private String genre;
    private int gameMode;
    private int status;
    private int rating;
    private String review;
    private int playtime;
    private String developer;
    private String platform;

    public VideoGame(String title, LocalDate dateAdded, String genre, int gameMode, String developer, String platform) {
        this.title = title;
        this.dateAdded = dateAdded;
        this.genre = genre;
        this.gameMode = gameMode;
        this.developer = developer;
        this.platform = platform;
        this.status = 0;
        this.playtime = 0;
    }

    public void updateStatus(int newStatus) {
        this.status = newStatus;
    }

    public void rate(int rating) {
        if (this.status == 2) {
            this.rating = rating;
            System.out.println("Rating saved successfully.");
        } else {
            System.out.println("Cannot rate an incomplete/unfinished Video Game.");
        }
    }

    public void review(String text) {
        if (this.status == 2) {
            this.review = text;
            System.out.println("Review saved successfully.");
        } else {
            System.out.println("Cannot review an incomplete/unfinished Video Game.");
        }
    }

    public String displayInfo() {
        return "Video Game: " + title + " | Platform: " + platform + " | Playtime: " + playtime + " hrs | Status: " + status;
    }

    public String getTitle() {
        return this.title;
    }

    public int getStatus() {
        return this.status;
    }
}
