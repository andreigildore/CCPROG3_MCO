package model;

/**
 * Represents an Anime media entry with studio, creator, episode, and subtitle information.
 */
public class Anime extends MediaEntry implements Episodic {
    private String studio;
    private String creator;
    private int episodes;
    private boolean isSubbed;
    private int[] episodeRatings; 

    /**
     * Creates a new Anime entry with the given details.
     * 
     * @param title the title of the anime
     * @param genre the genre of the anime
     * @param status the initial status (0 = Planned, 1 = In Progress)
     * @param studio the animation studio that produced the anime
     * @param creator the creator or original author of the anime
     * @param episodes the total number of episodes
     * @param isSubbed true if the anime is subbed, false if dubbed
     * Pre-condition: All parameters must be valid. Episodes must be greater than 0.
     * Post-condition: A new Anime entry is created with an empty episode ratings array.
     */
    public Anime(String title, String genre, int status, String studio, String creator, int episodes, boolean isSubbed) {
        super(title, genre, status);
        this.studio = studio;
        this.creator = creator;
        this.episodes = episodes;
        this.isSubbed = isSubbed;
        this.episodeRatings = new int[episodes]; 
    }

    @Override public String getTypeLabel() { return "Anime"; }
    
    @Override public int getEpisodeCount() { return episodes; }
    @Override public int[] getEpisodeRatings() { return episodeRatings; }
    @Override public void setEpisodeRating(int index, int rating) { this.episodeRatings[index] = rating; }
}
