package model;

import chatUtils.data.Chat;
import chatUtils.data.Consts;
import chatUtils.data.ChatMessage;
import chatUtils.data.UserData;
import chatUtils.net.Talker;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
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

        while (true) {

            chatMessage.setMessage(new Scanner(System.in).nextLine());
            chatMessage.setDateTime();
            socketChannelHandler.pushToChannel(chatMessage);
        }
        
//        socketChannelHandler.close();
    }
}
