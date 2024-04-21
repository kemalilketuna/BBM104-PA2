import java.util.*;

/**
 * Represents a voyage with a specific route, seat layout, and pricing.
 * This class manages the details of a voyage including its identification,
 * seat arrangements, ticketing, and revenue calculations.
 */
class Voyage {
    private int id;
    private int row_count;
    private String departure;
    private String arrival;
    private int column_count;
    private float seat_price;
    private boolean[][] seats;
    private float revenue;

    /**
     * Constructs a new Voyage with the specified details and initializes the seat matrix.
     *
     * @param id Unique identifier for the voyage.
     * @param row_count Number of rows of seats.
     * @param departure Starting point of the voyage.
     * @param arrival Destination point of the voyage.
     * @param column_count Number of columns of seats.
     * @param seat_price Price of a single seat.
     */
    public Voyage(int id, int row_count, String departure, String arrival, int column_count, float seat_price){
        this.id = id;
        this.row_count = row_count;
        this.departure = departure;
        this.arrival = arrival;
        this.column_count = column_count;
        this.seat_price = seat_price;
        this.seats = new boolean[row_count][column_count];
    }

    public int getId() { return this.id; }
    public int getRowCount() { return this.row_count; }
    public String getDeparture() { return this.departure; }
    public String getArrival() { return this.arrival; }
    public int getColumnCount() { return this.column_count; }
    public float getSeatPrice() { return this.seat_price; }
    public boolean[][] getSeats() { return this.seats; }
    public float getRevenue() { return this.revenue; }

    /**
     * Increases the total revenue by the specified amount.
     * @param amount Amount to increase the revenue by.
     */
    public void increaseRevenue(float amount) {
        this.revenue += amount;
    }

    /**
     * Processes a seat ticket sale by changing the seat status and increasing the revenue.
     * @param seatNumber Linear index of the seat to sell.
     */
    public void sellTicket(int seatNumber) {
        changeSeatStatus(seatNumber);
        increaseRevenue(this.getSeatPrice());
    }

    /**
     * Changes the status of a seat from occupied to unoccupied or vice versa.
     * @param seatNumber Linear index of the seat to change the status of.
     */
    protected void changeSeatStatus(int seatNumber) {
        int row = seatNumber / this.column_count;
        int column = seatNumber % this.column_count;
        this.seats[row][column] = !this.seats[row][column];
    }

    /**
     * Checks if the given seat number is valid within the current layout.
     * @param seatNumber Linear index of the seat to check.
     * @return true if the seat number is valid, false otherwise.
     */
    public boolean isSeatNumberValid(int seatNumber) {
        int row = seatNumber / this.column_count;
        int column = seatNumber % this.column_count;
        return row >= 0 && row < this.row_count && column >= 0 && column < this.column_count;
    }

    /**
     * Checks if a specific seat is empty.
     * @param seatNumber Linear index of the seat to check.
     * @return true if the seat is empty, false if it is occupied.
     */
    public boolean isSeatEmpty(int seatNumber) {
        int row = seatNumber / this.column_count;
        int column = seatNumber % this.column_count;
        return !this.seats[row][column];
    }

    /**
     * Prints the layout of the voyage's seats.
     */
    public void printLayout() {
        System.out.println(InfoMessages.getVoyageString(this.id));
        System.out.println(InfoMessages.getVoyageRouteString(this.departure, this.arrival));
        for (int i = 0; i < this.row_count; i++) {
            for (int j = 0; j < this.column_count; j++) {
                System.out.print(this.seats[i][j] ? "X" : "*");
                if (column_count == 3 && j == 0) System.out.print(" |");
                if (column_count == 4 && j == 1) System.out.print(" |");
                if (j != column_count - 1) System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println(InfoMessages.getRevenueMessageString(this.revenue));
    }

    /**
     * Processes the cancellation of the voyage and resets revenue to zero.
     */
    public void processVoyageCancelling() {
        System.out.println(InfoMessages.getVoyageSuccesfullyCancelledString(this.id));
        System.out.println(InfoMessages.VOYAGE_DETAILS);
        System.out.println(InfoMessages.getVoyageString(this.id));
        System.out.println(InfoMessages.getVoyageRouteString(this.departure, this.arrival));
      
        for (int i = 0; i < this.row_count; i++) {
            for (int j = 0; j < this.column_count; j++) {
                System.out.print(this.seats[i][j] ? "X" : "*");
                if (column_count == 3 && j == 0) System.out.print(" |");
                if (column_count == 4 && j == 1) System.out.print(" |");
                if (j != column_count - 1) System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println(InfoMessages.getRevenueMessageString(0.0f));
    }
}



/**
 * Represents a standard bus voyage, extending the Voyage class.
 * It has additional properties and methods specific to standard bus voyages.
 */
class StandardBus extends Voyage {
    private float refundAmount;

    /**
     * Constructs a StandardBus object with the provided parameters.
     *
     * @param id         The ID of the voyage.
     * @param row_count  The number of rows in the bus.
     * @param departure  The departure location.
     * @param arrival    The arrival location.
     * @param seat_price The price of each seat.
     * @param refundCut  The percentage cut for refunds.
     */
    public StandardBus(int id, int row_count, String departure, String arrival, float seat_price, int refundCut) {
        super(id, row_count, departure, arrival, 4, seat_price);
        refundAmount = ((100.f - (float) refundCut) / 100.f) * seat_price;
        // Round to 2 decimal places
        refundAmount = (float) Math.round(refundAmount * 100) / 100;
    }

    /**
     * Returns the refund amount for this standard bus voyage.
     *
     * @return The refund amount.
     */
    public float getRefundAmount() {
        return this.refundAmount;
    }

    /**
     * Refunds a ticket for the specified seat number.
     * Changes the seat status and decreases the revenue by the refund amount.
     *
     * @param seatNumber The seat number for which to refund the ticket.
     */
    public void refundTicket(int seatNumber) {
        changeSeatStatus(seatNumber);
        increaseRevenue(-this.getRefundAmount());
    }
}


/**
 * Represents a premium bus voyage, extending the Voyage class.
 * It has additional properties and methods specific to premium bus voyages.
 */
class PremiumBus extends Voyage {
    private float refundAmount;
    private float premiumPrice;
    private float premiumRefundAmount;

    /**
     * Constructs a PremiumBus object with the provided parameters.
     *
     * @param id         The ID of the voyage.
     * @param row_count  The number of rows in the bus.
     * @param departure  The departure location.
     * @param arrival    The arrival location.
     * @param seat_price The price of each seat.
     * @param refundCut  The percentage cut for refunds.
     * @param premiumFee The additional fee for premium seats.
     */
    public PremiumBus(int id, int row_count, String departure, String arrival, float seat_price, int refundCut, int premiumFee) {
        super(id, row_count, departure, arrival, 3, seat_price);
        refundAmount = ((100.f - (float) refundCut) / 100.f) * seat_price;
        premiumPrice = ((100.f + (float) premiumFee) / 100.f) * seat_price;
        premiumRefundAmount = ((100.f - (float) refundCut) / 100.f) * premiumPrice;
        // Round to 2 decimal places
        refundAmount = (float) Math.round(refundAmount * 100) / 100;
        premiumPrice = (float) Math.round(premiumPrice * 100) / 100;
        premiumRefundAmount = (float) Math.round(premiumRefundAmount * 100) / 100;
    }

    /**
     * Returns the refund amount for regular seats.
     *
     * @return The refund amount for regular seats.
     */
    public float getRefundAmount() {
        return this.refundAmount;
    }

    /**
     * Returns the price of premium seats.
     *
     * @return The price of premium seats.
     */
    public float getPremiumPrice() {
        return this.premiumPrice;
    }

    /**
     * Returns the refund amount for premium seats.
     *
     * @return The refund amount for premium seats.
     */
    public float getPremiumRefundAmount() {
        return this.premiumRefundAmount;
    }

    /**
     * Checks if the given seat number is a premium seat.
     *
     * @param seatNumber The seat number to check.
     * @return true if the seat is a premium seat, false otherwise.
     */
    private boolean isPremiumSeat(int seatNumber) {
        return seatNumber % this.getColumnCount() == 0;
    }

    /**
     * Sells a ticket for the specified seat number.
     * If the seat is a premium seat, the premium price is charged.
     * Otherwise, the regular seat price is charged.
     *
     * @param seatNumber The seat number for which to sell the ticket.
     */
    public void sellTicket(int seatNumber) {
        if (isPremiumSeat(seatNumber)) {
            changeSeatStatus(seatNumber);
            increaseRevenue(this.getPremiumPrice());
        } else {
            changeSeatStatus(seatNumber);
            increaseRevenue(this.getSeatPrice());
        }
    }

    /**
     * Refunds a ticket for the specified seat number.
     * If the seat is a premium seat, the premium refund amount is deducted.
     * Otherwise, the regular refund amount is deducted.
     *
     * @param seatNumber The seat number for which to refund the ticket.
     */
    public void refundTicket(int seatNumber) {
        if (isPremiumSeat(seatNumber)) {
            changeSeatStatus(seatNumber);
            increaseRevenue(-this.getPremiumRefundAmount());
        } else {
            changeSeatStatus(seatNumber);
            increaseRevenue(-this.getRefundAmount());
        }
    }
}


/**
 * Represents a mini bus voyage, extending the Voyage class.
 * It has no additional properties or methods specific to mini bus voyages.
 */
class MiniBus extends Voyage {
    /**
     * Constructs a MiniBus object with the provided parameters.
     *
     * @param id         The ID of the voyage.
     * @param row_count  The number of rows in the mini bus.
     * @param departure  The departure location.
     * @param arrival    The arrival location.
     * @param seat_price The price of each seat.
     */
    public MiniBus(int id, int row_count, String departure, String arrival, float seat_price) {
        super(id, row_count, departure, arrival, 2, seat_price);
    }
}


public class VoyageManager{
    private Map<Integer, Voyage> voyages = new TreeMap<>();

    private boolean isNewVoyageIdValid(int id){
        if (id <= 0){
            System.out.println(ErrorMessages.getVoyageIdMustPositiveString(id));
            return false;
        }

        if (voyages.containsKey(id)){
            System.out.println(ErrorMessages.getVoyageIdAlreadyUsingString(id));
            return false;
        }
        return true;
    }

    private boolean isVoyageIdValid(int id){
        if (id <= 0){
            System.out.println(ErrorMessages.getVoyageIdMustPositiveString(id));
            return false;
        }

        if (!voyages.containsKey(id)){
            System.out.println(ErrorMessages.getNoVoyageWithIdString(id));
            return false;
        }
        return true;
    }

    private boolean canSeatSold(int id, int seat){
        if(!voyages.get(id).isSeatNumberValid(seat)){
            System.out.println(ErrorMessages.NO_SEAT);
            return false;
        }
        if(!voyages.get(id).isSeatEmpty(seat)){
            System.out.println(ErrorMessages.SEAT_ALREADY_SOLD);
            return false;
        }
        return true;
    }

    private boolean canSeatRefunded(int id, int seat){
        if(!voyages.get(id).isSeatNumberValid(seat)){
            System.out.println(ErrorMessages.NO_SEAT);
            return false;
        }
        if(voyages.get(id).isSeatEmpty(seat)){
            System.out.println(ErrorMessages.SEAT_ALREADY_EMPTY);
            return false;
        }
        return true;
    }

    public void sellTickets(int id, int[] seats){
        if(!isVoyageIdValid(id)) return;

        for(int seat : seats){
            if(!canSeatSold(id, seat)) return;
        }
        float beforeSell = voyages.get(id).getRevenue();
        for(int seat : seats){
            voyages.get(id).sellTicket(seat);
        }
        System.out.println(InfoMessages.getSeatsSoldString(seats, id, voyages.get(id).getDeparture(), voyages.get(id).getArrival(), voyages.get(id).getRevenue() - beforeSell));
    }

    public void refundTickets(int id, int[] seats){
        if(!isVoyageIdValid(id)) return;
        if(voyages.get(id) instanceof MiniBus){
            System.out.println(ErrorMessages.MINIBUS_TICKETS_NOT_REFUNDABLE);
            return;
        }
        for(int seat : seats){
            if(!canSeatRefunded(id, seat)) return;
        }
        float beforeRefund = voyages.get(id).getRevenue();
        if(voyages.get(id) instanceof StandardBus){
            for(int seat : seats){
                ((StandardBus) voyages.get(id)).refundTicket(seat);
            }
        }
        else if(voyages.get(id) instanceof PremiumBus){
            for(int seat : seats){
                ((PremiumBus) voyages.get(id)).refundTicket(seat);
            }
        }
        System.out.println(InfoMessages.getSeatsRefundedString(seats, id, voyages.get(id).getDeparture(), voyages.get(id).getArrival(), beforeRefund - voyages.get(id).getRevenue()));
    }

    public void addStandardVoyage(int id, String departure, String arrival, int row_count, float seat_price, int refundCut){
        if (!isNewVoyageIdValid(id)) return;
        voyages.put(id, new StandardBus(id, row_count, departure, arrival, seat_price, refundCut));
        System.out.println(InfoMessages.getStandardVoyageInitializedString(id, departure, arrival, seat_price, row_count, refundCut));
    }

    public void addPremiumVoyage(int id, String departure, String arrival, int row_count, float seat_price, int refundCut, int premiumFee){
        if (!isNewVoyageIdValid(id)) return;
        PremiumBus premiumBus = new PremiumBus(id, row_count, departure, arrival, seat_price, refundCut, premiumFee);
        voyages.put(id, premiumBus);
        System.out.println(InfoMessages.getPremiumVoyageInitializedString(id, departure, arrival, seat_price, row_count, premiumBus.getPremiumPrice(), refundCut));
    }

    public void addMinibusVoyage(int id, String departure, String arrival, int row_count, float seat_price){
        if (!isNewVoyageIdValid(id)) return;
        voyages.put(id, new MiniBus(id, row_count, departure, arrival, seat_price));
        System.out.println(InfoMessages.getMinibusVoyageInitializedString(id, departure, arrival, seat_price, row_count));
    }

    public void cancelVoyage(int id){
        if (!isVoyageIdValid(id)) return;
        Voyage t = voyages.get(id);
        t.processVoyageCancelling();
        voyages.remove(id);
    }

    public void printVoyage(int id){
        if (!isVoyageIdValid(id)) return;
        voyages.get(id).printLayout();
    }

    public void zReport(){
        System.out.println("Z Report:");
        System.out.println("----------------");
        for(Voyage t : voyages.values()){
            t.printLayout();
            System.out.println("----------------");
        }
    }
}