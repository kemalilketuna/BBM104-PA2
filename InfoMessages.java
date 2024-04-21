public class InfoMessages {
    private static final String VOYAGE_SUCCESFULLY_CANCELLED = "Voyage %d was successfully cancelled!";
    public static final String VOYAGE_DETAILS = "Voyage details can be found below:";
    private static final String REVENUE_MESSAGE = "Revenue: %.2f";
    private static final String VOYAGE_MESSAGE = "Voyage %d";
    private static final String VOYAGE_ROUTE = "%s-%s";
    private static final String COMMAND_MESSAGE = "Command: %s";
    private static final String STANDARD_VOYAGE_INITIALIZED = "Voyage %d was initialized standard (2+2) voyage from %s to %s with %.2f TL priced %d regular seats. Note that refunds will be %d%% less than the paid amount.";
    private static final String PREMIUM_VOYAGE_INITIALIZED = "Voyage %d was initialized premium (1+2) voyage from %s to %s with %.2f TL priced %d regular seats and %.2f TL priced %d premium seats. Note that refunds will be %d%% less than the paid amount.";
    private static final String MINIBUS_VOYAGE_INITIALIZED = "Voyage %d was initialized as a minibus (2) voyage from %s to %s with %.2f TL priced %d regular seats. Note that minibus tickets are not refundable.";
    //Seat 1-2-3-4 of the Voyage 10 from Inheritance to Polymorphism was successfully sold for 4600.00 TL.
    private static final String SEATS_SOLD = "Seat %s of the %d from %s to %s was successfully sold for %.2f TL.";
    //Seat 1-3-5-7-9-11-13-15 of the Voyage 7 from Ankara to Sinop was successfully refunded for 5592.17 TL.
    private static final String SEATS_REFUNDED = "Seat %s of the %d from %s to %s was successfully refunded for %.2f TL.";

    public static String getVoyageSuccesfullyCancelledString(int id) {
        return String.format(VOYAGE_SUCCESFULLY_CANCELLED, id);
    }

    public static String getRevenueMessageString(float revenue) {
        return String.format(REVENUE_MESSAGE, revenue);
    }

    public static String getVoyageString(int id) {
        return String.format(VOYAGE_MESSAGE, id);
    }

    public static String getVoyageRouteString(String departure, String arrival) {
        return String.format(VOYAGE_ROUTE, departure, arrival);
    }

    public static String getCommandMessageString(String command) {
        return String.format(COMMAND_MESSAGE, command);
    }

    public static String getStandardVoyageInitializedString(int id, String departure, String arrival, float price, int regularSeats, int refundCut) {
        return String.format(STANDARD_VOYAGE_INITIALIZED, id, departure, arrival, price, regularSeats, refundCut);
    }

    public static String getPremiumVoyageInitializedString(int id, String departure, String arrival, float regularPrice, int regularSeats, float premiumPrice, int premiumSeats, int refundCut) {
        return String.format(PREMIUM_VOYAGE_INITIALIZED, id, departure, arrival, regularPrice, regularSeats, premiumPrice, premiumSeats, refundCut);
    }

    public static String getMinibusVoyageInitializedString(int id, String departure, String arrival, float price, int regularSeats) {
        return String.format(MINIBUS_VOYAGE_INITIALIZED, id, departure, arrival, price, regularSeats);
    }

    public static String getSeatsSoldString(int[] seats, int voyage, String departure, String arrival, float price) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seats.length; i++) {
            sb.append(seats[i]);
            if (i != seats.length - 1) {
                sb.append("-");
            }
        }
        return String.format(SEATS_SOLD, sb.toString(), voyage, departure, arrival, price);
    }

    public static String getSeatsRefundedString(int[] seats, int voyage, String departure, String arrival, float price) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seats.length; i++) {
            sb.append(seats[i]);
            if (i != seats.length - 1) {
                sb.append("-");
            }
        }
        return String.format(SEATS_REFUNDED, sb.toString(), voyage, departure, arrival, price);
    }
}
