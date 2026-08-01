package model;

/**
 * Represents a video game entry in the user's library.
 */
public class VideoGame extends MediaEntry {
    private int playtime;
    private String developer;
    private String platform;

    /**
     * Creates a new video game with default status of 0 (planned).
     * 
     * @param title the title of the video game
     * @param genre the genre of the video game
     * @param playtime the playtime of the video game in hours
     * @param developer the developer/developer team of the video game
     * @param platform the platform of the video game
     * Pre-condition: None.
     * Post-condition: A new VideoGame object is created with a planned status.
     */
    public VideoGame(String title, String genre, int initialStatus, int playtime, String developer, String platform) {
        super(title, genre, initialStatus);
        this.playtime = playtime;
        this.developer = developer;
        this.platform = platform;
    }

    /**
     * Adds hours to the total playtime.
     * 
     * @param additionalPlaytime the additional playtime in hours
     * @return the number of currrent playtime for the videogame if additionalPlaytime is valid. Else, it returns -1.
     * Pre-condition: The additionalPlaytime must be greater than 0.
     * Post-condition: The total playtime is increased by the additionalPlaytime.
     */
    public int addPlaytime(int additionalPlaytime) {
        if (additionalPlaytime > 0) {
            playtime += additionalPlaytime;
            return playtime;
        } else 
            return -1;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getPlatform() {
        return platform;
    }

    @Override
    public String getTypeLabel() {
        return "Video Game";
    }
}
