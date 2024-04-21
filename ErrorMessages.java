public class ErrorMessages {
    public static final String ERROR = "ERROR";
    public static final String MINIBUS_TICKETS_NOT_REFUNDABLE = "ERROR: Minibus tickets are not refundable!";
    private static final String ERRORGOUS_USAGE = "Erroneous usage of \"%s\" command!";
    public static final String INVALID_COMMAND = "ERROR: There is no command namely %s!";
    private static final String VOYAGEID_MUST_POSITIVE = "ERROR: %d is not a positive integer, ID of a voyage must be a positive integer!";
    private static final String VOYAGEID_ALREADY_USING = "ERROR: There is already a voyage with ID of %d!";
    private static final String NO_VOYAGE_WITH_ID = "ERROR: There is no voyage with ID of %d!";
    public static final String NO_SEAT = "ERROR: There is no such a seat!";
    private static final String SEATID_MUST_POSITIVE = "ERROR: %d is not a positive integer, seat number must be a positive integer!";
    public static final String SEAT_ALREADY_SOLD = "ERROR: One or more seats already sold!"; 
    public static final String SEAT_ALREADY_EMPTY = "ERROR: One or more seats already empty!";

    public static String getVoyageIdMustPositiveString(int id) {
        return String.format(VOYAGEID_MUST_POSITIVE, id);
    }

    public static String getVoyageIdAlreadyUsingString(int id) {
        return String.format(VOYAGEID_ALREADY_USING, id);
    }

    public static String getNoVoyageWithIdString(int id) {
        return String.format(NO_VOYAGE_WITH_ID, id);
    }

    public static String getSeatIdMustPositiveString(int id) {
        return String.format(SEATID_MUST_POSITIVE, id);
    }

    public static String getErrorgousUsageString(String command) {
        return String.format(ERRORGOUS_USAGE, command);
    }

    public static String getThereIsNoCommandString(String command){
        return String.format(INVALID_COMMAND, command);
    }
}
