package model;

import java.io.Serializable;

/**
 * Represents an abstract media entry in the user's library.
 * Serves as the base class for all media types such as Anime, TV Series, Video Games, and Music.
 */
public abstract class MediaEntry implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String title;
    private String genre;
    private int status; // 0 = Planned, 1 = In Progress, 2 = Completed
    private int rating;
    private String review;

    /**
     * Creates a new media entry with the given title, genre, and initial status.
     * 
     * @param title the title of the media entry
     * @param genre the genre of the media entry
     * @param status the initial status of the media entry (0 = Planned, 1 = In Progress)
     * Pre-condition: Title must be non-null and non-empty. Status must be 0 or 1.
     * Post-condition: A new MediaEntry is created with default rating of 0 and an empty review.
     */
    public MediaEntry(String title, String genre, int status) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        if (status == 2) {
            throw new IllegalArgumentException("New entries must start as Planned (0) or In Progress (1).");
        }
        
        this.title = title;
        this.genre = genre;
        this.status = status;
        this.rating = 0;
        this.review = "";
    }

    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    
    public int getRating() { return rating; }

    /**
     * Sets the rating of the media entry.
     * 
     * @param rating the rating to assign to the media entry
     * Pre-condition: The media entry must have a status of 2 (Completed).
     * Post-condition: The rating is set to the given value.
     */
    public void setRating(int rating) { 
        if (this.status != 2) throw new IllegalStateException("Can only rate completed media.");
        this.rating = rating; 
    }
    
    public String getReview() { return review; }

    /**
     * Sets the review of the media entry.
     * 
     * @param review the review text to assign to the media entry
     * Pre-condition: The media entry must have a status of 2 (Completed).
     * Post-condition: The review is set to the given value.
     */
    public void setReview(String review) { 
        if (this.status != 2) throw new IllegalStateException("Can only review completed media.");
        this.review = review; 
    }

    public abstract String getTypeLabel();
}
