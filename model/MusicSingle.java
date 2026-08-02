package model;

/**
 * Represents a Music Single media entry with artist and record label information.
 */
public class MusicSingle extends MediaEntry {
    private String artist;
    private String recordLabel;

    /**
     * Creates a new Music Single entry with the given details.
     * 
     * @param title the title of the single
     * @param genre the genre of the single
     * @param status the initial status (0 = Planned, 1 = In Progress)
     * @param artist the artist who released the single
     * @param recordLabel the record label that published the single
     * Pre-condition: All parameters must be valid.
     * Post-condition: A new MusicSingle entry is created.
     */
    public MusicSingle(String title, String genre, int status, String artist, String recordLabel) {
        super(title, genre, status);
        this.artist = artist;
        this.recordLabel = recordLabel;
    }
    @Override public String getTypeLabel() { return "Music Single"; }
}
