import java.util.ArrayList;

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

    public TVSeries(String title, String genre, String creator, int noOfEpisodes, boolean isRealityTV) {
        this.title = title;
        this.genre = genre;
        this.creator = creator;
        this.isRealityTV = isRealityTV;
        this.status = 0; // Default to Planned
        this.episodes = new ArrayList<>();
        for (int i = 1; i <= noOfEpisodes; i++) {
            Episode episode = new Episode(i);
            episodes.add(episode);
        }
        this.noOfEpisodes = episodes.size();
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
            System.out.println("Episode " + episodeNumber + " not found in" + title + ".");
    }

    public void markEpisodeWatched(int episodeNumber) {
        Episode episode = findEpisode(episodeNumber);
        if (episode != null) {
            episode.markWatched();
            System.out.println("Marked episode " + episodeNumber + " as watched.");
        }
        else
            System.out.println("Episode " + episodeNumber + " not found in" + title + ".");
    }

    public void favoriteEpisode(int episodeNumber) {
        Episode episode = findEpisode(episodeNumber);
        if (episode != null) {
            episode.toggleFavorite();
        }
        else
            System.out.println("Episode " + episodeNumber + "not found in " + title + ".");
    }

    public void displayEpisodes() {
        System.out.println("Episodes for " + title + "\n\n");
        for (Episode episode : episodes) 
            System.out.println(episode.displayInfo());
    }

    public void rate(int rating) {
        if (rating >= 1 && rating <= 10) {
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

    public void review(String text) {
        if (text != null && !text.isBlank()) {
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

    public String displayInfo() {
        return String.format("""
        TV Series  : %s
        Creator : %s
        Reality Tv : %s
        No. Of Episodes : %d
        Status : %s
        """,
        title,
        creator,
        isRealityTV ? "Yes" : "No",
        noOfEpisodes,
        StatusMapper.getStatus(status)
        );
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
}
