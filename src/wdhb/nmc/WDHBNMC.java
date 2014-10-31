
package wdhb.nmc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jesse Voight
 * @description Network Message Client
 */
public class WDHBNMC {
    private static int networkSocketNumber = 1234;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length > 1){
        if(!args[0].equals("")){
            try{
                networkSocketNumber = Integer.valueOf(args[0]);
            }
            catch(NumberFormatException e){
                System.err.println(e);
            }
        }
        }
        try {
            Socket listenSocket = new Socket("wsc852",networkSocketNumber);
            BufferedReader inReader = new BufferedReader(new InputStreamReader(listenSocket.getInputStream()));
            PrintWriter out = new PrintWriter(listenSocket.getOutputStream(), true);
            out.write("Test From Colient");
            out.flush();
            System.out.println(inReader.readLine());
        } 
        catch (IOException ex) {
            System.err.println("Could not create socket for port: "+String.valueOf(networkSocketNumber));
            Logger.getLogger(WDHBNMC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
