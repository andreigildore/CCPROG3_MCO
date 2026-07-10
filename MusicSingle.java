import java.time.LocalDate;

public class MusicSingle {
    private String title;
    private LocalDate dateAdded;
    private String genre;
    private int rating;
    private String review;
    private String artist;
    private String recordLabel;
    private int status;

    public MusicSingle(String title, LocalDate dateAdded, String genre, String artist, String recordLabel) {
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.status = 0;
        this.dateAdded = dateAdded;
    }

    public String getTitle() {
        return title;
    }

    public void rate(int rating) {
        if (rating >= 0 && rating  <= 10) {
            this.rating = rating;
            System.out.println("Rating saved successfully.");
        }
        else
            System.out.println("Invalid rating input");
    }

     public void review(String text) {
        if (text != null && !text.isBlank()) {
            this.review = text;
            System.out.println("Review saved successfully.");
        }
        else
            System.out.println("Review has no content or is null");
    }

    public String displayInfo() {
        return String.format("""
        Title  : %s
        Artist : %s
        Record Label : %s
        Genre : %s
        Status : %s
        """,
        title,
        artist,
        recordLabel,
        genre,
        StatusMapper.getStatus(status)
        );
    }

    public int getStatus() {
        return this.status;
    }

    public void updateStatus(int newStatus) {
        if(newStatus >= 0 && newStatus <= 2)
            status = newStatus;
        else
            System.out.println("Invalid status.");
    }
}
