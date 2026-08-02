package model;

public class MusicSingle extends MediaEntry {
    private String artist;
    private String recordLabel;

    public MusicSingle(String title, String genre, int status, String artist, String recordLabel) {
        super(title, genre, status);
        this.artist = artist;
        this.recordLabel = recordLabel;
    }
    @Override public String getTypeLabel() { return "Music Single"; }
}
