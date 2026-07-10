import java.time.LocalDate;
import java.util.ArrayList;

public class Anime {
    private String title;
    private LocalDate dateAdded;
    private String genre;
    private int status; 
    private int rating;
    private String review;
    private String animationStudio;
    private String creator;
    private ArrayList<Episode> episodes;
    private int noOfEpisodes;
    private boolean isSubbed;

    public Anime(String title, LocalDate dateAdded, String genre, String animationStudio, String creator, int noOfEpisodes, boolean isSubbed) {
        this.title = title;
        this.dateAdded = dateAdded;
        this.genre = genre;
        this.animationStudio = animationStudio;
        this.creator = creator;
        this.noOfEpisodes = noOfEpisodes;
        this.isSubbed = isSubbed;
        this.status = 0;
        this.episodes = new ArrayList<>();
    }

    public void updateStatus(int newStatus) {
        this.status = newStatus;
    }

    public void updateProgress(int n) {
    }

    public void rate(int n) {
        if (this.status == 2) {
            this.rating = n;
            System.out.println("Rating saved successfully.");
        } else {
            System.out.println("Error: Cannot rate an incomplete Anime.");
        }
    }

    public void review(String text) {
        if (this.status == 2) {
            this.review = text;
            System.out.println("Review saved successfully.");
        } else {
            System.out.println("Error: Cannot review an incomplete Anime.");
        }
    }

    public String displayInfo() {
        return "Anime: " + title + " | Studio: " + animationStudio + " | Subbed: " + isSubbed + " | Status: " + status;
    }

    // --- FIX 1: GETTERS ---
    public String getTitle() {
        return this.title;
    }

    public int getStatus() {
        return this.status;
    }
}
