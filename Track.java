public class Track {
    private String title;
    private int trackNumber;

    public Track(String title, int trackNumber) {
        this.title = title;
        this.trackNumber = trackNumber;
    }

    public String getTitle() { 
		return title; 
	}
    public void setTitle(String title) { 
		this.title = title; 
	}

    public int getTrackNumber() { 
		return trackNumber;	
	}
    public void setTrackNumber(int trackNumber) { 
		this.trackNumber = trackNumber; 
	}
}
