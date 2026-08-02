package model;

/**
 * Represents a Video Game media entry with playtime, developer, and platform information.
 */
public class VideoGame extends MediaEntry {
    private int playtime;
    private String developer;
    private String platform;

    /**
     * Creates a new Video Game entry with the given details.
     * 
     * @param title the title of the video game
     * @param genre the genre of the video game
     * @param status the initial status (0 = Planned, 1 = In Progress)
     * @param playtime the estimated playtime in hours
     * @param developer the developer studio of the video game
     * @param platform the platform the game is played on
     * Pre-condition: All parameters must be valid.
     * Post-condition: A new VideoGame entry is created.
     */
    public VideoGame(String title, String genre, int status, int playtime, String developer, String platform) {
        super(title, genre, status);
        this.playtime = playtime;
        this.developer = developer;
        this.platform = platform;
    }
    @Override public String getTypeLabel() { return "Video Game"; }
}
