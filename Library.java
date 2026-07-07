import java.util.ArrayList;

public class Library {
    private ArrayList<Anime> animes;
    private ArrayList<TVSeries> tvSeries;
    private ArrayList<MusicSingle> musicSingles;
    private ArrayList<MusicAlbum> musicAlbums;
    private ArrayList<VideoGame> videoGames;

    /* adding methods */
    public void addAnime(Anime input) {
        animes.add(input);
    }
    
    public void addTVSeries(TVSeries input) {
        tvSeries.add(input);
    }

    public void addMusicSingle(MusicSingle input) {
        musicSingles.add(input);
    }

    public void addMusicAlbum(MusicAlbum input) {
        musicAlbums.add(input);
    }

    public void addVideoGame(Anime input) {
        videoGames.add(input);
    }

    /* removal methods */
    public void removeAnime(Anime input) {
        anime.remove(input);
    }

    public void removeTVSeries(TVSeries input) {
        tvSeries.remove(input);
    }

    public void removeMusicSingle(MusicSingle input) {
        musicSingles.remove(input);
    }

    public void removeMusicAlbum(MusicAlbums input) {
        musicAlbums.remove(input);
    }

    public void removeVideoGame(VideoGame input) {
        videoGames.remove(input);
    }

    /* retrieval methods */
    public ArrayList<Anime> getAnimes() {
        return animes;
    }

    public ArrayList<TVSeries> getTVSeries() {
        return animes;
    }

    public ArrayList<MusicSingle> getMusicSingles() {
        return animes;
    }

    public ArrayList<MusicAlbum> getMusicAlbums() {
        return animes;
    }

    public ArrayList<VideoGame> getVideoGames() {
        return animes;
    }

    /* search specific media by name */
}
