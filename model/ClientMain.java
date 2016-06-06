package model;

import chatUtils.data.Chat;
import chatUtils.data.Consts;
import chatUtils.data.ChatMessage;
import chatUtils.data.UserData;
import chatUtils.net.Talker;
import chatUtils.net.Talker.TalkerType;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.net.SocketChannelHandler;

/**
 *
 * @author Edoardo Zanoni
 */
public class ClientMain {

    private static ChatMessage chatMessage;
    private static SocketChannelHandler socketChannelHandler;
    private static Map<Talker.TalkerType, Object> dataMap;
    
    public static void main(String[] args) {
        dataMap = new ConcurrentHashMap();
        System.out.print("Inserire nickname: ");
        String userName = new Scanner(System.in).nextLine();
        
        UserData userData = new UserData(userName);
        
        System.out.print("Inserire chat a cui connettersi: ");
        String chatName = new Scanner(System.in).nextLine();
        userData.addChat(new Chat(chatName));
        
        socketChannelHandler = new SocketChannelHandler(Consts.INETADDRESS, Consts.PORT);
        socketChannelHandler.pushToChannel(userData);

        chatMessage = new ChatMessage(userName);
        chatMessage.setChatName(chatName);

        socketChannelHandler.pushToChannel(userData);
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
