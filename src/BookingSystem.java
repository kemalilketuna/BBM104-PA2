import java.io.*;

/**
 * The {@code CommandExecuter} class interprets and executes commands related to managing voyages.
 * It acts as a controller that delegates commands to a {@link VoyageManager}.
 */
class CommandExecuter {
    protected VoyageManager transportManager = new VoyageManager();

    /**
     * Executes the command to add a new voyage based on the specified parameters.
     * Supports adding minibuses, standard buses, and premium buses.
     * @param tokens Array of strings containing command tokens.
     */
    private void addVoyageCommand(String[] tokens){
        try{
            switch (tokens[1]) {
                case "Minibus":
                    transportManager.addMinibusVoyage(Integer.parseInt(tokens[2]), tokens[3], tokens[4], Integer.parseInt(tokens[5]), Float.parseFloat(tokens[6]));
                    break;
                case "Standard":
                    transportManager.addStandardVoyage(Integer.parseInt(tokens[2]), tokens[3], tokens[4], Integer.parseInt(tokens[5]), Float.parseFloat(tokens[6]), Integer.parseInt(tokens[7]));
                    break;
                case "Premium":
                    transportManager.addPremiumVoyage(Integer.parseInt(tokens[2]), tokens[3], tokens[4], Integer.parseInt(tokens[5]), Float.parseFloat(tokens[6]), Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]));
                    break;
                default:
                    throw new Exception("Invalid vehicle type");
            }
        }catch(Exception e){
            System.out.println(ErrorMessages.getErrorgousUsageString(tokens[0]));
        }
    }

    /**
     * Executes the command to cancel a voyage based on the specified voyage ID.
     * @param tokens Array of strings containing command tokens.
     */
    private void cancelVoyageCommand(String[] tokens){
        try{
            transportManager.cancelVoyage(Integer.parseInt(tokens[1]));
        }
        catch(Exception e){
            System.out.println(ErrorMessages.getErrorgousUsageString(tokens[0]));
        }
    }
    
    /**
     * Executes the command to sell tickets for a specified voyage and seats.
     * @param tokens Array of strings containing command tokens.
     */
    private void sellTicketCommand(String[] tokens){
        try{
            String[] seats = tokens[2].split("_");
            int[] seatNumbers = new int[seats.length];
            for(int i = 0; i < seats.length; i++){
                seatNumbers[i] = Integer.parseInt(seats[i]) - 1;
            }
            transportManager.sellTickets(Integer.parseInt(tokens[1]), seatNumbers);
        }catch(Exception e){
            System.out.println(ErrorMessages.getErrorgousUsageString(tokens[0]));
        }
    }

    /**
     * Executes the command to refund tickets for a specified voyage and seats.
     * @param tokens Array of strings containing command tokens.
     */
    private void refundTicketCommand(String[] tokens){
        try{
            String[] seats = tokens[2].split("_");
            int[] seatNumbers = new int[seats.length];
            for(int i = 0; i < seats.length; i++){
                seatNumbers[i] = Integer.parseInt(seats[i]) - 1;
            }
            transportManager.refundTickets(Integer.parseInt(tokens[1]), seatNumbers);
        }catch(Exception e){
            System.out.println(ErrorMessages.getErrorgousUsageString(tokens[0]));
        }
    }

    /**
     * Executes the command to generate a Z report, which prints details and layout of all voyages.
     */
    private void zReportCommand(){
        transportManager.zReport();
    }

    /**
     * Executes the command to print the layout and details of a specific voyage.
     * @param tokens Array of strings containing command tokens.
     */
    private void printVoyageCommand(String[] tokens){
        try{
            transportManager.printVoyage(Integer.parseInt(tokens[1]));
        }catch(Exception e){
            System.out.println(ErrorMessages.getErrorgousUsageString(tokens[0]));
        }
    }

    /**
     * Decodes and routes the command to the appropriate method.
     * @param command A string representing the command to execute.
     */
    public void executeCommand(String command){
        System.out.println(InfoMessages.getCommandMessageString(command));

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
                System.out.println(ErrorMessages.getThereIsNoCommandString(tokens[0]));
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