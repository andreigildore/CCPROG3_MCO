import java.util.Scanner;

public class MainDriver {
    private static final Scanner sc = new Scanner(System.in);
    private static final User currentUser = new User(); // assume only one user for the program

    public static void main(String args[]) {
        System.out.println("""
            
            Welcome to the Media Vault!
            """);
        System.out.println("Use sample user details? (y/n)");
        if (readYesOrNo())
            setupUser();   
        boolean programRuns = true;

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
    
    private static void setupUser() {
        currentUser.register("jdjdsbgad", "jdjdsbgad64@email.com", "jdjdsbgad!@#1234");
        currentUser.login("jdjdsbgad", "jdjdsbgad!@#1234");
    }

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

    /* menu methods */
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
            switch (type) { // block inside asks the user's input based on the type, then adds the entry
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
                if (animeChoice == 1 || animeChoice == 3) {
                    System.out.println("Enter Rating (1-10) : ");
                    anime.rate(readValidInt());
                }
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
 
    public static void manageEpisodesOrTracks() {
        Library library = currentUser.getLibrary();
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
                boolean albumRunning = true;
                while (albumRunning) {
                    System.out.println("""
                        1. Update progress
                        2. Rate a track
                        3. Favorite/unfavorite a track
                        4. Go back
                        """);
                    int albumChoice = readValidInt();
                    switch (albumChoice) {
                        case 1:
                            System.out.println("Enter track number listened up to : ");
                            musicAlbum.updateProgress(readValidInt());
                            break;
                        case 2:
                            System.out.println("Enter track number : ");
                            int trackNum = readValidInt();
                            System.out.println("Enter Rating (1-10) : ");
                            int trackRating = readValidInt();
                            musicAlbum.rateTrack(trackNum, trackRating);
                            break;
                        case 3:
                            System.out.println("Enter track number : ");
                            musicAlbum.favoriteTrack(readValidInt());
                            break;
                        case 4:
                            albumRunning = false;
                            break;
                        default:
                            System.out.println(albumChoice + " is an invalid choice. Try again.");
                    }
                }
                break;
            case 4:
                break;
            default:
                System.out.println(type + " is an invalid choice.");
        }
    }
 
    /* input reader helper methods */
    public static int readInitialStatus() {
        System.out.println("""
                        Enter initial status:
                        0 = Planned
                        1 = In Progress
                    """);
        return readValidInt();
    }
 
    public static int readNewStatus() {
        System.out.println("""
                        Enter status:
                        0 = Planned
                        1 = In Progress
                        2 = Completed
                    """);
        return readValidInt();
    }
 
    public static int readRateReviewChoice() {
        System.out.println("""
                        1 = Rate
                        2 = Review
                        3 = Both
                    """);
        return readValidInt();
    }
    
    public static int readValidInt() {
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.println("Enter a valid number.");
        }
        int input = sc.nextInt();
        sc.nextLine();
        return input;
    }

    public static String readString() {
        String input = "";
        input = sc.nextLine().trim();
        return input;
    }

    public static boolean readYesOrNo() {
        return readString().equalsIgnoreCase("y") || readString().equalsIgnoreCase("yes");
    }
}
