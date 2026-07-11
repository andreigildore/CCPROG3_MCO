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
    public void addAnime(Anime input, int initialStatus) {
        if (initialStatus == 0 || initialStatus == 1) {
            input.updateStatus(initialStatus);
            animes.add(input);
        } else {
            System.out.println("Invalid initial status. New entries must be Planned or In Progress.");
        }
    }

    public void addTVSeries(TVSeries input, int initialStatus) {
        if (initialStatus == 0 || initialStatus == 1) {
            input.updateStatus(initialStatus);
            tvSeries.add(input);
        } else {
            System.out.println("Invalid initial status. New entries must be Planned or In Progress.");
        }
    }

    public void addMusicSingle(MusicSingle input, int initialStatus) {
        if (initialStatus == 0 || initialStatus == 1) {
            input.updateStatus(initialStatus);
            musicSingles.add(input);
        } else {
            System.out.println("Invalid initial status. New entries must be Planned or In Progress.");
        }
    }

    public void addMusicAlbum(MusicAlbum input, int initialStatus) {
        if (initialStatus == 0 || initialStatus == 1) {
            input.updateStatus(initialStatus);
            musicAlbums.add(input);
        } else {
            System.out.println("Invalid initial status. New entries must be Planned or In Progress.");
        }
    }

    public void addVideoGame(VideoGame input, int initialStatus) {
        if (initialStatus == 0 || initialStatus == 1) {
            input.updateStatus(initialStatus);
            videoGames.add(input);
        } else {
            System.out.println("Invalid initial status. New entries must be Planned or In Progress.");
        }
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
        System.out.println("\n--- Filtering by Status: " + StatusMapper.getStatus(status) + " ---");
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
            for (TVSeries tvS : tvSeries) System.out.println(tvS.displayInfo());
        } else if (type.equalsIgnoreCase("MusicSingle")) {
            for (MusicSingle musicSingle : musicSingles) System.out.println(musicSingle.displayInfo());
        } else if (type.equalsIgnoreCase("MusicAlbum")) {
            for (MusicAlbum musicAlbum : musicAlbums) System.out.println(musicAlbum.displayInfo());
        } else {
            System.out.println("Invalid media type.");
        }
    }

    public void displaySummary() {
        int total = animes.size() + tvSeries.size() + musicSingles.size() + musicAlbums.size() + videoGames.size();

        int planned = 0, inProgress = 0, completed = 0;
        int ratedCount = 0;
        int ratingSum = 0;

        for (Anime anime : animes) {
            if (anime.getStatus() == 0) planned++;
            else if (anime.getStatus() == 1) inProgress++;
            else if (anime.getStatus() == 2) { 
                completed++; 
                if (anime.getRating() > 0) { 
                    ratingSum += anime.getRating(); 
                    ratedCount++; } 
                }
        }

        for (TVSeries tvS : tvSeries) {
            if (tvS.getStatus() == 0) planned++;
            else if (tvS.getStatus() == 1) inProgress++;
            else if (tvS.getStatus() == 2) { 
                completed++; 
                if (tvS.getRating() > 0) { 
                    ratingSum += tvS.getRating(); 
                    ratedCount++; } 
                }
        }

        for (MusicSingle musicSingle : musicSingles) {
            if (musicSingle.getStatus() == 0) planned++;
            else if (musicSingle.getStatus() == 1) inProgress++;
            else if (musicSingle.getStatus() == 2) { 
                completed++; 
                if (musicSingle.getRating() > 0) { 
                    ratingSum += musicSingle.getRating(); 
                    ratedCount++; } 
                }
        }

        for (MusicAlbum musicAlbum : musicAlbums) {
            if (musicAlbum.getStatus() == 0) planned++;
            else if (musicAlbum.getStatus() == 1) inProgress++;
            else if (musicAlbum.getStatus() == 2) { 
                completed++; 
                if (musicAlbum.getRating() > 0) { 
                    ratingSum += musicAlbum.getRating(); 
                    ratedCount++; } 
                }
        }
        
        for (VideoGame videoGame : videoGames) {
            if (videoGame.getStatus() == 0) planned++;
            else if (videoGame.getStatus() == 1) inProgress++;
            else if (videoGame.getStatus() == 2) { 
                completed++; 
                if (videoGame.getRating() > 0) { 
                    ratingSum += videoGame.getRating(); 
                    ratedCount++; } 
                }
        }

        String avgRatingText = ratedCount == 0 ? "N/A" : String.format("%.2f", (double) ratingSum / ratedCount);

        System.out.println(String.format("""

            Library Summary 
        Total Entries   : %d
        Anime           : %d
        Video Games     : %d
        TV Series       : %d
        Music Singles   : %d
        Music Albums    : %d
        ------------------------
        Planned         : %d
        In Progress     : %d
        Completed       : %d
        ------------------------
        Avg Rating (Completed) : %s
        
        """,
        total,
        animes.size(),
        videoGames.size(),
        tvSeries.size(),
        musicSingles.size(),
        musicAlbums.size(),
        planned,
        inProgress,
        completed,
        avgRatingText
        ));
    }
}
