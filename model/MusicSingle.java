package model;

/**
 * Represents a music single entry in the user's library.
 */
public class MusicSingle extends MediaEntry{
    private String artist;
    private String recordLabel;

    /**
     * Creates a new music single with default status of 0 (planned).
     * 
     * @param title the title of the music single
     * @param genre the genre of the music single
     * @param artist the artist of the music single
     * @param recordLabel the record label of the music single
     * Pre-condition: None.
     * Post-condition: A new MusicSingle object is created with a planned status.
     */
    public MusicSingle(String title, String genre, int initialStatus, String artist, String recordLabel) {
        super(title, genre, initialStatus);
        this.artist = artist;
        this.recordLabel = recordLabel;
    }

    public String getArtist() {
        return artist;
    }

    public String getRecordLabel() {
        return recordLabel;
    }

    @Override
    public String getTypeLabel() {
        return "Music Single";
    }
}
