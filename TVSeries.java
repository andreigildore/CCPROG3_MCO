/**
 * Represents a TV series entry in the user's media list.
 */
public class TVSeries extends EpisodicMedia {
    private String creator;
    private boolean isRealityTV;

    /**
     * Creates a TV series with its details and generates episode objects.
     * 
     * @param title the title of the TV series
     * @param genre the genre of the TV series
     * @param creator the creator/writer/director of the TV series
     * @param noOfEpisodes the total number of episodes
     * @param isRealityTV whether the series is reality TV
     * Pre-condition: Title, genre, and creator are valid strings. noOfEpisodes is a positive integer.
     * Post-condition: A TVSeries object is created with the specified details, and episode objects are generated.
     */
    public TVSeries(String title, String genre, int initialStatus, String creator, int noOfEpisodes, boolean isRealityTV) {
        super(title, genre, initialStatus, noOfEpisodes,"Episode");
        this.creator = creator;
        this.isRealityTV = isRealityTV;
    }

    public String getCreator() {
        return creator;
    }

    public boolean getIsRealityTV() {
        return isRealityTV;
    }
}
