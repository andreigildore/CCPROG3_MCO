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

    /**
     * Marks all segments up to the segment number as watched.
     * 
     * @param segmentInput the segment number up to which to mark as watched
     * @return newlyMarked which is the number of segments that was marked as watched
     * Pre-condition: segmentInput is a valid segment number.
     * Post-condition: Segments from 1 to segmentInput are marked as consumed.
     */
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

    /**
     * Finds and returns an segment by its number, or null if not found.
     * 
     * @param segmentNumber the number of the segment to find
     * @return the Segment object if found, or null if not found
     * Pre-condition: segmentNumber is a valid integer.
     * Post-condition: The corresponding Segment object is returned, or null if it does not exist.
     */
    public Segment findSegment(int segmentNumber) {
        for (Segment segment: segments) {
            if (segment.getSegmentNumber() == segmentNumber)
                return segment;
        }
        return null;
    }

    /**
     * Rates a specific segment by its number.
     * 
     * @param segmentNumber the number of the segment to rate
     * @param rating the rating to assign to the segment
     * @return false if the segment was not found and true if found
     * Pre-condition: segmentNumber corresponds to an existing segment.
     * Post-condition: The specified segment is rated if it exists.
     */
    public boolean rateSegment(int segmentNumber, int rating) {
        boolean flag = false;
        for (Segment segment: segments) {
            if (segment.getSegmentNumber() == segmentNumber) {
                flag = segment.rate(rating);
            }
        }
        return flag;
    }

    /**
     * Marks a specific segment as consumed.
     * 
     * @param segmentNumber the number of the segment to mark as consumed
     * Pre-condition: segmentNumber corresponds to an existing segment.
     * Post-condition: The specified segment is marked as consumed if it exists.
     */
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

    /**
     * Toggles the favorite status of a specific segment.
     * 
     * @param segmentNumber the number of the segment to favorite or unfavorite
     * Pre-condition: segmentNumber corresponds to an existing segment.
     * Post-condition: The favorite status of the specified segment is toggled.
     */
    public boolean favoriteSegment(int segmentNumber) {
        boolean flag = false;
        for (Segment segment : segments) {
            if (segment.getSegmentNumber() == segmentNumber)
                flag = segment.toggleFavorite();
        }
        return flag;
    }

    /** WIP!
     * Prints all segments and their details.
     * 
     * Pre-condition: the media entry has a list of segments.
     * Post-condition: The details of all segments are printed to the console.
     */
    public String displaySegments() {
        String output = "";
        
        for(Segment segment : segments) {
            segment.displayInfo();
        }

        return output;
    }
}
