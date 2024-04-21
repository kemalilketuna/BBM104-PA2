/**
 * The {@code InfoMessages} class provides static utility methods to create formatted strings for various informational
 * messages related to voyages. These messages cover a range of functions from voyage initialization, seat sales,
 * to revenue reporting.
 */
public class InfoMessages {
    private static final String VOYAGE_SUCCESFULLY_CANCELLED = "Voyage %d was successfully cancelled!";
    public static final String VOYAGE_DETAILS = "Voyage details can be found below:";
    private static final String REVENUE_MESSAGE = "Revenue: %.2f";
    private static final String VOYAGE_MESSAGE = "Voyage %d";
    private static final String VOYAGE_ROUTE = "%s-%s";
    private static final String COMMAND_MESSAGE = "COMMAND: %s";
    private static final String STANDARD_VOYAGE_INITIALIZED = "Voyage %d was initialized as a standard (2+2) voyage from %s to %s with %.2f TL priced %d regular seats. Note that refunds will be %d%% less than the paid amount.";
    private static final String PREMIUM_VOYAGE_INITIALIZED = "Voyage %d was initialized as a premium (1+2) voyage from %s to %s with %.2f TL priced %d regular seats and %.2f TL priced %d premium seats. Note that refunds will be %d%% less than the paid amount.";
    private static final String MINIBUS_VOYAGE_INITIALIZED = "Voyage %d was initialized as a minibus (2) voyage from %s to %s with %.2f TL priced %d regular seats. Note that minibus tickets are not refundable.";
    private static final String SEATS_SOLD = "Seat %s of the Voyage %d from %s to %s was successfully sold for %.2f TL.";
    private static final String SEATS_REFUNDED = "Seat %s of the Voyage %d from %s to %s was successfully refunded for %.2f TL.";

    /**
     * Generates a message indicating successful cancellation of a voyage.
     *
     * @param id The voyage ID that was cancelled.
     * @return A formatted message indicating the voyage was successfully cancelled.
     */
    public static String getVoyageSuccesfullyCancelledString(int id) {
        return String.format(VOYAGE_SUCCESFULLY_CANCELLED, id);
    }

    /**
     * Generates a revenue message.
     *
     * @param revenue The amount of revenue.
     * @return A formatted message displaying the revenue.
     */
    public static String getRevenueMessageString(float revenue) {
        return String.format(REVENUE_MESSAGE, revenue);
    }

    /**
     * Generates a message displaying a voyage ID.
     *
     * @param id The voyage ID.
     * @return A formatted message showing the voyage ID.
     */
    public static String getVoyageString(int id) {
        return String.format(VOYAGE_MESSAGE, id);
    }

    /**
     * Generates a route message for a voyage.
     *
     * @param departure The departure location.
     * @param arrival The arrival location.
     * @return A formatted message showing the voyage route.
     */
    public static String getVoyageRouteString(String departure, String arrival) {
        return String.format(VOYAGE_ROUTE, departure, arrival);
    }

    /**
     * Generates a command message.
     *
     * @param command The command executed.
     * @return A formatted message indicating the command used.
     */
    public static String getCommandMessageString(String command) {
        return String.format(COMMAND_MESSAGE, command);
    }

    /**
     * Generates a message indicating that a standard voyage was initialized.
     *
     * @param id The voyage ID.
     * @param departure The departure location.
     * @param arrival The arrival location.
     * @param price The price per seat.
     * @param row_count The number of rows of seats.
     * @param refundCut The percentage deduction on refunds.
     * @return A formatted message detailing the voyage initialization.
     */
    public static String getStandardVoyageInitializedString(int id, String departure, String arrival, float price, int row_count, int refundCut) {
        return String.format(STANDARD_VOYAGE_INITIALIZED, id, departure, arrival, price, row_count * 4, refundCut);
    }

    /**
     * Generates a message indicating that a premium voyage was initialized.
     *
     * @param id The voyage ID.
     * @param departure The departure location.
     * @param arrival The arrival location.
     * @param regularPrice The price per regular seat.
     * @param row_count The number of rows of seats.
     * @param premiumPrice The price per premium seat.
     * @param refundCut The percentage deduction on refunds.
     * @return A formatted message detailing the voyage initialization.
     */
    public static String getPremiumVoyageInitializedString(int id, String departure, String arrival, float regularPrice, int row_count, float premiumPrice, int refundCut) {
        return String.format(PREMIUM_VOYAGE_INITIALIZED, id, departure, arrival, regularPrice, row_count * 2, premiumPrice, row_count, refundCut);
    }

    /**
     * Generates a message indicating that a minibus voyage was initialized.
     *
     * @param id The voyage ID.
     * @param departure The departure location.
     * @param arrival The arrival location.
     * @param price The price per seat.
     * @param row_count The number of rows of seats.
     * @return A formatted message detailing the voyage initialization.
     */
    public static String getMinibusVoyageInitializedString(int id, String departure, String arrival, float price, int row_count) {
        return String.format(MINIBUS_VOYAGE_INITIALIZED, id, departure, arrival, price, row_count * 2);
    }

    /**
     * Generates a message indicating that seats were sold.
     *
     * @param seats An array of seat numbers.
     * @param voyage The voyage ID.
     * @param departure The departure location.
     * @param arrival The arrival location.
     * @param price The price at which the seats were sold.
     * @return A formatted message detailing the seats sold.
     */
    public static String getSeatsSoldString(int[] seats, int voyage, String departure, String arrival, float price) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seats.length; i++) {
            sb.append(seats[i] + 1);
            if (i != seats.length - 1) {
                sb.append("-");
            }
        }
        return String.format(SEATS_SOLD, sb.toString(), voyage, departure, arrival, price);
    }

    /**
     * Generates a message indicating that seats were refunded.
     *
     * @param seats An array of seat numbers.
     * @param voyage The voyage ID.
     * @param departure The departure location.
     * @param arrival The arrival location.
     * @param price The refund amount for the seats.
     * @return A formatted message detailing the seats refunded.
     */
    public static String getSeatsRefundedString(int[] seats, int voyage, String departure, String arrival, float price) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seats.length; i++) {
            sb.append(seats[i] + 1);
            if (i != seats.length - 1) {
                sb.append("-");
            }
        }
        return String.format(SEATS_REFUNDED, sb.toString(), voyage, departure, arrival, price);
    }
}
