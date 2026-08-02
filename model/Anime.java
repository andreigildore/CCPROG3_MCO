package model;

public class Anime extends MediaEntry {
    private String studio;
    private String creator;
    private int episodes;
    private boolean isSubbed;

    public Anime(String title, String genre, int status, String studio, String creator, int episodes, boolean isSubbed) {
        super(title, genre, status);
        this.studio = studio;
        this.creator = creator;
        this.episodes = episodes;
        this.isSubbed = isSubbed;
    }
    @Override public String getTypeLabel() { return "Anime"; }
}
