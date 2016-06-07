package gui;

import chatUtils.data.ChatMessage;
import data.Reader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.ConnectionManager;

/**
 *
 * @author Edoardo Zanoni
 */
public class CustomActionListener implements ActionListener {
    
    private JFrame frame;
    private ConnectionManager connectionManager;
    
    private Reader objectObserved;

    public CustomActionListener() {
        
    }
    
    public CustomActionListener(JFrame frame) {
        
        this.frame = frame;
    }

    public void setFrame(JFrame frame) {
        
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            switch(button.getName()) {
                case "CONNECT_SPLASH_BUTTON":
                    SplashFrame sFrame = (SplashFrame) this.frame;
                    String nickname = sFrame.getNicknameTextField().getText();
                //Verifico che il campo nickname non sia vuoto
                    if (nickname.equals("")) {
                        JOptionPane.showMessageDialog(null,"Inserire un nickname!");
                        return;
                    }

                    String chatName = sFrame.getChatNameTextField().getText();
                //Verifico che il campo chat non sia vuoto
                    if (chatName.equals("")) {
                        JOptionPane.showMessageDialog(null,"Inserire una chat!");
                        return;
                    }
                    String address = sFrame.getAddressTextField().getText();
                //Verifico che il campo address non sia vuoto
                    if (address.equals("")) {
                        JOptionPane.showMessageDialog(null,"Inserire l'indirizzo del server!");
                        return;
                    }
                //Verifico che il campo address non sia vuoto
                    if (sFrame.getPortTextField().getText().equals("")) {
                        JOptionPane.showMessageDialog(null,"Inserire la porta del server!");
                        return;
                    }
                    int port = Integer.valueOf(sFrame.getPortTextField().getText());

                    this.frame.dispose();
                    connectionManager = new ConnectionManager(nickname, chatName, address, port);
                    MainFrame mainFrame = new MainFrame(nickname, chatName, this);
                    Reader reader = new Reader(mainFrame, this.connectionManager);
                    Thread readerThread = new Thread(reader);
                    readerThread.start();
                    break;
                case "EXIT_SPLASH_BUTTON":
                    this.frame.dispose();
                    break;
                case "SEND_MAIN_BUTTON":
                    MainFrame mFrame = (MainFrame) this.frame;
                    String message = mFrame.getLeftSouthMessageTextField().getText();
                    if (!message.equals("")) {
                        
                        ChatMessage chatMessage = new ChatMessage(mFrame.getNickname());
                        chatMessage.setChatName(mFrame.getChatName());
                        chatMessage.setMessage(message);
                        chatMessage.setDateTime();
                        connectionManager.push(chatMessage);
                    }
            }
        }
    }
}
