package controller;

import java.util.List;
import model.*;

public class LibraryController {
    private final Library library;

    public LibraryController(Library library) {
        this.library = library;
    }
    
    // add entry split into seperate data types
    public boolean addVideoGame(String title, String genre, int initialStatus, int playtime, String developer, String platform) {
        VideoGame game = new VideoGame(title, genre, initialStatus, playtime, developer, platform);
        return library.addMedia(game);
    }

    public boolean addAnime(String title, String genre, int initialStatus, String animationStudio, String creator, int noOfEpisodes, boolean isSubbed) {
        Anime anime = new Anime(title, genre, initialStatus, animationStudio, creator, noOfEpisodes, isSubbed);
        return library.addMedia(anime);
    }

    public boolean addTVSeries(String title, String genre, int initialStatus, String creator, int noOfEpisodes, boolean isRealityTV) {
        TVSeries tv = new TVSeries(title, genre, initialStatus, creator, noOfEpisodes, isRealityTV);
        return library.addMedia(tv);
    }

    public boolean addMusicAlbum(String title, String genre, int initialStatus, String artist, String recordLabel, int noOfTracks) {
        MusicAlbum album = new MusicAlbum(title, genre, initialStatus, artist, recordLabel, noOfTracks);
        return library.addMedia(album);
    }

    public boolean addMusicSingle(String title, String genre, int initialStatus, String artist, String recordLabel) {
        MusicSingle single = new MusicSingle(title, genre, initialStatus, artist, recordLabel);
        return library.addMedia(single);
    } 
    
    public boolean removeEntry(MediaEntry entry) {
        return library.removeMedia(entry);
    }

    public boolean updateStatus(MediaEntry entry, int newStatus) {
        return entry.updateStatus(newStatus);
    }

    public boolean rate(MediaEntry entry, int rating) {
        return entry.rate(rating);
    }

    public boolean review(MediaEntry entry, String reviewText) {
        return entry.review(reviewText);
    }

    public int addPlaytime(VideoGame game, int hours) {
        return game.addPlaytime(hours);
    }

    public int updateProgress(EpisodicMedia media, int segmentInput) {
        return media.updateProgress(segmentInput); 
    }

    public List<MediaEntry> getAllEntries() {
        return library.getEntries();
    }

    public List<MediaEntry> filterByStatus(int status) {
        return library.getEntriesByStatus(status);
    }
 
    public List<MediaEntry> filterByType(String type) {
        return library.getEntriesByType(type);
    }

    public Library.LibrarySummary getSummary() {
        return library.getSummary();
    }

}
