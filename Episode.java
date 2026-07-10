public class Episode {
    private String title;
    private int episodeNumber;

    public Episode(String title, int episodeNumber) {
        this.title = title;
        this.episodeNumber = episodeNumber;
    }

    public String getTitle() { 
		return title; 
	}
    public void setTitle(String title) { 
		this.title = title; 
	}

    public int getEpisodeNumber() { 
		return episodeNumber; 
	}
    public void setEpisodeNumber(int episodeNumber) { 
		this.episodeNumber = episodeNumber; 
	}
}
