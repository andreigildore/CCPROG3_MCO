import java.util.Scanner;

/**
 * Handles all user interaction and drives the media vault program.
 */
public class MainDriver {
    private static final Scanner sc = new Scanner(System.in);
    private static final User currentUser = new User(); // Assume only one user for the program.

    /**
     * The main function of the program, enables all the classes/objects in the program
     * 
     * @param args the command line arguments
     * Pre-condition: None.
     * Post-condition: The program executes and exits when the user chooses to.
     */
    public static void main(String args[]) {
        System.out.println("""
            
            Welcome to the Media Vault!
            """);
        System.out.println("Use sample user details? (y/n)");
        if (readYesOrNo())
            setupUser();
        else {
            System.out.println("Enter Username : ");
            String username = readString();
            System.out.println("Enter Email : ");
            String email = readString();
            System.out.println("Enter Password : ");
            String password = readString();
            currentUser.register(username, email, password);
            currentUser.login(username, password);
        }
        boolean programRuns = true;

        // Main loop keeps showing the menu until the user picks exit.
        while (programRuns) {
            printMenu();
            System.out.println("Enter Input (1-7) : ");
            int choice = readValidInt();

            switch (choice) {
                case 1:
                    addNewMedia();
                    break;
                case 2:
                    updateEntryStatus();
                    break;
                case 3:
                    rateReviewMedia();
                    break;
                case 4:
                    displayEntries();
                    break;
                case 5:
                    currentUser.getLibrary().displaySummary();
                    break;
                case 6:
                    manageEpisodesOrTracks();
                    break;
                case 7:
                    programRuns = false;
                    break;
                default: 
                    System.out.println(choice + " is an invalid choice. Try again.");
            }
        }
        System.out.println("Closing the vault...");
    }
    
    /**
     * Registers and logs in a sample user for quick testing.
     * 
     * Pre-condition: None.
     * Post-condition: A sample user is registered and logged in.
     */
    private static void setupUser() {
        currentUser.register("jdjdsbgad", "jdjdsbgad64@email.com", "jdjdsbgad!@#1234");
        currentUser.login("jdjdsbgad", "jdjdsbgad!@#1234");
    }

    /**
     * Prints the main menu options to the console.
     * 
     * Pre-condition: None.
     * Post-condition: The main menu is displayed on the console.
     */
    private static void printMenu() {
        System.out.println("""

                        MAIN MENU
                        1. Add new media entry
                        2. Update status of an entry
                        3. Rate and/or Review a completed media
                        4. Display Entries
                        5. Display library Summary
                        6. Manage episodes/tracks
                        7. Exit
                        """);
    }

    /* Menu methods. */

    /**
     * Tells the user to pick a media type and collects details to add a new entry.
     * 
     * Pre-condition: The user is logged in.
     * Post-condition: A new media entry is added to the user's library if valid input is provided.
     */
    public static void addNewMedia() {
        Library library = currentUser.getLibrary();
        boolean running = true;
        while (running) {
            System.out.println("""

                                Select the media type of the entry to be added:
                                1. Anime
                                2. TV Series
                                3. Video Game
                                4. Music Single
                                5. Music Album
                                6. Go back
                                """);
            int type = readValidInt();
            switch (type) { // Block inside asks the user's input based on the type, then adds the entry.
                case 1:
                    System.out.println("Enter anime Title : ");
                    String title = readString();
                    System.out.println("Enter Genre : ");
                    String genre = readString();
                    System.out.println("Enter Animation Studio: ");
                    String studio = readString();
                    System.out.println("Enter Creator : ");
                    String creator = readString();
                    System.out.println("Enter number of Episodes : ");
                    int noOfEpisodes = readValidInt();
                    System.out.println("Subbed?");
                    boolean isSubbed = readYesOrNo();
                    int initialStatus = readInitialStatus();
                    Anime anime = new Anime(title, genre, studio, creator, noOfEpisodes, isSubbed);
                    library.addAnime(anime, initialStatus);
                    break;
                case 2:
                    System.out.println("Enter TV Series Title : ");
                    String tvTitle = readString();
                    System.out.println("Enter Genre : ");
                    String tvGenre = readString();
                    System.out.println("Enter Creator : ");
                    String tvCreator = readString();
                    System.out.println("Enter number of Episodes : ");
                    int tvNoOfEpisodes = readValidInt();
                    System.out.println("Reality TV?");
                    boolean isRealityTV = readYesOrNo();
                    int tvInitialStatus = readInitialStatus();
                    TVSeries tvSeries = new TVSeries(tvTitle, tvGenre, tvCreator, tvNoOfEpisodes, isRealityTV);
                    library.addTVSeries(tvSeries, tvInitialStatus);
                    break;
                case 3:
                    System.out.println("Enter Video Game Title : ");
                    String vgTitle = readString();
                    System.out.println("Enter Genre : ");
                    String vgGenre = readString();
                    System.out.println("Enter Developer : ");
                    String developer = readString();
                    System.out.println("Enter Platform : ");
                    String platform = readString();
                    System.out.println("Enter Playtime (hrs) : ");
                    int playtime = readValidInt();
                    int vgInitialStatus = readInitialStatus();
                    VideoGame videoGame = new VideoGame(vgTitle, vgGenre, playtime, developer, platform);
                    library.addVideoGame(videoGame, vgInitialStatus);
                    break;
                case 4:
                    System.out.println("Enter Song Title : ");
                    String msTitle = readString();
                    System.out.println("Enter Genre : ");
                    String msGenre = readString();
                    System.out.println("Enter Artist : ");
                    String msArtist = readString();
                    System.out.println("Enter Record Label : ");
                    String msRecordLabel = readString();
                    int msInitialStatus = readInitialStatus();
                    MusicSingle musicSingle = new MusicSingle(msTitle, msGenre, msArtist, msRecordLabel);
                    library.addMusicSingle(musicSingle, msInitialStatus);
                    break;
                case 5:
                    System.out.println("Enter Album Title : ");
                    String maTitle = readString();
                    System.out.println("Enter Genre : ");
                    String maGenre = readString();
                    System.out.println("Enter Artist : ");
                    String maArtist = readString();
                    System.out.println("Enter Record Label : ");
                    String maRecordLabel = readString();
                    System.out.println("Enter number of Tracks : ");
                    int noOfTracks = readValidInt();
                    int maInitialStatus = readInitialStatus();
                    MusicAlbum musicAlbum = new MusicAlbum(maTitle, noOfTracks, maGenre, maArtist, maRecordLabel);
                    library.addMusicAlbum(musicAlbum, maInitialStatus);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println(type + " is an invalid choice. Try again.");
            }
        }
    }

    /**
     * Lets the user pick a media entry by type and title, then update its status.
     * 
     * Pre-condition: User is logged in.
     * Post-condition: The status of the selected media entry is updated if found and a valid status is provided.
     */
    public static void updateEntryStatus() {
        Library library = currentUser.getLibrary();
        System.out.println("""

            Select media type:
            1. Anime
            2. TV Series
            3. Video Game
            4. Music Single
            5. Music Album
            6. Go back
            """);
        int type = readValidInt();
        switch (type) {
            case 1:
                System.out.println("Enter Anime Title : ");
                String animeTitle = readString();
                int animeNewStatus = readNewStatus();
                library.updateStatus("Anime", animeTitle, animeNewStatus);
                break;
            case 2:
                System.out.println("Enter TV Series Title : ");
                String tvTitle = readString();
                int tvNewStatus = readNewStatus();
                library.updateStatus("TVSeries", tvTitle, tvNewStatus);
                break;
            case 3:
                System.out.println("Enter Video Game Title : ");
                String vgTitle = readString();
                int vgNewStatus = readNewStatus();
                library.updateStatus("VideoGame", vgTitle, vgNewStatus);
                break;
            case 4:
                System.out.println("Enter Song Title : ");
                String msTitle = readString();
                int msNewStatus = readNewStatus();
                library.updateStatus("MusicSingle", msTitle, msNewStatus);
                break;
            case 5:
                System.out.println("Enter Album Title : ");
                String maTitle = readString();
                int maNewStatus = readNewStatus();
                library.updateStatus("MusicAlbum", maTitle, maNewStatus);
                break;
            case 6:
                break;
            default:
                System.out.println(type + " is an invalid choice.");
        }
    }
 
    /**
     * Lets the user rate and/or review a completed media entry.
     * 
     * Pre-condition: The user is logged in and the selected media entry must exist.
     * Post-condition: The rating and/or review is applied to the selected media entry if it is completed.
     */
    public static void rateReviewMedia() {
        Library library = currentUser.getLibrary();
        System.out.println("""

            Select media type:
            1. Anime
            2. TV Series
            3. Video Game
            4. Music Single
            5. Music Album
            6. Go back
            """);
        int type = readValidInt();
        switch (type) {
            case 1:
                System.out.println("Enter Anime Title : ");
                String animeTitle = readString();
                Anime anime = library.getAnimeByTitle(animeTitle);
                if (anime == null) {
                    System.out.println("Anime '" + animeTitle + "' not found.");
                    break;
                }
                int animeChoice = readRateReviewChoice();
                // Choice 1 = rate only, 3 = both, thus either triggers rating.
                if (animeChoice == 1 || animeChoice == 3) {
                    System.out.println("Enter Rating (1-10) : ");
                    anime.rate(readValidInt());
                }
                // Choice 2 = review only, 3 = both, thus either triggers review.
                if (animeChoice == 2 || animeChoice == 3) {
                    System.out.println("Enter Review : ");
                    anime.review(readString());
                }
                break;
            case 2:
                System.out.println("Enter TV Series Title : ");
                String tvTitle = readString();
                TVSeries tvSeries = library.getTVSeriesByTitle(tvTitle);
                if (tvSeries == null) {
                    System.out.println("TV Series '" + tvTitle + "' not found.");
                    break;
                }
                int tvChoice = readRateReviewChoice();
                if (tvChoice == 1 || tvChoice == 3) {
                    System.out.println("Enter Rating (1-10) : ");
                    tvSeries.rate(readValidInt());
                }
                if (tvChoice == 2 || tvChoice == 3) {
                    System.out.println("Enter Review : ");
                    tvSeries.review(readString());
                }
                break;
            case 3:
                System.out.println("Enter Video Game Title : ");
                String vgTitle = readString();
                VideoGame videoGame = library.getVideoGameByTitle(vgTitle);
                if (videoGame == null) {
                    System.out.println("Video Game '" + vgTitle + "' not found.");
                    break;
                }
                int vgChoice = readRateReviewChoice();
                if (vgChoice == 1 || vgChoice == 3) {
                    System.out.println("Enter Rating (1-10) : ");
                    videoGame.rate(readValidInt());
                }
                if (vgChoice == 2 || vgChoice == 3) {
                    System.out.println("Enter Review : ");
                    videoGame.review(readString());
                }
                break;
            case 4:
                System.out.println("Enter Song Title : ");
                String msTitle = readString();
                MusicSingle musicSingle = library.getMusicSingleByTitle(msTitle);
                if (musicSingle == null) {
                    System.out.println("Music Single '" + msTitle + "' not found.");
                    break;
                }
                int msChoice = readRateReviewChoice();
                if (msChoice == 1 || msChoice == 3) {
                    System.out.println("Enter Rating (1-10) : ");
                    musicSingle.rate(readValidInt());
                }
                if (msChoice == 2 || msChoice == 3) {
                    System.out.println("Enter Review : ");
                    musicSingle.review(readString());
                }
                break;
            case 5:
                System.out.println("Enter Album Title : ");
                String maTitle = readString();
                MusicAlbum musicAlbum = library.getMusicAlbumByTitle(maTitle);
                if (musicAlbum == null) {
                    System.out.println("Music Album '" + maTitle + "' not found.");
                    break;
                }
                int maChoice = readRateReviewChoice();
                if (maChoice == 1 || maChoice == 3) {
                    System.out.println("Enter Rating (1-10) : ");
                    musicAlbum.rate(readValidInt());
                }
                if (maChoice == 2 || maChoice == 3) {
                    System.out.println("Enter Review : ");
                    musicAlbum.review(readString());
                }
                break;
            case 6:
                break;
            default:
                System.out.println(type + " is an invalid choice.");
        }
    }
 
    /**
     * Displays library entries with optional filtering by status or type.
     * 
     * Pre-condition: The user is logged in.
     * Post-condition: The requested entries are displayed to the console.
     */
    public static void displayEntries() {
        Library library = currentUser.getLibrary();
        boolean running = true;
        while (running) {
            System.out.println("""

                1. Display all entries
                2. Filter by status
                3. Filter by type
                4. Go back
                """);
            int choice = readValidInt();
            switch (choice) {
                case 1:
                    library.displayAllEntries();
                    break;
                case 2:
                    int status = readNewStatus();
                    library.filterByStatus(status);
                    break;
                case 3:
                    System.out.println("""

                        Select media type:
                        1. Anime
                        2. TV Series
                        3. Video Game
                        4. Music Single
                        5. Music Album
                        6. Go back
                        """);
                    int type = readValidInt();
                    // Nested switch to filter entries by the selected media type.
                    switch (type) {
                        case 1:
                            library.filterByType("Anime");
                            break;
                        case 2:
                            library.filterByType("TVSeries");
                            break;
                        case 3:
                            library.filterByType("VideoGame");
                            break;
                        case 4:
                            library.filterByType("MusicSingle");
                            break;
                        case 5:
                            library.filterByType("MusicAlbum");
                            break;
                        case 6:
                            break;
                        default:
                            System.out.println(type + " is an invalid choice.");
                    }
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println(choice + " is an invalid choice. Try again.");
            }
        }
    }
 
    /**
     * Lets the user manage individual episodes or tracks within a media entry.
     * 
     * Pre-condition: The user is logged in.
     * Post-condition: The episodes or tracks of the selected media entry are updated based on user input.
     */
    public static void manageEpisodesOrTracks() {
        Library library = currentUser.getLibrary();
        boolean running = true;

        // Outer loop picks the media type.
        while (running) {
            System.out.println("""

            Select media type:
            1. Anime
            2. TV Series
            3. Music Album
            4. Go back
            """);
            int type = readValidInt();
            switch (type) {
                case 1:
                    System.out.println("Enter Anime Title : ");
                    String animeTitle = readString();
                    Anime anime = library.getAnimeByTitle(animeTitle);
                    if (anime == null) {
                        System.out.println("Anime '" + animeTitle + "' not found.");
                        break;
                    }
                    // Inner loop manages episodes for the selected anime.
                    boolean animeRunning = true;
                    while (animeRunning) {
                        System.out.println("""

                            1. Display episodes
                            2. Update progress
                            3. Mark an episode watched
                            4. Rate an episode
                            5. Favorite/unfavorite an episode
                            6. Go back
                            """);
                        int animeChoice = readValidInt();
                        switch (animeChoice) {
                            case 1:
                                anime.displayEpisodes();
                                break;
                            case 2:
                                System.out.println("Enter episode number watched up to : ");
                                anime.updateProgress(readValidInt());
                                break;
                            case 3:
                                System.out.println("Enter episode number : ");
                                anime.markEpisodeWatched(readValidInt());
                                break;
                            case 4:
                                System.out.println("Enter episode number : ");
                                int animeEpNum = readValidInt();
                                System.out.println("Enter Rating (1-10) : ");
                                int animeEpRating = readValidInt();
                                anime.rateEpisode(animeEpNum, animeEpRating);
                                break;
                            case 5:
                                System.out.println("Enter episode number : ");
                                anime.favoriteEpisode(readValidInt());
                                break;
                            case 6:
                                animeRunning = false;
                                break;
                            default:
                                System.out.println(animeChoice + " is an invalid choice. Try again.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter TV Series Title : ");
                    String tvTitle = readString();
                    TVSeries tvSeries = library.getTVSeriesByTitle(tvTitle);
                    if (tvSeries == null) {
                        System.out.println("TV Series '" + tvTitle + "' not found.");
                        break;
                    }
                    // Inner loop manages episodes for the selected TV series.
                    boolean tvRunning = true;
                    while (tvRunning) {
                        System.out.println("""

                            1. Display episodes
                            2. Update progress
                            3. Mark an episode watched
                            4. Rate an episode
                            5. Favorite/unfavorite an episode
                            6. Go back
                            """);
                        int tvChoice = readValidInt();
                        switch (tvChoice) {
                            case 1:
                                tvSeries.displayEpisodes();
                                break;
                            case 2:
                                System.out.println("Enter episode number watched up to : ");
                                tvSeries.updateProgress(readValidInt());
                                break;
                            case 3:
                                System.out.println("Enter episode number : ");
                                tvSeries.markEpisodeWatched(readValidInt());
                                break;
                            case 4:
                                System.out.println("Enter episode number : ");
                                int tvEpNum = readValidInt();
                                System.out.println("Enter Rating (1-10) : ");
                                int tvEpRating = readValidInt();
                                tvSeries.rateEpisode(tvEpNum, tvEpRating);
                                break;
                            case 5:
                                System.out.println("Enter episode number : ");
                                tvSeries.favoriteEpisode(readValidInt());
                                break;
                            case 6:
                                tvRunning = false;
                                break;
                            default:
                                System.out.println(tvChoice + " is an invalid choice. Try again.");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Enter Album Title : ");
                    String maTitle = readString();
                    MusicAlbum musicAlbum = library.getMusicAlbumByTitle(maTitle);
                    if (musicAlbum == null) {
                        System.out.println("Music Album '" + maTitle + "' not found.");
                        break;
                    }
                    // Inner loop manages tracks for the selected album.
                    boolean albumRunning = true;
                    while (albumRunning) {
                        System.out.println("""
                            1. Display tracks
                            2. Update progress
                            3. Rate a track
                            4. Favorite/unfavorite a track
                            5. Go back
                            """);
                        int albumChoice = readValidInt();
                        switch (albumChoice) {
                            case 1:
                                musicAlbum.displayTracks();
                                break;
                            case 2:
                                System.out.println("Enter track number listened up to : ");
                                musicAlbum.updateProgress(readValidInt());
                                break;
                            case 3:
                                System.out.println("Enter track number : ");
                                int trackNum = readValidInt();
                                System.out.println("Enter Rating (1-10) : ");
                                int trackRating = readValidInt();
                                musicAlbum.rateTrack(trackNum, trackRating);
                                break;
                            case 4:
                                System.out.println("Enter track number : ");
                                musicAlbum.favoriteTrack(readValidInt());
                                break;
                            case 5:
                                albumRunning = false;
                                break;
                            default:
                                System.out.println(albumChoice + " is an invalid choice. Try again.");
                        }
                    }
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println(type + " is an invalid choice.");
            }
        }
        
    }
 
    /* Input reader helper methods. */

    /**
     * Prompts the user to enter an initial status and reads the input.
     * 
     * @return the initial status entered by the user
     * Pre-condition: None.
     * Post-condition: A valid integer representing the initial status is returned.
     */
    public static int readInitialStatus() {
        System.out.println("""
                        Enter initial status:
                        0 = Planned
                        1 = In Progress
                    """);
        return readValidInt();
    }
 
    /**
     * Prompts the user to enter a new status and reads the input.
     * 
     * @return the new status entered by the user
     * Pre-condition: None.
     * Post-condition: A valid integer representing the new status is returned.
     */
    public static int readNewStatus() {
        System.out.println("""
                        Enter status:
                        0 = Planned
                        1 = In Progress
                        2 = Completed
                    """);
        return readValidInt();
    }
 
    /**
     * Prompts the user to enter a rate or review choice and reads the input.
     * 
     * @return the rate or review choice entered by the user
     * Pre-condition: None.
     * Post-condition: A valid integer representing the rate or review choice is returned.
     */
    public static int readRateReviewChoice() {
        System.out.println("""
                        1 = Rate
                        2 = Review
                        3 = Both
                    """);
        return readValidInt();
    }
    
    /**
     * Reads a valid integer from the user input.
     * 
     * @return a valid integer read from the console
     * Pre-condition: None.
     * Post-condition: A valid integer is returned, and any invalid non-integer inputs are discarded.
     */
    public static int readValidInt() {
        // Keeps looping and discarding non-integer input until a valid int is found.
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.println("Enter a valid number.");
        }
        int input = sc.nextInt();
        sc.nextLine();
        return input;
    }

    /**
     * Reads a string from the user input.
     * 
     * @return the trimmed string read from the console
     * Pre-condition: None.
     * Post-condition: A trimmed string is returned.
     */
    public static String readString() {
        String input = "";
        input = sc.nextLine().trim();
        return input;
    }

    /**
     * Reads a yes or no answer from the user input.
     * 
     * @return true if the user enters "y" or "yes", false otherwise
     * Pre-condition: readString() works as intended.
     * Post-condition: A boolean representing the user's choice is returned.
     */
    public static boolean readYesOrNo() {
        String input = readString();
        return input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes");
    }
}
