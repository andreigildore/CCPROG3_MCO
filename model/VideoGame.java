package model;

public class VideoGame extends MediaEntry {
    private int playtime;
    private String developer;
    private String platform;

    public VideoGame(String title, String genre, int status, int playtime, String developer, String platform) {
        super(title, genre, status);
        this.playtime = playtime;
        this.developer = developer;
        this.platform = platform;
    }
    @Override public String getTypeLabel() { return "Video Game"; }
}
