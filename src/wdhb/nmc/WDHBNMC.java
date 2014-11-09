package wdhb.nmc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesse Voight
 * @description Network Message Client
 */
public class WDHBNMC {

    private static int networkSocketNumber = 6789;
    private static int programStatus;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        programStatus = 0;
        while (programStatus == 0) {
            try {
                ServerSocket listenSocket = new ServerSocket(networkSocketNumber);
                Socket liveSocket = listenSocket.accept();
                BufferedReader inReader = new BufferedReader(new InputStreamReader(liveSocket.getInputStream()));
                PrintWriter out = new PrintWriter(liveSocket.getOutputStream(), true);
                out.write("Message Recieved");
                out.flush();
                String message = inReader.readLine();
                out.close();
                inReader.close();
                liveSocket.close();
                listenSocket.close();
                JOptionPane.showMessageDialog(null, message);
                System.out.println(message);
                //liveSocket.setKeepAlive(true);
            } catch (IOException ex) {
                System.err.println("Could not create socket for port: " + String.valueOf(networkSocketNumber));
                Logger.getLogger(WDHBNMC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
