/**
 * Maps integer status codes to readable status strings.
 */
public class StatusMapper {
    // index 0 = Planned, 1 = In Progress, 2 = Completed
    private static final String[] STATUSES = {"Planned", "In Progress", "Completed"};

    /**
     * Converts a status integer to its string label.
     * 
     * @param status the integer code for the status.
     * @return the string representation of the status, or "Invalid Status" if out of bounds.
     * Pre-condition: None.
     * Post-condition: Returns the corresponding status string.
     */
    public static String getStatusString(int status) {
        if (status < 0 || status >= STATUSES.length) {
            return "Invalid Status";
        }
        return STATUSES[status];
    }
}