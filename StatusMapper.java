public class StatusMapper {
    private static final String[] STATUSES = {"Planned", "In Progress", "Consumed"};

    public static String getStatus(int status) {
        if (status < 0 || status >= STATUSES.length) {
            return "Invalid Status";
        }
        return STATUSES[status];
    }
}