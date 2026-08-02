package model;

public interface Episodic {
    int getEpisodeCount();
    int[] getEpisodeRatings();
    void setEpisodeRating(int episodeIndex, int rating);
}
