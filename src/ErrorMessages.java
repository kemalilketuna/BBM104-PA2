/**
 * The {@code ErrorMessages} class contains constants and methods to handle various error messages
 * used throughout the application. It mainly supports formatting specific error messages
 * related to voyage and seat management commands.
 */
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

    /**
     * Returns an error message that the voyage ID must be a positive integer.
     *
     * @param id the voyage ID to check
     * @return formatted error string indicating the voyage ID is not positive
     */
    public static String getVoyageIdMustPositiveString(int id) {
        return String.format(VOYAGEID_MUST_POSITIVE, id);
    }

    /**
     * Returns an error message that the voyage ID is already in use.
     *
     * @param id the voyage ID that is already in use
     * @return formatted error string indicating the voyage ID is already used
     */
    public static String getVoyageIdAlreadyUsingString(int id) {
        return String.format(VOYAGEID_ALREADY_USING, id);
    }

    /**
     * Returns an error message indicating there is no voyage with the given ID.
     *
     * @param id the ID of the voyage that does not exist
     * @return formatted error string indicating no voyage is found with the ID
     */
    public static String getNoVoyageWithIdString(int id) {
        return String.format(NO_VOYAGE_WITH_ID, id);
    }

    /**
     * Returns an error message that the seat ID must be a positive integer.
     *
     * @param id the seat ID to check
     * @return formatted error string indicating the seat ID is not positive
     */
    public static String getSeatIdMustPositiveString(int id) {
        return String.format(SEATID_MUST_POSITIVE, id + 1);
    }

    /**
     * Returns an error message for erroneous usage of a specified command.
     *
     * @param command the command that was used incorrectly
     * @return formatted error string indicating incorrect usage of the command
     */
    public static String getErrorgousUsageString(String command) {
        return String.format(ERRORGOUS_USAGE, command);
    }

    /**
     * Returns an error message stating that the specified command does not exist.
     *
     * @param command the command that is invalid
     * @return formatted error string indicating the command does not exist
     */
    public static String getThereIsNoCommandString(String command){
        return String.format(INVALID_COMMAND, command);
    }
}
