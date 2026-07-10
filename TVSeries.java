import java.time.LocalDate;
import java.util.ArrayList;

public class TVSeries {
    private String title;
    private LocalDate dateAdded;
    private String genre;
    private int status;
    private int rating;
    private String review;
    private String creator;
    private ArrayList<Episode> episodes;
    private int noOfEpisodes;
    private boolean isRealityTV;

    public TVSeries(String title, LocalDate dateAdded, String genre, String creator, int noOfEpisodes, boolean isRealityTV) {
        this.title = title;
        this.dateAdded = dateAdded;
        this.genre = genre;
        this.creator = creator;
        this.noOfEpisodes = noOfEpisodes;
        this.isRealityTV = isRealityTV;
        this.status = 0; // Default to Planned
        this.episodes = new ArrayList<>();
    }

    public void updateStatus(int newStatus) {
        this.status = newStatus;
    }

    public void updateProgress(int n) {
    }

    public void trackCurrentSeason() {
        System.out.println("Tracking current season for reality TV...");
    }

    public void rate(int n) {
        if (this.status == 2) {
            this.rating = n;
            System.out.println("Rating saved successfully.");
        } else {
            System.out.println("Error: Cannot rate an incomplete TV Series.");
        }
    }

    public void review(String text) {
        if (this.status == 2) {
            this.review = text;
            System.out.println("Review saved successfully.");
        } else {
            System.out.println("Error: Cannot review an incomplete TV Series.");
        }
    }

    public String displayInfo() {
        return "TV Series: " + title + " | Creator: " + creator + " | Reality TV: " + isRealityTV + " | Status: " + status;
    }

    public String getTitle() {
        return this.title;
    }

    public int getStatus() {
        return this.status;
    }
}
