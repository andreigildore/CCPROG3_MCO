package model;

/**
 * Defines the behavior for media types that contain episodes.
 * Implemented by media types such as Anime and TV Series.
 */
public interface Episodic {
    int getEpisodeCount();
    int[] getEpisodeRatings();
    void setEpisodeRating(int episodeIndex, int rating);
}
