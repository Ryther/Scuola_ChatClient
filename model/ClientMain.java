package model;

import chatUtils.data.Chat;
import chatUtils.data.Consts;
import chatUtils.data.UserData;
import chatUtils.net.Talker;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import chatUtils.data.ObjectObserved;
import chatUtils.data.ObjectObserver;
import utils.net.SocketChannelHandler;

/**
 *
 * @author Edoardo Zanoni
 */
public class ClientMain implements Runnable {

    private static UserData userData;
    private static String chatName;
    private static InetAddress inetAddress;
    private static int port;
    private static SocketChannelHandler socketChannelHandler;
    private ObjectObserved objectObserved;
    private ObjectObserver objectObserver;
    
    public ClientMain(String userName, String chatName, String address, int port) {
        
        this.userData = new UserData(userName);
        this.chatName = chatName;
        this.userData.addChat(new Chat(this.chatName));
        
        this.inetAddress = null;
        try {
            this.inetAddress = InetAddress.getByName(address);
            this.port = port;
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, "Impossibile creare InetAddress", ex);
        }
    }

    public void setObjectObserved(ObjectObserved objectObserved) {
        
        this.objectObserved = objectObserved;
    }

    public void setObjectObserver(ObjectObserver objectObserver) {
        
        this.objectObserver = objectObserver;
    }
    
    @Override
    public void run() {
        socketChannelHandler = new SocketChannelHandler(inetAddress, port);
        socketChannelHandler.pushToChannel(userData);
        
        ExecutorService threadPool = Executors.newFixedThreadPool(Consts.TALKER_THREADS);
        Talker writer = new Talker(socketChannelHandler, this.objectObserver);
        Talker reader = new Talker(socketChannelHandler, this.objectObserved);
        threadPool.execute(writer);
        threadPool.execute(reader);
        
        try {
            threadPool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        socketChannelHandler.close();
    }
}
