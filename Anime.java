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
        if(newStatus >= 0 && newStatus <= 2)
            status = newStatus;
        else
            System.out.println("Invalid status.");
    }

    public void updateProgress(int n) {
        
    }

    public void rate(int rating) {
        if (rating >= 0 && rating  <= 10) {
            if (this.status == 2) {
                this.rating = rating;
                System.out.println("Rating saved successfully.");
            } else {
                System.out.println("Cannot rate an unfinished Anime.");
            }
        }
        else
            System.out.println("Invalid rating input");
    }

    public void review(String text) {
        if (text != null && !text.isBlank()){
            if (this.status == 2) {
                this.review = text;
                System.out.println("Review saved successfully.");
            } else {
                System.out.println("Cannot review an unfinished Anime.");
            }
        }
        else
            System.out.println("Review has no content or is null");
    }

    public String displayInfo() {
        return String.format("""
        Anime  : %s
        Studio : %s
        Subbed : %b
        No. Of Episodes : %d
        Status : %s
        """,
        title,
        animationStudio,
        isSubbed,
        noOfEpisodes,
        StatusMapper.getStatus(status)
        );
    }

    public String getTitle() {
        return this.title;
    }

    public int getStatus() {
        return this.status;
    }
}
