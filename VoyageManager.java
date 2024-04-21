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

    protected boolean isSeatNumberValid(int seatNumber){
        int row = seatNumber / this.column_count;
        int column = seatNumber % this.column_count;
        return row >= 0 && row < this.row_count && column >= 0 && column < this.column_count;
    }

    public void printLayout(){
        System.out.println(InfoMessages.getVoyage(this.id));
        System.out.println(InfoMessages.getVoyageRoute(this.departure, this.arrival));
        for(int i = 0; i < this.row_count; i++){
            for(int j = 0; j < this.column_count; j++){
                System.out.print(this.seats[i][j] ? "X" : "*");
                if(column_count == 3 && j == 0) System.out.print(" | ");
                if(column_count == 4 && j == 1) System.out.print(" | ");
            }
            System.out.println();
        }
        System.out.println(InfoMessages.getRevenueMessage(this.revenue));
    }

    public void processVoyageCancelling(){
        System.out.println(InfoMessages.getVoyageSuccesfullyCancelled(this.id));
        System.out.println(InfoMessages.VOYAGE_DETAILS);
        System.out.println(InfoMessages.getRevenueMessage(this.revenue));
        System.out.println(InfoMessages.getVoyageRoute(this.departure, this.arrival));
      
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
        System.out.println(InfoMessages.getRevenueMessage(this.revenue));
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
}

class PremiumBus extends Voyage{
    private float refundAmount;
    private float premiumPrice;

    public PremiumBus(int id, int row_count, String departure, String arrival, int seat_price, int refundCut, int premiumFee){
        super(id, row_count, departure, arrival, 3, seat_price);
        refundAmount = (100.f - (float) refundCut / 100.f) * seat_price;
        premiumPrice = (100.f + (float) premiumFee / 100.f) * seat_price;
        // round to 2 decimal places float
        refundAmount = (float) Math.round(refundAmount * 100) / 100;
        premiumPrice = (float) Math.round(premiumPrice * 100) / 100;
    }

    public float getRefundAmount(){
        return this.refundAmount;
    }

    public float getPremiumPrice(){
        return this.premiumPrice;
    }

    public void processVoyageCancelling(){
        System.out.println(InfoMessages.getVoyageSuccesfullyCancelled(this.getId()));
        System.out.println(InfoMessages.VOYAGE_DETAILS);
        System.out.println(InfoMessages.getRevenueMessage(this.getRevenue()));
        System.out.println(InfoMessages.getVoyageRoute(this.getDeparture(), this.getArrival()));
      
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

        System.out.println(InfoMessages.getRevenueMessage(this.revenue));
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
    INIT_VOYAGE
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

    private boolean isSeatEmpty(int id, int seat){
        if(!voyages.get(id).isSeatNumberValid(seat)){
            System.out.println(ErrorMessages.NO_SEAT);
            return false;
        }
        return true;
    }

    public void addStandardVoyage(int id, String departure, String arrival, int row_count, int seat_price, int refundCut){
        if (!isNewVoyageIdValid(id)) return;
        voyages.put(id, new StandardBus(id, row_count, departure, arrival, seat_price, refundCut));
    }

    public void addPremiumVoyage(int id, String departure, String arrival, int row_count, int seat_price, int refundCut, int premiumFee){
        if (!isNewVoyageIdValid(id)) return;
        voyages.put(id, new PremiumBus(id, row_count, departure, arrival, seat_price, refundCut, premiumFee));
    }

    public void addMiniVoyage(int id, String departure, String arrival, int row_count, int seat_price){
        if (!isNewVoyageIdValid(id)) return;
        voyages.put(id, new MiniBus(id, row_count, departure, arrival, seat_price));
    }

    public void cancelVoyage(int id){
        if (!isVoyageIdValid(id)) return;
        Voyage t = voyages.get(id);
        t.processVoyageCancelling();
        voyages.remove(id);
    }

    public void sellTickets(int id, int[] seats){
    
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