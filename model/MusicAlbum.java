package model;

public class MusicAlbum extends MediaEntry {
    private String artist;
    private String recordLabel;
    private int tracks;

    public MusicAlbum(String title, String genre, int status, String artist, String recordLabel, int tracks) {
        super(title, genre, status);
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.tracks = tracks;
    }
    @Override public String getTypeLabel() { return "Music Album"; }
}
