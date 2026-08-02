package model;

/**
 * A data container that holds summary statistics of the user's media library.
 * Used by the dashboard and report export features.
 */
public class LibrarySummary {
    public int total = 0, planned = 0, inProgress = 0, completed = 0;
    public int animeCount = 0, tvSeriesCount = 0, videoGameCount = 0;
    public int musicAlbumCount = 0, musicSingleCount = 0;
    public double averageRating = 0.0;
}
