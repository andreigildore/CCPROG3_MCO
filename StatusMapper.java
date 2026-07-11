/**
 * maps integer status codes to readable status strings
 */
public class StatusMapper {
    // index 0 = Planned, 1 = In Progress, 2 = Completed
    private static final String[] STATUSES = {"Planned", "In Progress", "Completed"};

    /**
     * converts a status int to its string label
     */
    public static String getStatus(int status) {
        if (status < 0 || status >= STATUSES.length) {
            return "Invalid Status";
        }
        return STATUSES[status];
    }
}