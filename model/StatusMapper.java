package model;

/**
 * Maps integer status codes to their human-readable string representations.
 */
public class StatusMapper {
    /**
     * Returns the string representation of a status code.
     * 
     * @param status the status code (0 = Planned, 1 = In Progress, 2 = Completed)
     * @return the corresponding status string, or "Unknown" if the code is invalid
     */
    public static String getStatusString(int status) {
        switch (status) {
            case 0: return "Planned";
            case 1: return "In Progress";
            case 2: return "Completed";
            default: return "Unknown";
        }
    }
}
