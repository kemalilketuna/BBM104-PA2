import java.io.*;
import java.util.*;

class BookingSystem{
    public static void main(String[] args) throws Exception{
        // change print stream to file
        System.setOut(new PrintStream(new FileOutputStream(args[1])));

        // read file line by line from args[0]
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String line;        
        while((line = br.readLine()) != null){
            line = line.trim();
            if(line.length() == 0) continue;

            
        }
    }
}