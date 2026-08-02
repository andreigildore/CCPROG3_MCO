package model;

public class StatusMapper {
    public static String getStatusString(int status) {
        switch (status) {
            case 0: return "Planned";
            case 1: return "In Progress";
            case 2: return "Completed";
            default: return "Unknown";
        }
    }
}
