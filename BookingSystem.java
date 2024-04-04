import java.io.*;

class CommandExecuter{
    protected TransportManager transportManager = new TransportManager();

    /*
    Command list
    INIT_VOYAGE
    SELL_TICKET
    REFUND_TICKET
    PRINT_VOYAGE
    CANCEL_VOYAGE
    Z_REPORT
    */

    private void addVoyageCommand(String[] tokens){
    }

    private void cancelVoyageCommand(String[] tokens){
    }
    
    private void sellTicketCommand(String[] tokens){
    }

    private void refundTicketCommand(String[] tokens){
    }

    private void zReportCommand(){
    }

    private void printVoyageCommand(String[] tokens){
    }

    public void executeCommand(String command){
        
    }
}

public class BookingSystem{
    public static void main(String[] args) throws Exception{
        // original print stream
        PrintStream originalOut = System.out;
        // change print stream to file
        System.setOut(new PrintStream(new FileOutputStream(args[1])));

        CommandExecuter commandExecuter = new CommandExecuter();

        // read file line by line from args[0]
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String line;        
        while((line = br.readLine()) != null){
            line = line.trim();
            if(line.length() == 0) continue;
            commandExecuter.executeCommand(line);
        }

        // close the file
        br.close();

        // reset print stream
        System.setOut(originalOut);
    }
}