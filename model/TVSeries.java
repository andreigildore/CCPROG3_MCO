package model;

/**
 * Represents a TV Series media entry with creator, episode, and reality TV information.
 */
public class TVSeries extends MediaEntry implements Episodic {
    private String creator;
    private int episodes;
    private boolean isRealityTV;
    private int[] episodeRatings; 

    /**
     * Creates a new TV Series entry with the given details.
     * 
     * @param title the title of the TV series
     * @param genre the genre of the TV series
     * @param status the initial status (0 = Planned, 1 = In Progress)
     * @param creator the creator or showrunner of the TV series
     * @param episodes the total number of episodes
     * @param isRealityTV true if the series is a reality TV show, false otherwise
     * Pre-condition: All parameters must be valid. Episodes must be greater than 0.
     * Post-condition: A new TVSeries entry is created with an empty episode ratings array.
     */
    public TVSeries(String title, String genre, int status, String creator, int episodes, boolean isRealityTV) {
        super(title, genre, status);
        this.creator = creator;
        this.episodes = episodes;
        this.isRealityTV = isRealityTV;
        this.episodeRatings = new int[episodes];
    }

    @Override public String getTypeLabel() { return "TV Series"; }
    
    @Override public int getEpisodeCount() { return episodes; }
    @Override public int[] getEpisodeRatings() { return episodeRatings; }
    @Override public void setEpisodeRating(int index, int rating) { this.episodeRatings[index] = rating; }
}
