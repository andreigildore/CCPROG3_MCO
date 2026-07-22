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

    public String getGenre() {
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

    /**
     * Updates the watching status of the media entry.
     * 
     * @param newStatus the new status code
     * Pre-condition: newStatus should ideally be between 0 and 2.
     * Post-condition: The status is updated if valid, otherwise an error message is printed.
     */
    public boolean updateStatus(int newStatus) {
        if(newStatus < 0 && newStatus > 2) 
            return false;
        else {
            status = newStatus;
            return true;
        }
            
    }

    /**
     * Rates the media entry from 1 to 10, only if completed.
     * 
     * @param rating the rating score to assign
     * Pre-condition: The media entry's status must be 2 (Completed), and rating must be between 1 and 10.
     * Post-condition: The rating is saved successfully if conditions are met.
     */
    public boolean rate(int rating) {
        if (rating < 1 || rating > 10 || status != 2)
            return false;
        else {
            this.rating = rating;
            return true;
        }
    }

    /**
     * Adds a text review for the media entry, only if completed.
     * 
     * @param text the review content
     * Pre-condition: The media entry's status must be 2 (Completed), and the text must not be null or blank.
     * Post-condition: The review is saved successfully if conditions are met.
     */
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
