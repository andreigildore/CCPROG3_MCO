public class Segment {
    private final int segmentNumber;
    private final String type; //   Can be episode or track
    private int rating;
    private boolean isFavorite;
    private boolean isConsumed;

    public Segment(int segmentNumber, String type) {
        this.segmentNumber = segmentNumber;
        this.type = type;
        rating = -1; // Default sentinel value for an unrated segment
        isFavorite = false;
        isConsumed = false;
    }

    public int getSegmentNumber() {
        return segmentNumber;
    }
    
    public int getRating() {
        return rating;
    }

    public boolean rate(int rating) {
        if (rating < 1 || rating > 10 || isConsumed == false)
            return false;
        else {
            this.rating = rating;
            return true;
        }
    }

    public boolean toggleFavorite() {
        if (isConsumed == false)
            return false;
        else {
            isFavorite = !isFavorite;
            return true;
        }
    }

    public void markConsumed() {
        isConsumed = true;
    }
    
    public boolean isConsumed() {
        return isConsumed;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public String displayInfo() {
        return String.format("""
                %s %-3d | Consumed : %3s | Favorite : %3s | Rating : %s
                """,
                type,
                segmentNumber,
                isConsumed ? "Yes" : "No",
                isFavorite ? "Yes" : "No",
                rating);
    }
}
