public abstract class MediaEntry {
    private String title;
    private String genre;
    private int status;
    private int rating;
    private String review;

    protected MediaEntry(String title, String genre, int initialStatus) {
        this.title = title;
        this.genre = genre;
        if (initialStatus == 0 || initialStatus == 1) 
            this.status = initialStatus;
        else
            System.out.println("New entries must start as planned (0) or in Progress (1).");
    }

    public String getTitle() {
        return title;
    }

    public String genre() {
        return genre;
    }

    public int getStatus() {
        return status;
    }

    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public boolean updateStatus(int newStatus) {
        if(newStatus < 0 && newStatus > 2) 
            return false;
        else {
            status = newStatus;
            return true;
        }
            
    }

    public boolean rate(int rating) {
        if (rating < 1 || rating > 10 || status != 2)
            return false;
        else {
            this.rating = rating;
            return true;
        }
    }

    public boolean review(String text) {
        if (text == null || text.isBlank()) 
            return false;
        else {
            review = text;
            return true;
        }
    }

    public abstract String displayInfo();
}
