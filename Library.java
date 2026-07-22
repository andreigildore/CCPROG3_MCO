import java.util.ArrayList;

/**
 * Stores and manages all media entries in the user's library.
 */
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

    /* Adding methods. */

    /**
     * Adds an Anime to the library.
     * 
     * @param input the Anime object to add
     * @param initialStatus the starting status (0=Planned, 1=In Progress)
     * Pre-condition: Input is a valid Anime object, initialStatus is 0 or 1.
     * Post-condition: The anime is added to the library with the specified status.
     */
    public void addAnime(Anime input, int initialStatus) {
        if (initialStatus == 0 || initialStatus == 1) {
            input.updateStatus(initialStatus);
            animes.add(input);
            System.out.println("Successfully added " + input.getTitle() + " as Anime to your library.");
        } 
        else {
            System.out.println("Invalid initial status. New entries must be Planned or In Progress.");
        }
    }

    /**
     * Adds a TVSeries to the library.
     * 
     * @param input the TVSeries object to add
     * @param initialStatus the starting status (0=Planned, 1=In Progress)
     * Pre-condition: Input is a valid TVSeries object, initialStatus is 0 or 1.
     * Post-condition: The TV series is added to the library with the specified status.
     */
    public void addTVSeries(TVSeries input, int initialStatus) {
        if (initialStatus == 0 || initialStatus == 1) {
            input.updateStatus(initialStatus);
            tvSeries.add(input);
            System.out.println("Successfully added " + input.getTitle() + " as TV Series to your library.");
        } 
        else {
            System.out.println("Invalid initial status. New entries must be Planned or In Progress.");
        }
    }

    /**
     * Adds a MusicSingle to the library.
     * 
     * @param input the MusicSingle object to add
     * @param initialStatus the starting status (0=Planned, 1=In Progress)
     * Pre-condition: Input is a valid MusicSingle object, initialStatus is 0 or 1.
     * Post-condition: The music single is added to the library with the specified status.
     */
    public void addMusicSingle(MusicSingle input, int initialStatus) {
        if (initialStatus == 0 || initialStatus == 1) {
            input.updateStatus(initialStatus);
            musicSingles.add(input);
            System.out.println("Successfully added " + input.getTitle() + " as Music Single to your library.");
        } 
        else {
            System.out.println("Invalid initial status. New entries must be Planned or In Progress.");
        }
    }

    /**
     * Adds a MusicAlbum to the library.
     * 
     * @param input the MusicAlbum object to add
     * @param initialStatus the starting status (0=Planned, 1=In Progress)
     * Pre-condition: Input is a valid MusicAlbum object, initialStatus is 0 or 1.
     * Post-condition: The music album is added to the library with the specified status.
     */
    public void addMusicAlbum(MusicAlbum input, int initialStatus) {
        if (initialStatus == 0 || initialStatus == 1) {
            input.updateStatus(initialStatus);
            musicAlbums.add(input);
            System.out.println("Successfully added " + input.getTitle() + " as Music Album to your library.");
        } 
        else {
            System.out.println("Invalid initial status. New entries must be Planned or In Progress.");
        }
    }

    /**
     * Adds a VideoGame to the library.
     * 
     * @param input the VideoGame object to add
     * @param initialStatus the starting status (0=Planned, 1=In Progress)
     * Pre-condition: Input is a valid VideoGame object, initialStatus is 0 or 1.
     * Post-condition: The video game is added to the library with the specified status.
     */
    public void addVideoGame(VideoGame input, int initialStatus) {
        if (initialStatus == 0 || initialStatus == 1) {
            input.updateStatus(initialStatus);
            videoGames.add(input);
            System.out.println("Successfully added " + input.getTitle() + " as Video Game to your library.");
        } 
        else {
            System.out.println("Invalid initial status. New entries must be Planned or In Progress.");
        }
    }

    /* Removal methods. */

    /**
     * Removes an Anime from the library.
     *
     * @param input the Anime object to remove
     * Pre-condition: Input is a valid Anime object present in the library.
     * Post-condition: The anime is removed from the library.
     */
    public void removeAnime(Anime input) {
        animes.remove(input);
    }

    /**
     * Removes a TVSeries from the library.
     *
     * @param input the TVSeries object to remove
     * Pre-condition: Input is a valid TVSeries object present in the library.
     * Post-condition: The TV series is removed from the library.
     */
    public void removeTVSeries(TVSeries input) {
        tvSeries.remove(input);
    }

    /**
     * Removes a MusicSingle from the library.
     *
     * @param input the MusicSingle object to remove
     * Pre-condition: Input is a valid MusicSingle object present in the library.
     * Post-condition: The music single is removed from the library.
     */
    public void removeMusicSingle(MusicSingle input) {
        musicSingles.remove(input);
    }

    /**
     * Removes a MusicAlbum from the library.
     *
     * @param input the MusicAlbum object to remove
     * Pre-condition: Input is a valid MusicAlbum object present in the library.
     * Post-condition: The music album is removed from the library.
     */
    public void removeMusicAlbum(MusicAlbum input) {
        musicAlbums.remove(input);
    }

    /**
     * Removes a VideoGame from the library.
     *
     * @param input the VideoGame object to remove
     * Pre-condition: Input is a valid VideoGame object present in the library.
     * Post-condition: The video game is removed from the library.
     */
    public void removeVideoGame(VideoGame input) {
        videoGames.remove(input);
    }

    /* Retrieval methods. */

    /**
     * Retrieves the list of animes in the library.
     *
     * @return the list of Anime objects
     * Pre-condition: The library has been initialized.
     * Post-condition: The list of animes is returned.
     */
    public ArrayList<Anime> getAnimes() {
        return animes;
    }

    /**
     * Retrieves the list of TV series in the library.
     *
     * @return the list of TVSeries objects
     * Pre-condition: The library has been initialized.
     * Post-condition: The list of TV series is returned.
     */
    public ArrayList<TVSeries> getTVSeries() {
        return tvSeries;
    }

    /**
     * Retrieves the list of music singles in the library.
     *
     * @return the list of MusicSingle objects
     * Pre-condition: The library has been initialized.
     * Post-condition: The list of music singles is returned.
     */
    public ArrayList<MusicSingle> getMusicSingles() {
        return musicSingles;
    }

    /**
     * Retrieves the list of music albums in the library.
     *
     * @return the list of MusicAlbum objects
     * Pre-condition: The library has been initialized.
     * Post-condition: The list of music albums is returned.
     */
    public ArrayList<MusicAlbum> getMusicAlbums() {
        return musicAlbums;
    }

    /**
     * Retrieves the list of video games in the library.
     *
     * @return the list of VideoGame objects
     * Pre-condition: The library has been initialized.
     * Post-condition: The list of video games is returned.
     */
    public ArrayList<VideoGame> getVideoGames() {
        return videoGames;
    }

    /* Search specific object in list methods. */

    /**
     * Searches for an Anime by its title.
     *
     * @param title the title of the anime to search for
     * @return the Anime object if found, otherwise null
     * Pre-condition: The title is a valid string.
     * Post-condition: The matching Anime object is returned, or null if not found.
     */
    public Anime getAnimeByTitle(String title) {
        for (Anime anime : animes) {
            if (anime.getTitle().equalsIgnoreCase(title))
                return anime;
        }
        return null;
    }

    /**
     * Searches for a TVSeries by its title.
     *
     * @param title the title of the TV series to search for
     * @return the TVSeries object if found, otherwise null
     * Pre-condition: The title is a valid string.
     * Post-condition: The matching TVSeries object is returned, or null if not found.
     */
    public TVSeries getTVSeriesByTitle(String title) {
        for (TVSeries tvS : tvSeries) {
            if (tvS.getTitle().equalsIgnoreCase(title))
                return tvS;
        }
        return null;
    }

    /**
     * Searches for a VideoGame by its title.
     *
     * @param title the title of the video game to search for
     * @return the VideoGame object if found, otherwise null
     * Pre-condition: The title is a valid string.
     * Post-condition: The matching VideoGame object is returned, or null if not found.
     */
    public VideoGame getVideoGameByTitle(String title) {
        for (VideoGame videoGame : videoGames) {
            if (videoGame.getTitle().equalsIgnoreCase(title))
                return videoGame;
        }
        return null;
    }

    /**
     * Searches for a MusicAlbum by its title.
     *
     * @param title the title of the music album to search for
     * @return the MusicAlbum object if found, otherwise null
     * Pre-condition: The title is a valid string.
     * Post-condition: The matching MusicAlbum object is returned, or null if not found.
     */
    public MusicAlbum getMusicAlbumByTitle(String title) {
        for (MusicAlbum musicAlbum : musicAlbums) {
            if (musicAlbum.getTitle().equalsIgnoreCase(title))
                return musicAlbum;
        }
        return null;
    }

    /**
     * Searches for a MusicSingle by its title.
     *
     * @param title the title of the music single to search for
     * @return the MusicSingle object if found, otherwise null
     * Pre-condition: The title is a valid string.
     * Post-condition: The matching MusicSingle object is returned, or null if not found.
     */
    public MusicSingle getMusicSingleByTitle(String title) {
        for (MusicSingle musicSingle : musicSingles) {
            if (musicSingle.getTitle().equalsIgnoreCase(title))
                return musicSingle;
        }
        return null;
    }

    /**
     * Finds a media entry by type and title, then updates its status.
     * 
     * @param type the type of media to search for
     * @param mediaTitle the title of the media to update
     * @param newStatus the new status to set
     * Pre-condition: The type and mediaTitle are valid strings, newStatus is a valid status integer.
     * Post-condition: The status of the matching media entry is updated if found.
     */
    public void updateStatus(String type, String mediaTitle, int newStatus) {
        // Search the matching type list for the title, update if found.
        if (type.equalsIgnoreCase("Anime")) {
            for (Anime anime : animes) {
                if (anime.getTitle().equalsIgnoreCase(mediaTitle)) { 
                    System.out.println("Current status of " + mediaTitle + ": " + StatusMapper.getStatusString(anime.getStatus()));
                    anime.updateStatus(newStatus); 
                    System.out.println("Status updated."); 
                    return; 
                }
            }
        } 
        else if (type.equalsIgnoreCase("TVSeries")) {
            for (TVSeries tvS : tvSeries) {
                if (tvS.getTitle().equalsIgnoreCase(mediaTitle)) { 
                    System.out.println("Current status of " + mediaTitle + ": " + StatusMapper.getStatusString(tvS.getStatus()));
                    tvS.updateStatus(newStatus); 
                    System.out.println("Status updated."); 
                    return; 
                }
            }
        } 
        else if (type.equalsIgnoreCase("MusicSingle")) {
            for (MusicSingle musicSingle : musicSingles) {
                if (musicSingle.getTitle().equalsIgnoreCase(mediaTitle)) { 
                    System.out.println("Current status of " + mediaTitle + ": " + StatusMapper.getStatusString(musicSingle.getStatus()));
                    musicSingle.updateStatus(newStatus); 
                    System.out.println("Status updated."); 
                    return; 
                }
            }
        } 
        else if (type.equalsIgnoreCase("MusicAlbum")) {
            for (MusicAlbum musicAlbum : musicAlbums) {
                if (musicAlbum.getTitle().equalsIgnoreCase(mediaTitle)) { 
                    System.out.println("Current status of " + mediaTitle + ": " + StatusMapper.getStatusString(musicAlbum.getStatus()));
                    musicAlbum.updateStatus(newStatus); 
                    System.out.println("Status updated."); 
                    return; 
                }
            }
        } 
        else if (type.equalsIgnoreCase("VideoGame")) {
            for (VideoGame videoGame : videoGames) {
                if (videoGame.getTitle().equalsIgnoreCase(mediaTitle)) { 
                    System.out.println("Current status of " + mediaTitle + ": " + StatusMapper.getStatusString(videoGame.getStatus()));
                    videoGame.updateStatus(newStatus); 
                    System.out.println("Status updated."); 
                    return; 
                }
            }
        } 
        else {
            System.out.println("Invalid media type.");
            return;
        }
        System.out.println("Error: '" + mediaTitle + "' not found in your " + type + " library.");
    }
    /**
     * Displays all entries across every media type.
     * 
     * Pre-condition: The library has been initialized.
     * Post-condition: All entries in the library are printed to the console.
     */
    public void displayAllEntries() {
        int total = animes.size() + tvSeries.size() + musicSingles.size() + musicAlbums.size() + videoGames.size();
        // Skip display if library has no entries at all.
        if (total == 0) {
            System.out.println("\nYour library is empty. Add some media to get started!");
            return;
        }
        System.out.println("\n--- Your Media Library ---");
        for (Anime anime : animes)                   System.out.println(anime.displayInfo());
        for (TVSeries tvS : tvSeries)                System.out.println(tvS.displayInfo());
        for (MusicSingle musicSingle : musicSingles) System.out.println(musicSingle.displayInfo());
        for (MusicAlbum musicAlbum : musicAlbums)    System.out.println(musicAlbum.displayInfo());
        for (VideoGame videoGame : videoGames)       System.out.println(videoGame.displayInfo());
    }

    /**
     * Displays only entries that match the given status.
     *
     * @param status the status to filter by
     * Pre-condition: The status is a valid status integer.
     * Post-condition: All matching entries are printed to the console.
     */
    public void filterByStatus(int status) {
        System.out.println("\n--- Filtering by Status: " + StatusMapper.getStatusString(status) + " ---");
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

    /**
     * Displays only entries of the given media type.
     *
     * @param type the type of media to filter by
     * Pre-condition: The type is a valid string.
     * Post-condition: All matching entries are printed to the console.
     */
    public void filterByType(String type) {
        System.out.println("\n--- Filtering by Type: " + type + " ---");
        if (type.equalsIgnoreCase("Anime")) {
            for (Anime anime : animes) 
                System.out.println(anime.displayInfo());
        } else if (type.equalsIgnoreCase("VideoGame")) {
            for (VideoGame videoGame : videoGames) 
                System.out.println(videoGame.displayInfo());
        } else if (type.equalsIgnoreCase("TVSeries")) {
            for (TVSeries tvS : tvSeries) 
                System.out.println(tvS.displayInfo());
        } else if (type.equalsIgnoreCase("MusicSingle")) {
            for (MusicSingle musicSingle : musicSingles) 
                System.out.println(musicSingle.displayInfo());
        } else if (type.equalsIgnoreCase("MusicAlbum")) {
            for (MusicAlbum musicAlbum : musicAlbums) 
                System.out.println(musicAlbum.displayInfo());
        } else {
            System.out.println("Invalid media type.");
        }
    }

    /**
     * Prints a summary of library stats including counts and average rating.
     *
     * Pre-condition: The library has been initialized.
     * Post-condition: A summary of the library is printed to the console.
     */
    public void displaySummary() {
        int total = animes.size() + tvSeries.size() + musicSingles.size() + musicAlbums.size() + videoGames.size();

        int planned = 0, inProgress = 0, completed = 0;
        // Tracks how many completed entries have a rating and their total sum.
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

        // Average rating only from completed entries that have been rated.
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
