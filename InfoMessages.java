public class InfoMessages {
    private static final String VOYAGE_SUCCESFULLY_CANCELLED = "Voyage %d was successfully cancelled!";
    public static final String VOYAGE_DETAILS = "Voyage details can be found below:";
    private static final String REVENUE_MESSAGE = "Revenue: %.2f";
    private static final String VOYAGE_MESSAGE = "Voyage %d";
    private static final String VOYAGE_ROUTE = "%s-%s";
    private static final String COMMAND_MESSAGE = "Command: %s";

    public static String getVoyageSuccesfullyCancelled(int id) {
        return String.format(VOYAGE_SUCCESFULLY_CANCELLED, id);
    }

    public static String getRevenueMessage(float revenue) {
        return String.format(REVENUE_MESSAGE, revenue);
    }

    public static String getVoyage(int id) {
        return String.format(VOYAGE_MESSAGE, id);
    }

    public static String getVoyageRoute(String departure, String arrival) {
        return String.format(VOYAGE_ROUTE, departure, arrival);
    }

    public static String getCommandMessage(String command) {
        return String.format(COMMAND_MESSAGE, command);
    }
}
