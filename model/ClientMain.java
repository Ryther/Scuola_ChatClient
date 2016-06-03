package model;

import chatUtils.data.Chat;
import chatUtils.data.Consts;
import chatUtils.data.ChatMessage;
import chatUtils.data.UserData;
import java.util.Scanner;
import utils.net.SocketChannelHandler;
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
    
    private static SocketChannelHandler socketChannelHandler;
    
//    public static void main(String[] args) {
//        
//        System.out.print("Inserire nickname: ");
//        String userName = new Scanner(System.in).nextLine();
//        socketHandler = new SocketHandler(Consts.INETADDRESS, Consts.PORT);
//        streamHandler = new StreamHandler(socketHandler.getSocket());
//        streamHandler.init();
//        UserData userData = new UserData(userName);
//        System.out.print("Inserire chat a cui connettersi: ");
//        String chatName = new Scanner(System.in).nextLine();
//        userData.addChat(new Chat(chatName));
//        streamHandler.pushToStream(userData);
//        chatMessage = new ChatMessage(userName);
//        chatMessage.setChatName(chatName);
//        ExecutorService threadPool = Executors.newFixedThreadPool(Consts.TALKER_THREADS);
//        Runnable writingThread = new Talker(streamHandler, chatMessage);
//        Runnable readingThread = new Talker(streamHandler);
//        threadPool.submit(writingThread);
//        threadPool.submit(readingThread);
//    }
    
    public static void main(String[] args) {
        System.out.print("Inserire nickname: ");
        String userName = new Scanner(System.in).nextLine();
        
        UserData userData = new UserData(userName);
        
        System.out.print("Inserire chat a cui connettersi: ");
        String chatName = new Scanner(System.in).nextLine();
        userData.addChat(new Chat(chatName));
        
        socketChannelHandler = new SocketChannelHandler(Consts.INETADDRESS, Consts.PORT);

        chatMessage = new ChatMessage(userName);

        while (true) {

            chatMessage.setMessage(new Scanner(System.in).nextLine());
            chatMessage.setDateTime();
            socketChannelHandler.pushToChannel(chatMessage);
        }
        
//        socketChannelHandler.close();
    }
}
