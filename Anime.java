import java.util.ArrayList;

/**
 * Represents an anime entry in the user's media list.
 */
public class Anime extends EpisodicMedia{
    private String animationStudio;
    private String creator;
    private boolean isSubbed;

    /**
     * Creates an anime with its details and generates episode objects.
     * 
     * @param title the title of the anime
     * @param genre the genre of the anime
     * @param animationStudio the studio that animated the anime
     * @param creator the creator/writer of the anime
     * @param noOfEpisodes the total number of episodes
     * @param isSubbed whether the anime is subbed or dubbed
     * Pre-condition: Title, genre, animationStudio, and creator are valid strings. noOfEpisodes is a positive integer.
     * Post-condition: An Anime object is created with the specified details, and episode objects are generated.
     */
    public Anime(String title, String genre, int initialStatus, String animationStudio, String creator, int noOfEpisodes, boolean isSubbed) {
        super(title, genre, initialStatus, noOfEpisodes, "Episode");
        this.animationStudio = animationStudio;
        this.creator = creator;
        this.isSubbed = isSubbed;
    }
    
    @Override
    /**
     * Returns a formatted string of all anime details.
     * 
     * @return a formatted string containing the anime's details
     * Pre-condition: None.
     * Post-condition: A string with the anime's information is returned.
     */
    public String displayInfo() {
        /* 
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
        StatusMapper.getStatusString(status),
        ratingStr,
        reviewStr
        );
        */
        return "";
    }
}
