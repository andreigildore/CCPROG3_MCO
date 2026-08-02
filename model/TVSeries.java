package model;

public class TVSeries extends MediaEntry {
    private String creator;
    private int episodes;
    private boolean isRealityTV;

    public TVSeries(String title, String genre, int status, String creator, int episodes, boolean isRealityTV) {
        super(title, genre, status);
        this.creator = creator;
        this.episodes = episodes;
        this.isRealityTV = isRealityTV;
    }
    @Override public String getTypeLabel() { return "TV Series"; }
}
