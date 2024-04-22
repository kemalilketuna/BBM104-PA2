/**
 * The {@code ErrorMessages} class contains constants and methods to handle various error messages
 * used throughout the application. It mainly supports formatting specific error messages
 * related to voyage and seat management commands.
 */
public class ErrorMessages {
    public static final String ERROR = "ERROR";
    public static final String MINIBUS_TICKETS_NOT_REFUNDABLE = "ERROR: Minibus tickets are not refundable!";
    private static final String ERRORGOUS_USAGE = "ERROR: Erroneous usage of \"%s\" command!";
    public static final String INVALID_COMMAND = "ERROR: There is no command namely %s!";
    private static final String VOYAGEID_MUST_POSITIVE = "ERROR: %d is not a positive integer, ID of a voyage must be a positive integer!";
    private static final String VOYAGEID_ALREADY_USING = "ERROR: There is already a voyage with ID of %d!";
    private static final String NO_VOYAGE_WITH_ID = "ERROR: There is no voyage with ID of %d!";
    public static final String NO_SEAT = "ERROR: There is no such a seat!";
    private static final String SEATID_MUST_POSITIVE = "ERROR: %d is not a positive integer, seat number must be a positive integer!";
    public static final String SEAT_ALREADY_SOLD = "ERROR: One or more seats are already sold!";
    public static final String SEAT_ALREADY_EMPTY = "ERROR: One or more seats are already empty!";
    public static final String SEAT_ROW_MUST_POSITIVE = "ERROR: %d is not a positive integer, number of seat rows of a voyage must be a positive integer!";
    public static final String PRICE_MUST_POSITIVE = "ERROR: %d is not a positive number, price must be a positive number!";
    public static final String REFUND_CUT_MUST_BE_IN_RANGE = "ERROR: %d is not an integer that is in range of [0, 100], refund cut must be an integer that is in range of [0, 100]!";
    public static final String PREMIUM_FEE_MUST_NON_NEGATIVE = "ERROR: %d is not a non-negative integer, premium fee must be a non-negative integer!";
    //ERROR: -1 is not a positive integer, seat number must be a positive integer!
    public static final String INVALID_SEAT_NUMBER = "ERROR: %d is not a positive integer, seat number must be a positive integer!";
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

    /**
     * Returns an error message that the number of seat rows must be a positive integer.
     *
     * @param row the number of seat rows to check
     * @return formatted error string indicating the number of seat rows is not positive
     */
    public static String getSeatRowMustPositiveString(int row) {
        return String.format(SEAT_ROW_MUST_POSITIVE, row);
    }

    /**
     * Returns an error message that the price must be a positive number.
     *
     * @param price the price to check
     * @return formatted error string indicating the price is not positive
     */
    public static String getPriceMustPositiveString(float price) {
        int priceInt = (int) price;
        return String.format(PRICE_MUST_POSITIVE, priceInt);
    }

    /**
     * Returns an error message that the refund cut must be an integer in range of [0, 100].
     *
     * @param cut the refund cut to check
     * @return formatted error string indicating the refund cut is not in range of [0, 100]
     */
    public static String getRefundCutMustInRangeString(int cut) {
        return String.format(REFUND_CUT_MUST_BE_IN_RANGE, cut);
    }

    /**
     * Returns an error message that the premium fee must be a non-negative integer.
     *
     * @param fee the premium fee to check
     * @return formatted error string indicating the premium fee is not non-negative
     */
    public static String getPremiumFeeMustNonNegativeString(int fee) {
        return String.format(PREMIUM_FEE_MUST_NON_NEGATIVE, fee);
    }

    /**
     * Returns an error message that the seat number must be a positive integer.
     *
     * @param number the seat number to check
     * @return formatted error string indicating the seat number is not positive
     */
    public static String getInvalidSeatNumberString(int number) {
        return String.format(INVALID_SEAT_NUMBER, number + 1);
    }
}
