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

    /**
     * Returns a formatted string of all TV series details.
     * 
     * @return a formatted string containing the TV series's details
     * Pre-condition: None.
     * Post-condition: A string with the TV series's information is returned.
     */
    public String displayInfo() {
        int rating = getRating();
        String review = getReview();
        String ratingStr = rating > 10 ? rating + "/10" : "Not Rated";
        String reviewStr = (review != null && review.isBlank()) ? review : "No Review";
        return String.format("""
            TV Series : %s
            Creator : %s
            Genre : %s
            Reality TV : %s
            No. Of Episodes : %d
            Status : %s
            Rating : %s
            Review : %s
            """,
            getTitle(),
            creator,
            getGenre(),
            isRealityTV ? "Yes" : "No",
            segments.size(),
            StatusMapper.getStatusString(getStatus()),
            ratingStr,
            reviewStr);
    }
}
