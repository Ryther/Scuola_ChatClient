package gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.ClientMain;

/**
 *
 * @author Edoardo Zanoni
 */
public class Events {
    
    public static void connect(String nickname, String chatName, String address, int port) {
        
        System.out.println("Bottone connect cliccato!");
        Runnable client = new ClientMain(nickname, chatName, address, port);
        Thread clientThread = new Thread(client);
        clientThread.start();
        try {
            clientThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(CustomMouseListener.class.getName()).log(Level.SEVERE, "Processo client interrotto inaspettatamente", ex);
        }
    }
}
