package model;

/**
 * Represents a single segment (e.g., episode or track) within an episodic media entry.
 * Tracks consumption, rating, and favorite status.
 */
public class Segment {
    private final int segmentNumber;
    private final String type; //   Can be episode or track
    private int rating;
    private boolean isFavorite;
    private boolean isConsumed;

    /**
     * Creates a new segment with the given number and type.
     * 
     * @param segmentNumber the sequential number of the segment
     * @param type the type label of the segment (e.g., "Episode", "Track")
     * Pre-condition: segmentNumber must be a positive integer.
     * Post-condition: A new Segment is created with default rating of -1, not consumed, and not favorited.
     */
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

    public String getType() {
        return type;
    }

    /**
     * Rates the segment with a value between 1 and 10.
     * 
     * @param rating the rating to assign to the segment
     * @return true if the rating was successfully set, false if the segment has not been consumed or rating is out of range
     * Pre-condition: The segment must be consumed before it can be rated.
     * Post-condition: The segment's rating is updated if valid, otherwise no change is made.
     */
    public boolean rate(int rating) {
        if (rating < 1 || rating > 10 || isConsumed == false)
            return false;
        else {
            this.rating = rating;
            return true;
        }
    }

    /**
     * Toggles the favorite status of the segment.
     * 
     * @return true if the toggle was successful, false if the segment has not been consumed
     * Pre-condition: The segment must be consumed before it can be favorited.
     * Post-condition: The segment's favorite status is toggled if consumed.
     */
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

}
