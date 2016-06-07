package gui;

import chatUtils.data.ObjectObserved;
import chatUtils.data.ObjectObserver;
import model.ClientMain;

/**
 *
 * @author Edoardo Zanoni
 */
public class Events {
    
    private static Thread clientThread;
            
    public static void connect(String nickname, String chatName, String address, int port, ObjectObserved objectObserved, ObjectObserver objectObserver) {
        
        ClientMain client = new ClientMain(nickname, chatName, address, port);
        client.setObjectObserved(objectObserved);
        client.setObjectObserver(objectObserver);
        Events.clientThread = new Thread(client);
        clientThread.start();
//        try {
//            clientThread.join();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(CustomMouseListener.class.getName()).log(Level.SEVERE, "Processo client interrotto inaspettatamente", ex);
//        }
    }
}
