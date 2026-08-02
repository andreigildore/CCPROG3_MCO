package model;

public class Anime extends MediaEntry implements Episodic {
    private String studio;
    private String creator;
    private int episodes;
    private boolean isSubbed;
    private int[] episodeRatings; 

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
