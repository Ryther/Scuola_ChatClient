package model;

import chatUtils.data.Chat;
import chatUtils.data.Consts;
import chatUtils.data.ChatMessage;
import chatUtils.data.UserData;
import chatUtils.net.Talker;
import chatUtils.net.Talker.TalkerType;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import chatUtils.data.ObjectObserved;
import utils.net.SocketChannelHandler;

/**
 *
 * @author Edoardo Zanoni
 */
public class ClientMain implements Runnable {

    private static UserData userData;
    private static ChatMessage chatMessage;
    private static String chatName;
    private static InetAddress inetAddress;
    private static int port;
    private static SocketChannelHandler socketChannelHandler;
    private static ObjectObserved objectObserved;
    
    public ClientMain(String userName, String chatName, String address, int port, ObjectObserved objectObserved) {
        
        this.objectObserved = objectObserved;
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

    @Override
    public void run() {
        socketChannelHandler = new SocketChannelHandler(inetAddress, port);
        socketChannelHandler.pushToChannel(userData);

        chatMessage = new ChatMessage(userData.getUserName());
        chatMessage.setChatName(this.chatName);
        
        ExecutorService threadPool = Executors.newFixedThreadPool(Consts.TALKER_THREADS);
        Talker reader = new Talker(userData, dataMap, socketChannelHandler, TalkerType.READER);
        Talker writer = new Talker(userData, dataMap, socketChannelHandler, TalkerType.WRITER);
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
