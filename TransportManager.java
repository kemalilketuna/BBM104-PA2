import java.util.*;

class Voyage{
    protected int id;
    protected int row_count;
    protected String departure;
    protected String arrival;
    protected int column_count;
    protected float seat_price;
    protected boolean[][] seats;
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

    protected boolean isValidSeat(int seatNumber){
        int row = seatNumber / this.column_count;
        int column = seatNumber % this.column_count;
        return row >= 0 && row < this.row_count && column >= 0 && column < this.column_count;
    }

    public void printLayout(){
        System.out.println("Voyage " + this.id);
        System.out.println(departure + "-" + arrival);
        for(int i = 0; i < this.row_count; i++){
            for(int j = 0; j < this.column_count; j++){
                System.out.print(this.seats[i][j] ? "X" : "*");
                if(column_count == 3 && j == 0) System.out.print(" | ");
                if(column_count == 4 && j == 1) System.out.print(" | ");
            }
            System.out.println();
        }
        System.out.println("Revenue: " + String.format("%.2f", this.revenue));
    }

    public void cancelVoyage(){
        for(int i = 0; i < this.row_count; i++){
            for(int j = 0; j < this.column_count; j++){
                this.seats[i][j] = false;
                this.revenue -= this.seat_price;
            }
        }
    }
}

class STANDARDBus extends Voyage{
    private float refundAmount;

    public STANDARDBus(int id, int row_count, String departure, String arrival, int seat_price, int refundCut){
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

    private Boolean isPremium(int seatNumber){
        return seatNumber % column_count == 1;
    }

    public float getRefundAmount(){
        return this.refundAmount;
    }

    public float getPremiumPrice(){
        return this.premiumPrice;
    }

    public void cancelVoyage(){
        for(int i = 0; i < this.row_count; i++){
            for(int j = 0; j < this.column_count; j++){
                this.seats[i][j] = false;
                if(i==0) this.revenue -= this.premiumPrice;
                else this.revenue -= this.seat_price;
            }
        }
    }
}

class MiniBus extends Voyage{
    public MiniBus(int id, int row_count, String departure, String arrival, int seat_price){
        super(id, row_count, departure, arrival, 2, seat_price);
    }
}

public class TransportManager{
    // use tree map
    private Map<Integer, Voyage> Voyages = new TreeMap<>();


    /*
    Command list
    INIT_VOYAGE
    SELL_TICKET
    REFUND_TICKET
    PRINT_VOYAGE
    CANCEL_VOYAGE
    Z_REPORT
    */


    private void addSTANDARDVoyage(int id, String departure, String arrival, int row_count, int seat_price, int refundCut){
        Voyages.put(id, new STANDARDBus(id, row_count, departure, arrival, seat_price, refundCut));
    }

    private void addPremiumVoyage(int id, String departure, String arrival, int row_count, int seat_price, int refundCut, int premiumFee){
        Voyages.put(id, new PremiumBus(id, row_count, departure, arrival, seat_price, refundCut, premiumFee));
    }

    private void addMiniVoyage(int id, String departure, String arrival, int row_count, int seat_price){
        Voyages.put(id, new MiniBus(id, row_count, departure, arrival, seat_price));
    }

    public void addVoyage(VoyageTypes type, int id, String departure, String arrival, int row_count, int seat_price, int refundCut, int premiumFee){
        switch(type){
            case STANDARD:
                addSTANDARDVoyage(id, departure, arrival, row_count, seat_price, refundCut);
                break;
            case PREMIUM:
                addPremiumVoyage(id, departure, arrival, row_count, seat_price, refundCut, premiumFee);
                break;
            case MINIBUS:
                addMiniVoyage(id, departure, arrival, row_count, seat_price);
                break;
        }
    }

    public void zReport(){
        System.out.println("Z Report:");
        System.out.println("----------------");
        for(Voyage t : Voyages.values()){
            t.printLayout();
            System.out.println("----------------");
        }
    }
}