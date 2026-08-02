package model;

/**
 * Represents a Music Album media entry with artist, record label, and track information.
 */
public class MusicAlbum extends MediaEntry {
    private String artist;
    private String recordLabel;
    private int tracks;

    /**
     * Creates a new Music Album entry with the given details.
     * 
     * @param title the title of the album
     * @param genre the genre of the album
     * @param status the initial status (0 = Planned, 1 = In Progress)
     * @param artist the artist or band who created the album
     * @param recordLabel the record label that published the album
     * @param tracks the total number of tracks in the album
     * Pre-condition: All parameters must be valid. Tracks must be greater than 0.
     * Post-condition: A new MusicAlbum entry is created.
     */
    public MusicAlbum(String title, String genre, int status, String artist, String recordLabel, int tracks) {
        super(title, genre, status);
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.tracks = tracks;
    }
    @Override public String getTypeLabel() { return "Music Album"; }
}
