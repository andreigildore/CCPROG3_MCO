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
        for (Anime anime : animes) {
            if (anime.getTitle().equalsIgnoreCase(mediaTitle)) { 
                anime.updateStatus(newStatus); 
                System.out.println("Status updated."); 
                return; 
            }
        }
        for (TVSeries tvS : tvSeries) {
            if (tvS.getTitle().equalsIgnoreCase(mediaTitle)) { 
                tvS.updateStatus(newStatus); 
                System.out.println("Status updated."); 
                return; 
            }
        }
        for (MusicSingle musicSingle : musicSingles) {
            if (musicSingle.getTitle().equalsIgnoreCase(mediaTitle)) { 
                musicSingle.updateStatus(newStatus); 
                System.out.println("Status updated."); 
                return; 
            }
        }
        for (MusicAlbum musicAlbum : musicAlbums) {
            if (musicAlbum.getTitle().equalsIgnoreCase(mediaTitle)) { 
                musicAlbum.updateStatus(newStatus); 
                System.out.println("Status updated."); 
                return; 
            }
        }
        for (VideoGame videoGame : videoGames) {
            if (videoGame.getTitle().equalsIgnoreCase(mediaTitle)) { 
                videoGame.updateStatus(newStatus); 
                System.out.println("Status updated."); 
                return; 
            }
        }
        System.out.println("Error: '" + mediaTitle + "' not found in your library.");
    }

    public void displayAllEntries() {
        System.out.println("\n--- Your Media Library ---");
        for (Anime anime : animes)                   System.out.println(anime.displayInfo());
        for (TVSeries tvS : tvSeries)                System.out.println(tvS.displayInfo());
        for (MusicSingle musicSingle : musicSingles) System.out.println(musicSingle.displayInfo());
        for (MusicAlbum musicAlbum : musicAlbums)    System.out.println(musicAlbum.displayInfo());
        for (VideoGame videoGame : videoGames)       System.out.println(videoGame.displayInfo());
    }

    public void filterByStatus(int status) {
        System.out.println("\n--- Filtering by Status: " + status + " ---");
        for (Anime anime : animes) { 
            if (anime.getStatus() == status) 
                System.out.println(anime.displayInfo()); 
            }
        for (TVSeries tvS : tvSeries) { 
            if (tvS.getStatus() == status) 
                System.out.println(tvS.displayInfo()); 
            }
        for (MusicSingle musicSingle : musicSingles) { 
            if (musicSingle.getStatus() == status) 
                System.out.println(musicSingle.displayInfo()); 
            }
        for (MusicAlbum musicAlbum : musicAlbums) { 
            if (musicAlbum.getStatus() == status) 
                System.out.println(musicAlbum.displayInfo()); 
            }
        for (VideoGame videoGame : videoGames) { 
            if (videoGame.getStatus() == status) 
                System.out.println(videoGame.displayInfo()); 
            }
    }

    public void filterByType(String type) {
        System.out.println("\n--- Filtering by Type: " + type + " ---");
        if (type.equalsIgnoreCase("Anime")) {
            for (Anime anime : animes) System.out.println(anime.displayInfo());
        } else if (type.equalsIgnoreCase("VideoGame")) {
            for (VideoGame videoGame : videoGames) System.out.println(videoGame.displayInfo());
        } else if (type.equalsIgnoreCase("TVSeries")) {
            for (TVSeries t : tvSeries) System.out.println(t.displayInfo());
        } else if (type.equalsIgnoreCase("MusicSingle")) {
            for (MusicSingle ms : musicSingles) System.out.println(ms.displayInfo());
        } else if (type.equalsIgnoreCase("MusicAlbum")) {
            for (MusicAlbum ma : musicAlbums) System.out.println(ma.displayInfo());
        } else {
            System.out.println("Invalid media type.");
        }
    }

    public void displaySummary() {
        int total = animes.size() + tvSeries.size() + musicSingles.size() + musicAlbums.size() + videoGames.size();
        System.out.println("\n=== Library Summary ===");
        System.out.println("Total Entries: " + total);
        System.out.println("Anime: " + animes.size());
        System.out.println("Video Games: " + videoGames.size());
        System.out.println("TV Series: " + tvSeries.size());
        System.out.println("Music Singles: " + musicSingles.size());
        System.out.println("Music Albums: " + musicAlbums.size());
        System.out.println("=======================");
    }
}
