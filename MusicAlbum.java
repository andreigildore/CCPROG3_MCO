import java.util.ArrayList;

public class MusicAlbum {
    private String title;
    private String genre;
    private int status;
    private int rating;
    private String review;
    private String artist;
    private String recordLabel;
    private ArrayList<Track> tracks;
    private int noOfTracks;

    public MusicAlbum(String title, int noOfTracks, String genre, String artist, String recordLabel) {
        this.title = title;
        this.genre = genre;
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.status = 0;
        this.tracks = new ArrayList<>();
        for (int i = 1; i <= noOfTracks; i++) {
            Track track = new Track(i);
            tracks.add(track);
        }
        this.noOfTracks = tracks.size();
    }

    public String getTitle() {
        return this.title;
    }

    public int getStatus() {
        return this.status;
    }

    public int getRating() {
        return this.rating;
    }

    public String getReview() {
        return this.review;
    }

    public void updateStatus(int newStatus) {
        if(newStatus >= 0 && newStatus <= 2)
            status = newStatus;
        else
            System.out.println("Invalid status.");
    }

    public void updateProgress(int trackInput) {
        for (Track track : tracks) {
            if (track.getTrackNumber() <= trackInput)
                track.markListened();
        }
    }

    public Track findTrack(int trackNumber) {
        for (Track track : tracks) {
            if (track.getTrackNumber() == trackNumber)
                return track;
        }
        return null;
    }

    public void rateTrack(int trackNumber, int rating) {
        Track track = findTrack(trackNumber);
        if (track != null) {
            track.rate(rating);
        }
        else {
            System.out.println("Track " + trackNumber + " not found in " + title + ".");
        }
    }

    public void favoriteTrack(int trackNumber) {
        Track track = findTrack(trackNumber);
        if (track != null) {
            track.toggleFavorite();
        }
        else {
            System.out.println("Track " + trackNumber + " not found in " + title + ".");
        }
    }

    public void rate(int rating) {
        if (rating >= 1 && rating  <= 10) {
            if (this.status == 2) {
                this.rating = rating;
                System.out.println("Rating saved successfully.");
            } else {
                System.out.println("Cannot rate an unfinished album.");
            }
        }
        else
            System.out.println("Invalid rating score");
    }

    public void review(String text) {
        if (text != null && !text.isBlank()){
            if (this.status == 2) {
                this.review = text;
                System.out.println("Review saved successfully.");
            } else {
                System.out.println("Cannot review an unfinished album.");
            }
        }
        else
            System.out.println("Review has no content or is null");
    }

    public String displayInfo() {
        String ratingStr = (rating > 0) ? rating + "/10" : "NotRated";
        String reviewStr = (review != null && !review.isBlank()) ? review : "No review";
        return String.format("""
        Title  : %s
        Artist : %s
        Record Label : %s
        Genre: %s
        No. Of Tracks : %d
        Status : %s
        Rating : %s
        Review : %s
        """,
        title,
        artist,
        recordLabel,
        genre,
        noOfTracks,
        StatusMapper.getStatus(status),
        ratingStr,
        reviewStr
        );
    }
    
}
