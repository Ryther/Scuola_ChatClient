package model;

import net.Talker;
import data.Consts;
import data.ChatMessage;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
//        try {
            inetAddress = InetAddress.getLoopbackAddress();
//        } catch (UnknownHostException ex) {
//            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        socketHandler = new SocketHandler(inetAddress, Consts.PORT);
        streamHandler = new StreamHandler(socketHandler.getSocket());
        streamHandler.init();
        chatMessage = new ChatMessage("Ryther");
        ExecutorService threadPool = Executors.newFixedThreadPool(Consts.TALKER_THREADS);
        Runnable writingThread = new Talker(streamHandler, chatMessage);
        Runnable readingThread = new Talker(streamHandler);
        threadPool.submit(writingThread);
        threadPool.submit(readingThread);
    }
}
