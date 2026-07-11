/**
 * Represents a single episode of an anime or TV series.
 */
public class Episode {
    private final int episodeNumber;
    private int rating;
    private boolean isFavorite;
    private boolean isWatched;
 
    /**
     * Initializes the episode with its episode number.
     * 
     * @param episodeNumber the number of this episode
     * Pre-condition: episodeNumber must be greater than 0.
     * Post-condition: The episode is created with with rating set to zero and booleans defaulted to false.
     */
    public Episode(int episodeNumber) {
        this.episodeNumber = episodeNumber;
        this.rating = 0;
        this.isFavorite = false;
        this.isWatched = false;
    }
 
    public int getEpisodeNumber() {
        return episodeNumber;
    }
 
    /**
     * Rates the episode from 1-10, only if watched.
     * 
     * @param rating the rating score from 1 to 10
     * Pre-condition: isWatched must be true. Rating must be from, 1 to 10.
     * Post-condition: The rating is saved to the episode object.
     */
    public void rate(int rating) {
        if (rating >= 1 && rating <= 10) {
            // must be watched before rating
            if (isWatched) {
                this.rating = rating;
                System.out.println("Episode rated successfully.");
            }
            else
                System.out.println("Episode has not been watched yet.");
        }
        else
            System.out.println("Invalid rating score");
    }
 
    public int getRating() {
        return rating;
    }
 
    /**
     * Toggles the favorite status of the episode.
     * 
     * Pre-condition: None.
     * Post-condition: isFavorite is flipped between true and false.
     */
    public void toggleFavorite() {
        isFavorite = !isFavorite;
        System.out.println(isFavorite ? "Episode marked as favorite." : "Episode removed from favorites.");
    }
 
    public boolean isFavorite() {
        return isFavorite;
    }
 
    /**
     * Marks the episode as watched.
     * 
     * Pre-condition: None.
     * Post-condition: isWatched is set to true.
     */
    public void markWatched() {
        isWatched = true;
    }
 
    public boolean isWatched() {
        return isWatched;
    }
 
    /**
     * Returns a formatted string containing all details of the episode.
     * 
     * @return the formatted information string
     * Pre-condition: None
     * Post-condition: Returns the string representation.
     */
    public String displayInfo() { // assumes episode number will only reach 3 digits
        return String.format("""
        Episode %-4d Watched: %3s Favorite : %3s Rating : %s 
        """,
        episodeNumber,
        isWatched ? "Yes" : "No",
        isFavorite  ? "Yes" : "No",
        rating >= 1 ? rating + "/10" : "Not rated"
        );
    }
}