import java.util.ArrayList;

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

    public Anime(String title, String genre, String animationStudio, String creator, int noOfEpisodes, boolean isSubbed) {
        this.title = title;
        this.genre = genre;
        this.animationStudio = animationStudio;
        this.creator = creator;
        this.isSubbed = isSubbed;
        this.status = 0;
        this.episodes = new ArrayList<>();
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

    public void updateStatus(int newStatus) {
        if(newStatus >= 0 && newStatus <= 2)
            status = newStatus;
        else
            System.out.println("Invalid status.");
    }

    public void updateProgress(int episodeInput) {
        for (Episode episode: episodes) {
            if (episode.getEpisodeNumber() <= episodeInput)
                episode.markWatched();
        }
        System.out.println("Updated watched episodes to episode " + episodeInput);
    }

    public Episode findEpisode(int episodeNumber) {
        for (Episode episode : episodes) {
            if (episode.getEpisodeNumber() == episodeNumber)
                return episode;
        }
        return null;
    }

    public void rateEpisode(int episodeNumber, int rating) {
        Episode episode = findEpisode(episodeNumber);
        if (episode != null)
            episode.rate(rating);
        else
            System.out.println("Episode " + episodeNumber + " not found in " + title + ".");
    }

    public void markEpisodeWatched(int episodeNumber) {
        Episode episode = findEpisode(episodeNumber);
        if (episode != null) {
            episode.markWatched();
            System.out.println("Marked episode " + episodeNumber + " as watched.");
        }
        else
            System.out.println("Episode " + episodeNumber + " not found in " + title + ".");
    }

    public void displayEpisodes() {
        System.out.println("Episodes for " + title + "\n\n");
        for (Episode episode : episodes)
            System.out.println(episode.displayInfo());
    }

    public void favoriteEpisode(int episodeNumber) {
        Episode episode = findEpisode(episodeNumber);
        if (episode != null) {
            episode.toggleFavorite();
        }
        else
            System.out.println("Episode " + episodeNumber + " not found in " + title + ".");
    }

    public void rate(int rating) {
        if (rating >= 1 && rating  <= 10) {
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

    public void review(String text) {
        if (text != null && !text.isBlank()){
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
