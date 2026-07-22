import java.util.ArrayList;

public abstract class EpisodicMedia extends MediaEntry{
    protected ArrayList<Segment> segments;
    protected int noOfSegments;

    public EpisodicMedia(String title, String genre, int initialStatus, int noOfSegments, String segmentType) {
        super(title, genre, initialStatus);
        segments = new ArrayList<>();
        for (int i = 1; i <= noOfSegments; i++) {
            segments.add(new Segment(i, segmentType));
        }
    }

    // returns number of newly updated entries in a specific episodic media
    public int updateProgress(int segmentInput) {
        if (segmentInput < 1 || segmentInput > segments.size())
            return -1;
        int newlyMarked = 0;
        for (Segment segment : segments) {
            if (segment.getSegmentNumber() <= segmentInput && !segment.isConsumed()) {
                segment.markConsumed();
                newlyMarked++;
            }
        }
        return newlyMarked;
    }

    public Segment findSegment(int segmentNumber) {
        for (Segment segment: segments) {
            if (segment.getSegmentNumber() == segmentNumber)
                return segment;
        }
        return null;
    }

    public boolean rateSegment(int segmentNumber, int rating) {
        boolean flag = false;
        for (Segment segment: segments) {
            if (segment.getSegmentNumber() == segmentNumber) {
                flag = segment.rate(rating);
            }
        }
        return flag;
    }

    public boolean markSegmentConsumed(int segmentNumber) {
        boolean flag = false;
        for (Segment segment : segments) {
            if (segment.getSegmentNumber() == segmentNumber) {
                segment.markConsumed();
                flag = true;
            }
        }
        return flag;
    }

    public boolean favoriteSegment(int segmentNumber) {
        boolean flag = false;
        for (Segment segment : segments) {
            if (segment.getSegmentNumber() == segmentNumber)
                flag = segment.toggleFavorite();
        }
        return flag;
    }

    public String displaySegments() {
        String output = "";
        
        for(Segment segment : segments) {
            segment.displayInfo();
        }

        return output;
    }
}
