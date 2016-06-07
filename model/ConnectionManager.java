package model;

import chatUtils.data.Chat;
import chatUtils.data.UserData;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.net.SocketChannelHandler;

/**
 *
 * @author Edoardo Zanoni
 */
public class ConnectionManager {

    private static UserData userData;
    private static String chatName;
    private static InetAddress inetAddress;
    private static int port;
    private static SocketChannelHandler socketChannelHandler;
    
    public ConnectionManager(String userName, String chatName, String address, int port) {
        
        this.userData = new UserData(userName);
        this.chatName = chatName;
        this.userData.addChat(new Chat(this.chatName));
        
        this.inetAddress = null;
        try {
            this.inetAddress = InetAddress.getByName(address);
            this.port = port;
        } catch (UnknownHostException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, "Impossibile creare InetAddress", ex);
        }
    }
    
    public void connect() {
        socketChannelHandler = new SocketChannelHandler(inetAddress, port);
        socketChannelHandler.pushToChannel(userData);
    }
    
    public void disconnect() {
     
        socketChannelHandler.close();
    }
    
    public void push(Object object) {
        
        socketChannelHandler.pushToChannel(object);
    }
    
    public Object pull() {
        
        return socketChannelHandler.pullFromChannel();
    }
}
