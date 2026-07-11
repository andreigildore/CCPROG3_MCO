import java.util.ArrayList;

/**
 * Represents an anime entry in the user's media list.
 */
public class Anime {
    private String title;
    private String genre;
    private int status; 
    private int rating;
    private String review;
    private String animationStudio;
    private String creator;
    private ArrayList<Episode> episodes;
    private int noOfEpisodes;
    private boolean isSubbed;

    /**
     * Creates an anime with its details and generates episode objects.
     * 
     * @param title the title of the anime
     * @param genre the genre of the anime
     * @param animationStudio the studio that animated the anime
     * @param creator the creator/writer of the anime
     * @param noOfEpisodes the total number of episodes
     * @param isSubbed whether the anime is subbed or dubbed
     * Pre-condition: Title, genre, animationStudio, and creator are valid strings. noOfEpisodes is a positive integer.
     * Post-condition: An Anime object is created with the specified details, and episode objects are generated.
     */
    public Anime(String title, String genre, String animationStudio, String creator, int noOfEpisodes, boolean isSubbed) {
        this.title = title;
        this.genre = genre;
        this.animationStudio = animationStudio;
        this.creator = creator;
        this.isSubbed = isSubbed;
        this.status = 0;
        this.episodes = new ArrayList<>();
        // Generate episode objects numbered 1 to noOfEpisodes.
        for (int i = 1; i <= noOfEpisodes; i++) {
            Episode episode = new Episode(i);
            episodes.add(episode);
        }
        this.noOfEpisodes = episodes.size();
    }

    public int getRating() {
        return this.rating;
    }

    public String getReview() {
        return this.review;
    }

    /**
     * Updates the watching status of the anime.
     * 
     * @param newStatus the new status code
     * Pre-condition: newStatus should ideally be between 0 and 2.
     * Post-condition: The status is updated if valid, otherwise an error message is printed.
     */
    public void updateStatus(int newStatus) {
        if(newStatus >= 0 && newStatus <= 2)
            status = newStatus;
        else
            System.out.println("Invalid status.");
    }

    /**
     * Marks all episodes up to the given episode number as watched.
     * 
     * @param episodeInput the episode number up to which to mark as watched
     * Pre-condition: episodeInput is a valid episode number.
     * Post-condition: Episodes from 1 to episodeInput are marked as watched.
     */
    public void updateProgress(int episodeInput) {
        // Mark all episodes from 1 up to episodeInput as watched.
        for (Episode episode: episodes) {
            if (episode.getEpisodeNumber() <= episodeInput)
                episode.markWatched();
        }
        System.out.println("Updated watched episodes to episode " + episodeInput);
    }

    /**
     * Finds and returns an episode by its number, or null if not found.
     * 
     * @param episodeNumber the number of the episode to find
     * @return the Episode object if found, or null if not found
     * Pre-condition: episodeNumber is a valid integer.
     * Post-condition: The corresponding Episode object is returned, or null if it does not exist.
     */
    public Episode findEpisode(int episodeNumber) {
        for (Episode episode : episodes) {
            if (episode.getEpisodeNumber() == episodeNumber)
                return episode;
        }
        return null;
    }

    /**
     * Rates a specific episode by its number.
     * 
     * @param episodeNumber the number of the episode to rate
     * @param rating the rating to assign to the episode
     * Pre-condition: episodeNumber corresponds to an existing episode.
     * Post-condition: The specified episode is rated if it exists.
     */
    public void rateEpisode(int episodeNumber, int rating) {
        Episode episode = findEpisode(episodeNumber);
        if (episode != null)
            episode.rate(rating);
        else
            System.out.println("Episode " + episodeNumber + " not found in " + title + ".");
    }

    /**
     * Marks a specific episode as watched.
     * 
     * @param episodeNumber the number of the episode to mark as watched
     * Pre-condition: episodeNumber corresponds to an existing episode.
     * Post-condition: The specified episode is marked as watched if it exists.
     */
    public void markEpisodeWatched(int episodeNumber) {
        Episode episode = findEpisode(episodeNumber);
        if (episode != null) {
            episode.markWatched();
            System.out.println("Marked episode " + episodeNumber + " as watched.");
        }
        else
            System.out.println("Episode " + episodeNumber + " not found in " + title + ".");
    }

    /**
     * Prints all episodes and their details.
     * 
     * Pre-condition: The anime has a list of episodes.
     * Post-condition: The details of all episodes are printed to the console.
     */
    public void displayEpisodes() {
        System.out.println("Episodes for " + title + "\n\n");
        for (Episode episode : episodes)
            System.out.println(episode.displayInfo());
    }

    /**
     * Toggles the favorite status of a specific episode.
     * 
     * @param episodeNumber the number of the episode to favorite or unfavorite
     * Pre-condition: episodeNumber corresponds to an existing episode.
     * Post-condition: The favorite status of the specified episode is toggled.
     */
    public void favoriteEpisode(int episodeNumber) {
        Episode episode = findEpisode(episodeNumber);
        if (episode != null) {
            episode.toggleFavorite();
        }
        else
            System.out.println("Episode " + episodeNumber + " not found in " + title + ".");
    }

    /**
     * Rates the anime from 1 to 10, only if completed.
     * 
     * @param rating the rating score to assign
     * Pre-condition: The anime status must be 2 (Completed), and rating must be between 1 and 10.
     * Post-condition: The rating is saved successfully if conditions are met.
     */
    public void rate(int rating) {
        if (rating >= 1 && rating  <= 10) {
            // Only completed media can be rated.
            if (this.status == 2) {
                this.rating = rating;
                System.out.println("Rating saved successfully.");
            } else {
                System.out.println("Cannot rate an unfinished Anime.");
            }
        }
        else
            System.out.println("Invalid rating score");
    }

    /**
     * Adds a text review for the anime, only if completed.
     * 
     * @param text the review content
     * Pre-condition: The anime status must be 2 (Completed), and the text must not be null or blank.
     * Post-condition: The review is saved successfully if conditions are met.
     */
    public void review(String text) {
        if (text != null && !text.isBlank()){
            // Only completed media can be reviewed.
            if (this.status == 2) {
                this.review = text;
                System.out.println("Review saved successfully.");
            } else {
                System.out.println("Cannot review an unfinished Anime.");
            }
        }
        else
            System.out.println("Review has no content or is null");
    }

    /**
     * Returns a formatted string of all anime details.
     * 
     * @return a formatted string containing the anime's details
     * Pre-condition: None.
     * Post-condition: A string with the anime's information is returned.
     */
    public String displayInfo() {
        String ratingStr = (rating > 0) ? rating + "/10" : "NotRated";
        String reviewStr = (review != null && !review.isBlank()) ? review : "No review";
        return String.format("""
        Anime  : %s
        Creator : %s 
        Studio : %s
        Genre : %s
        Subbed : %b
        No. Of Episodes : %d
        Status : %s
        Rating : %s
        Review : %s
        """,
        title,
        creator,
        animationStudio,
        genre,
        isSubbed,
        noOfEpisodes,
        StatusMapper.getStatus(status),
        ratingStr,
        reviewStr
        );
    }

    public String getTitle() {
        return this.title;
    }

    public int getStatus() {
        return this.status;
    }
}
