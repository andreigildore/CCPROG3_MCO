import java.util.ArrayList;

/**
 * represents a tv series entry in the user's media list
 */
public class TVSeries {
    private String title;
    private String genre;
    private int status;
    private int rating;
    private String review;
    private String creator;
    private ArrayList<Episode> episodes;
    private int noOfEpisodes;
    private boolean isRealityTV;

    /**
     * creates a tv series with its details and generates episode objects
     */
    public TVSeries(String title, String genre, String creator, int noOfEpisodes, boolean isRealityTV) {
        this.title = title;
        this.genre = genre;
        this.creator = creator;
        this.isRealityTV = isRealityTV;
        this.status = 0; // Default to Planned
        this.episodes = new ArrayList<>();
        // generate episode objects numbered 1 to noOfEpisodes
        for (int i = 1; i <= noOfEpisodes; i++) {
            Episode episode = new Episode(i);
            episodes.add(episode);
        }
        this.noOfEpisodes = episodes.size();
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

    public String getReview() {
        return this.review;
    }

    /**
     * updates the watching status of the tv series
     */
    public void updateStatus(int newStatus) {
        if(newStatus >= 0 && newStatus <= 2)
            status = newStatus;
        else
            System.out.println("Invalid status.");
    }

    /**
     * marks all episodes up to the given episode number as watched
     */
    public void updateProgress(int episodeInput) {
        // mark all episodes from 1 up to episodeInput as watched
        for (Episode episode: episodes) {
            if (episode.getEpisodeNumber() <= episodeInput)
                episode.markWatched();
        }
        System.out.println("Updated watched episodes to episode " + episodeInput);
    }

    /**
     * finds and returns an episode by its number, or null if not found
     */
    public Episode findEpisode(int episodeNumber) {
        for (Episode episode : episodes) {
            if (episode.getEpisodeNumber() == episodeNumber)
                return episode;
        }
        return null;
    }

    /**
     * rates a specific episode by its number
     */
    public void rateEpisode(int episodeNumber, int rating) {
        Episode episode = findEpisode(episodeNumber);
        if (episode != null)
            episode.rate(rating);
        else
            System.out.println("Episode " + episodeNumber + " not found in " + title + ".");
    }

    /**
     * marks a specific episode as watched
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
     * toggles the favorite status of a specific episode
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
     * prints all episodes and their details
     */
    public void displayEpisodes() {
        System.out.println("Episodes for " + title + "\n\n");
        for (Episode episode : episodes) 
            System.out.println(episode.displayInfo());
    }

    /**
     * rates the tv series from 1-10, only if completed
     */
    public void rate(int rating) {
        if (rating >= 1 && rating <= 10) {
            // only completed media can be rated
            if (this.status == 2) {
                this.rating = rating;
                System.out.println("Rating saved successfully.");
            } else {
                System.out.println("Cannot rate an unfinished TV Series.");
            }
        }
        else
            System.out.println("Invalid rating score");
    }

    /**
     * adds a text review for the tv series, only if completed
     */
    public void review(String text) {
        if (text != null && !text.isBlank()) {
            // only completed media can be reviewed
            if (this.status == 2) {
                this.review = text;
                System.out.println("Review saved successfully.");
            } else {
                System.out.println("Cannot review an unfinished TV Series.");
            }
        }
        else
            System.out.println("Review has no content or is null");
    }

    /**
     * returns a formatted string of all tv series details
     */
    public String displayInfo() {
        String ratingStr = (rating > 0) ? rating + "/10" : "NotRated";
        String reviewStr = (review != null && !review.isBlank()) ? review : "No review";
        return String.format("""
        TV Series  : %s
        Creator : %s
        Genre : %s
        Reality Tv : %s
        No. Of Episodes : %d
        Status : %s
        Rating : %s
        Review : %s
        """,
        title,
        creator,
        genre,
        isRealityTV ? "Yes" : "No",
        noOfEpisodes,
        StatusMapper.getStatus(status),
        ratingStr,
        reviewStr
        );
    }
}
