import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.SocketHandler;
import net.StreamHandler;

/**
 *
 * @author Edoardo Zanoni
 */
public class ClientMain {

    private static SocketHandler socketHandler;
    private static StreamHandler streamHandler;
    private static ChatMessage chatMessage;
    
    public static void main(String[] args) {
        
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        socketHandler = new SocketHandler(inetAddress, Consts.PORT);
        streamHandler = new StreamHandler(socketHandler.getSocket());
        streamHandler.init();
        chatMessage = new ChatMessage("Ryther");
        chatMessage.setMessage("Messaggio di prova");
        chatMessage.setDate();
        streamHandler.pushToStream(chatMessage);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
