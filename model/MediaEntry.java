package model;

import java.io.Serializable;

public abstract class MediaEntry implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String title;
    private String genre;
    private int status; // 0 = Planned, 1 = In Progress, 2 = Completed
    private int rating;
    private String review;

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
    public void setRating(int rating) { 
        if (this.status != 2) throw new IllegalStateException("Can only rate completed media.");
        this.rating = rating; 
    }
    
    public String getReview() { return review; }
    public void setReview(String review) { 
        if (this.status != 2) throw new IllegalStateException("Can only review completed media.");
        this.review = review; 
    }

    public abstract String getTypeLabel();
}
