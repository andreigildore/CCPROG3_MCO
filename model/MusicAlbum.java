package model;

/**
 * Represents a music album entry in the user's media list.
 */
public class MusicAlbum extends EpisodicMedia{
    private String artist;
    private String recordLabel;

    /**
     * Creates an album with its details and generates track objects.
     * 
     * @param title the title of the album
     * @param noOfTracks the total number of tracks
     * @param genre the genre of the album
     * @param artist the artist of the album
     * @param recordLabel the record label of the album
     * Pre-condition: Title, genre, artist, and recordLabel are valid strings. noOfTracks is a positive integer.
     * Post-condition: A MusicAlbum object is created with the specified details, and track objects are generated.
     */
    public MusicAlbum(String title, String genre, int initialStatus, String artist, String recordLabel, int noOfTracks) {
        super(title, genre, initialStatus, noOfTracks, "Track");
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
        return "Music Album";
    }
}
