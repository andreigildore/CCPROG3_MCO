package controller;

import java.util.List;
import model.*;

public class LibraryController {
    private final Library library;

    public LibraryController(Library library) {
        this.library = library;
    }
    
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
    public boolean removeEntry(String title, String type) {
        MediaEntry entry = library.getMediaEntryByTitleAndType(title, type);
        if (entry != null) {
            return library.removeMedia(entry);
        }
        return false;
    }

    public boolean updateStatus(String title, String type, int newStatus) {
        return library.updateEntryStatus(type, title, newStatus);
    }

    public boolean rate(String title, String type, int rating) {
        MediaEntry entry = library.getMediaEntryByTitleAndType(title, type);
        if (entry != null) 
            return entry.rate(rating);
        return false;
    }

    public boolean review(String title, String type, String reviewText) {
        MediaEntry entry = library.getMediaEntryByTitleAndType(title, type);
        if (entry != null) {
            return entry.review(reviewText);
        }
        return false;
    }

    public int addPlaytime(String title, String type, int hours) {
        MediaEntry entry = library.getMediaEntryByTitleAndType(title, type);
        if (entry instanceof VideoGame) {
            return ((VideoGame) entry).addPlaytime(hours);
        }
        return -1;
    }

    public int updateProgress(String title, String type, int segmentInput) {
        MediaEntry entry = library.getMediaEntryByTitleAndType(title, type);
        if (entry instanceof EpisodicMedia) {
            return ((EpisodicMedia) entry).updateProgress(segmentInput);
        }
        return -1;
    }

    public List<MediaEntry> getAllEntries() {
        return library.getEntries();
    }

    public Library.LibrarySummary getSummary() {
        return library.getSummary();
    }

}
