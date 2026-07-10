import java.util.ArrayList;

public class Library {
    private ArrayList<Anime> animes;
    private ArrayList<TVSeries> tvSeries;
    private ArrayList<MusicSingle> musicSingles;
    private ArrayList<MusicAlbum> musicAlbums;
    private ArrayList<VideoGame> videoGames;
	
	public Library() {
		this.animes = new ArrayList<>();
		this.tvSeries = new ArrayList<>();
        this.musicSingles = new ArrayList<>();
        this.musicAlbums = new ArrayList<>();
        this.videoGames = new ArrayList<>();
	}

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

    public void addVideoGame(VideoGame input) {
        videoGames.add(input);
    }

    /* removal methods */
    public void removeAnime(Anime input) {
        animes.remove(input);
    }

    public void removeTVSeries(TVSeries input) {
        tvSeries.remove(input);
    }

    public void removeMusicSingle(MusicSingle input) {
        musicSingles.remove(input);
    }

    public void removeMusicAlbum(MusicAlbum input) {
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
        return tvSeries;
    }

    public ArrayList<MusicSingle> getMusicSingles() {
        return musicSingles;
    }

    public ArrayList<MusicAlbum> getMusicAlbums() {
        return musicAlbums;
    }

    public ArrayList<VideoGame> getVideoGames() {
        return videoGames;
    }

    public void updateStatus(String mediaTitle, int newStatus) {
    }

    public void displayAllEntries() {
    }

    public void filterByStatus(int status) {
    }

    public void filterByType(String type) {
    }

    public void displaySummary() {
    }
}
