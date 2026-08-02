package model;

public class TVSeries extends MediaEntry implements Episodic {
    private String creator;
    private int episodes;
    private boolean isRealityTV;
    private int[] episodeRatings; 

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
