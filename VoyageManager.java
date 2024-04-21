import java.util.*;

class Voyage{
    private int id;
    private int row_count;
    private String departure;
    private String arrival;
    private int column_count;
    private float seat_price;
    private boolean[][] seats;
    protected float revenue;

    public Voyage(int id, int row_count, String departure, String arrival, int column_count, int seat_price){
        this.id = id;
        this.row_count = row_count;
        this.departure = departure;
        this.arrival = arrival;
        this.column_count = column_count;
        this.seat_price = seat_price;
        this.seats = new boolean[row_count][column_count];
    }

    public int getId(){
        return this.id;
    }

    public int getRowCount(){
        return this.row_count;
    }

    public String getDeparture(){
        return this.departure;
    }

    public String getArrival(){
        return this.arrival;
    }

    public int getColumnCount(){
        return this.column_count;
    }

    public float getSeatPrice(){
        return this.seat_price;
    }

    public boolean[][] getSeats(){
        return this.seats;
    }

    public float getRevenue(){
        return this.revenue;
    }

    public void increaseRevenue(float amount){
        this.revenue += amount;
    }

    public void sellTicket(int seatNumber){
        changeSeatStatus(seatNumber);
        increaseRevenue(this.getSeatPrice());
    }

    protected void changeSeatStatus(int seatNumber){
        int row = seatNumber / this.column_count;
        int column = seatNumber % this.column_count;
        this.seats[row][column] = !this.seats[row][column];
    }

    public boolean isSeatNumberValid(int seatNumber){
        int row = seatNumber / this.column_count;
        int column = seatNumber % this.column_count;
        return row >= 0 && row < this.row_count && column >= 0 && column < this.column_count;
    }

    public boolean isSeatEmpty(int seatNumber){
        int row = seatNumber / this.column_count;
        int column = seatNumber % this.column_count;
        return !this.seats[row][column];
    }

    public void printLayout(){
        System.out.println(InfoMessages.getVoyageString(this.id));
        System.out.println(InfoMessages.getVoyageRouteString(this.departure, this.arrival));
        for(int i = 0; i < this.row_count; i++){
            for(int j = 0; j < this.column_count; j++){
                System.out.print(this.seats[i][j] ? "X" : "*");
                if(column_count == 3 && j == 0) System.out.print(" | ");
                if(column_count == 4 && j == 1) System.out.print(" | ");
            }
            System.out.println();
        }
        System.out.println(InfoMessages.getRevenueMessageString(this.revenue));
    }

    public void processVoyageCancelling(){
        System.out.println(InfoMessages.getVoyageSuccesfullyCancelledString(this.id));
        System.out.println(InfoMessages.VOYAGE_DETAILS);
        System.out.println(InfoMessages.getRevenueMessageString(this.revenue));
        System.out.println(InfoMessages.getVoyageRouteString(this.departure, this.arrival));
      
        for(int i = 0; i < this.row_count; i++){
            for(int j = 0; j < this.column_count; j++){
                System.out.print(this.seats[i][j] ? "X" : "*");
                if(column_count == 3 && j == 0) System.out.print(" | ");
                if(column_count == 4 && j == 1) System.out.print(" | ");
            }
            System.out.println();
        }

        for(int i = 0; i < this.row_count; i++){
            for(int j = 0; j < this.column_count; j++){
                this.seats[i][j] = false;
                this.revenue -= this.seat_price;
            }
        }
        System.out.println(InfoMessages.getRevenueMessageString(this.revenue));
    }
}


class StandardBus extends Voyage{
    private float refundAmount;

    public StandardBus(int id, int row_count, String departure, String arrival, int seat_price, int refundCut){
        super(id, row_count, departure, arrival, 4, seat_price);
        refundAmount = (100.f - (float) refundCut / 100.f) * seat_price;
        // round to 2 decimal places float
        refundAmount = (float) Math.round(refundAmount * 100) / 100;
    }

    public float getRefundAmount(){
        return this.refundAmount;
    }

    public void refundTicket(int seatNumber){
        changeSeatStatus(seatNumber);
        increaseRevenue(-this.getRefundAmount());
    }
}

class PremiumBus extends Voyage{
    private float refundAmount;
    private float premiumPrice;
    private float premiumRefundAmount;

    public PremiumBus(int id, int row_count, String departure, String arrival, int seat_price, int refundCut, int premiumFee){
        super(id, row_count, departure, arrival, 3, seat_price);
        refundAmount = (100.f - (float) refundCut / 100.f) * seat_price;
        premiumPrice = (100.f + (float) premiumFee / 100.f) * seat_price;
        premiumRefundAmount = (100.f - (float) refundCut / 100.f) * premiumPrice;
        // round to 2 decimal places float
        refundAmount = (float) Math.round(refundAmount * 100) / 100;
        premiumPrice = (float) Math.round(premiumPrice * 100) / 100;
        premiumRefundAmount = (float) Math.round(premiumRefundAmount * 100) / 100;
    }

    public float getRefundAmount(){
        return this.refundAmount;
    }

    public float getPremiumPrice(){
        return this.premiumPrice;
    }

    public float getPremiumRefundAmount(){
        return this.premiumRefundAmount;
    }

    private boolean isPremiumSeat(int seatNumber){
        return seatNumber % this.getColumnCount() == 0;
    }

    public void sellTicket(int seatNumber){
        if(isPremiumSeat(seatNumber)){
            changeSeatStatus(seatNumber);
            increaseRevenue(this.getPremiumPrice());
        }
        else{
            changeSeatStatus(seatNumber);
            increaseRevenue(this.getSeatPrice());
        }
    }

    public void refundTicket(int seatNumber){
        if(isPremiumSeat(seatNumber)){
            changeSeatStatus(seatNumber);
            increaseRevenue(-this.getPremiumRefundAmount());
        }
        else{
            changeSeatStatus(seatNumber);
            increaseRevenue(-this.getRefundAmount());
        }
    }

    public void processVoyageCancelling(){
        System.out.println(InfoMessages.getVoyageSuccesfullyCancelledString(this.getId()));
        System.out.println(InfoMessages.VOYAGE_DETAILS);
        System.out.println(InfoMessages.getRevenueMessageString(this.getRevenue()));
        System.out.println(InfoMessages.getVoyageRouteString(this.getDeparture(), this.getArrival()));
      
        for(int i = 0; i < this.getRowCount(); i++){
            for(int j = 0; j < this.getColumnCount(); j++){
                System.out.print(this.getSeats()[i][j] ? "X" : "*");
                if(this.getColumnCount() == 3 && j == 0) System.out.print(" | ");
                if(this.getColumnCount() == 4 && j == 1) System.out.print(" | ");
            }
            System.out.println();
        }

        for(int i = 0; i < this.getRowCount(); i++){
            for(int j = 0; j < this.getColumnCount(); j++){
                this.getSeats()[i][j] = false;
                if(i==0) this.revenue -= this.premiumPrice;
                else this.revenue -= this.getSeatPrice();
            }
        }

        System.out.println(InfoMessages.getRevenueMessageString(this.revenue));
    }
}

class MiniBus extends Voyage{
    public MiniBus(int id, int row_count, String departure, String arrival, int seat_price){
        super(id, row_count, departure, arrival, 2, seat_price);
    }
}

public class VoyageManager{
    private Map<Integer, Voyage> voyages = new TreeMap<>();


    /*
    Command list
    INIT_VOYAGE done
    SELL_TICKET
    REFUND_TICKET
    PRINT_VOYAGE
    CANCEL_VOYAGE
    Z_REPORT
    */

    private boolean isNewVoyageIdValid(int id){
        if (id <= 0){
            System.out.println(ErrorMessages.getVoyageIdMustPositiveString(id));
            return false;
        }

        if (voyages.containsKey(id)){
            System.out.println(ErrorMessages.getVoyageIdAlreadyUsingString(id));
            return false;
        }
        return voyages.containsKey(id);
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

    public void addStandardVoyage(int id, String departure, String arrival, int row_count, int seat_price, int refundCut){
        if (!isNewVoyageIdValid(id)) return;
        voyages.put(id, new StandardBus(id, row_count, departure, arrival, seat_price, refundCut));
        System.out.println(InfoMessages.getStandardVoyageInitializedString(id, departure, arrival, seat_price, row_count, refundCut));
    }

    public void addPremiumVoyage(int id, String departure, String arrival, int row_count, int seat_price, int refundCut, int premiumFee){
        if (!isNewVoyageIdValid(id)) return;
        voyages.put(id, new PremiumBus(id, row_count, departure, arrival, seat_price, refundCut, premiumFee));
        System.out.println(InfoMessages.getPremiumVoyageInitializedString(id, departure, arrival, seat_price, row_count, premiumFee, row_count, refundCut));
    }

    public void addMinibusVoyage(int id, String departure, String arrival, int row_count, int seat_price){
        if (!isNewVoyageIdValid(id)) return;
        voyages.put(id, new MiniBus(id, row_count, departure, arrival, seat_price));
        System.out.println(InfoMessages.getMinibusVoyageInitializedString(id, departure, arrival, seat_price, row_count));
    }

    public void cancelVoyage(int id){
        if (!isVoyageIdValid(id)) return;
        Voyage t = voyages.get(id);
        t.processVoyageCancelling();
        voyages.remove(id);
        System.out.println(InfoMessages.getVoyageSuccesfullyCancelledString(id));
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