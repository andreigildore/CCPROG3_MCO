import java.util.ArrayList;

/**
 * represents an anime entry in the user's media list
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
     * creates an anime with its details and generates episode objects
     */
    public Anime(String title, String genre, String animationStudio, String creator, int noOfEpisodes, boolean isSubbed) {
        this.title = title;
        this.genre = genre;
        this.animationStudio = animationStudio;
        this.creator = creator;
        this.isSubbed = isSubbed;
        this.status = 0;
        this.episodes = new ArrayList<>();
        // generate episode objects numbered 1 to noOfEpisodes
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
     * updates the watching status of the anime
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
     * prints all episodes and their details
     */
    public void displayEpisodes() {
        System.out.println("Episodes for " + title + "\n\n");
        for (Episode episode : episodes)
            System.out.println(episode.displayInfo());
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
     * rates the anime from 1-10, only if completed
     */
    public void rate(int rating) {
        if (rating >= 1 && rating  <= 10) {
            // only completed media can be rated
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
     * adds a text review for the anime, only if completed
     */
    public void review(String text) {
        if (text != null && !text.isBlank()){
            // only completed media can be reviewed
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
     * returns a formatted string of all anime details
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
