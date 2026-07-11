public class VideoGame {
    private String title;
    private String genre;
    private int status;
    private int rating;
    private String review;
    private int playtime;
    private String developer;
    private String platform;

    public VideoGame(String title, String genre, int playtime String developer, String platform) {
        this.title = title;
        this.genre = genre;
        this.playtime = playtime;
        this.developer = developer;
        this.platform = platform;
        this.status = 0;
        this.playtime = 0;
    }

    public void updateStatus(int newStatus) {
        if(newStatus >= 0 && newStatus <= 2)
            status = newStatus;
        else
            System.out.println("Invalid status.");
    }

    public void rate(int rating) {
        if (rating >= 1 && rating <= 10) {
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

    public void addPlaytime(int playtime) {

    }

    public String displayInfo() {
        return String.format("""
        Video Game : %s
        Developer : %s
        Platform : %s
        Playtime : %d hrs
        Status : %s
        """,
        title,
        developer,
        platform,
        playtime,
        StatusMapper.getStatus(status)
        );
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
}
