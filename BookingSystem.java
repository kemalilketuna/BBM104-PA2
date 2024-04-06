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

    /*
    This command’s mechanics are as follows:
    INIT_VOYAGE <TAB> Minibus <TAB> <ID> <TAB> <FROM> <TAB> <TO> <TAB> <#ROWS> <TAB> <PRICE>
    INIT_VOYAGE <TAB> Standard <TAB> <ID> <TAB> <FROM> <TAB> <TO> <TAB> <#ROWS> <TAB> <PRICE> <TAB> <REFUND_CUT>
    INIT_VOYAGE <TAB> Premium <TAB> <ID> <TAB> <FROM> <TAB> <TO> <TAB> <#ROWS> <TAB> <PRICE> <TAB> <REFUND_CUT> <TAB> <PREMIUM_FEE>
    */

    private void addVoyageCommand(String[] tokens){
        switch (tokens[1]) {
            case "Minibus":
                transportManager.addMiniVoyage(Integer.parseInt(tokens[2]), tokens[3], tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
                break;
            case "Standard":
                transportManager.addStandardVoyage(Integer.parseInt(tokens[2]), tokens[3], tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]));
                break;
            case "Premium":
                transportManager.addPremiumVoyage(Integer.parseInt(tokens[2]), tokens[3], tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]));
                break;
            default:
                break;
        }
    }

    private void cancelVoyageCommand(String[] tokens){
        
    }
    
    private void sellTicketCommand(String[] tokens){
    }

    private void refundTicketCommand(String[] tokens){
    }

    private void zReportCommand(){
        transportManager.zReport();
    }

    private void printVoyageCommand(String[] tokens){
    }

    public void executeCommand(String command){
        System.out.println(InfoMessages.getCommandMessage(command));

        String tokens[] = command.split("\t");
        switch (tokens[0]) {
            case "INIT_VOYAGE":
                addVoyageCommand(tokens);
                break;
            case "CANCEL_VOYAGE":
                cancelVoyageCommand(tokens);
                break;
            case "SELL_TICKET":
                sellTicketCommand(tokens);
                break;
            case "REFUND_TICKET":
                refundTicketCommand(tokens);
                break;
            case "Z_REPORT":
                zReportCommand();
                break;
            case "PRINT_VOYAGE":
                printVoyageCommand(tokens);
                break;
            default:
                break;
        }
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