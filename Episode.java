/**
 * represents a single episode of a series
 */
public class Episode {
    private final int episodeNumber;
    private int rating;
    private boolean isFavorite;
    private boolean isWatched;
 
    /**
     * creates a new unwatched, unrated episode
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
     * rates the episode from 1-10
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
 
    public void toggleFavorite() {
        isFavorite = !isFavorite;
        System.out.println(isFavorite ? "Episode marked as favorite." : "Episode removed from favorites.");
    }
 
    public boolean isFavorite() {
        return isFavorite;
    }
 
    /**
     * marks this episode as watched
     */
    public void markWatched() {
        isWatched = true;
    }
 
    public boolean isWatched() {
        return isWatched;
    }
 
    /**
     * returns a formatted string of the episode's details
     */
    public String displayInfo() {
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