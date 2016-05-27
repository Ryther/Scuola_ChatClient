package model;

import chatUtils.data.Chat;
import chatUtils.net.Talker;
import chatUtils.data.Consts;
import chatUtils.data.ChatMessage;
import chatUtils.data.UserData;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import utils.net.SocketHandler;
import utils.net.StreamHandler;

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
        
        String userName = new Scanner(System.in).nextLine();
        socketHandler = new SocketHandler(inetAddress, Consts.PORT);
        streamHandler = new StreamHandler(socketHandler.getSocket());
        streamHandler.init();
        UserData userData = new UserData(userName);
        userData.addChat(new Chat("Prova"));
        streamHandler.pushToStream(userData);
        chatMessage = new ChatMessage(userName);
        ExecutorService threadPool = Executors.newFixedThreadPool(Consts.TALKER_THREADS);
        Runnable writingThread = new Talker(streamHandler, chatMessage);
        Runnable readingThread = new Talker(streamHandler);
        threadPool.submit(writingThread);
        threadPool.submit(readingThread);
    }
}
