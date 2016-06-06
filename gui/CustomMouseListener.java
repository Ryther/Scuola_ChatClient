package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.ClientMain;

/**
 *
 * @author Edoardo Zanoni
 */
public class CustomMouseListener implements MouseListener {

    public static final String CONNECT = "connect";
    
    private static JFrame frame;

    public CustomMouseListener(JFrame frame) {
        
        CustomMouseListener.frame = frame;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        switch(e.getComponent().getName()) {
            case "CONNECT_SPLASH_BUTTON":
                SplashFrame splashFrame = (SplashFrame) CustomMouseListener.frame;
                String nickname = splashFrame.getNicknameTextField().getText();
            //Verifico che il campo nickname non sia vuoto
                if (nickname.equals("")) {
                    JOptionPane.showMessageDialog(null,"Inserire un nickname!");
                    return;
                }
                
                String chatName = splashFrame.getChatNameTextField().getText();
            //Verifico che il campo chat non sia vuoto
                if (chatName.equals("")) {
                    JOptionPane.showMessageDialog(null,"Inserire una chat!");
                    return;
                }
                String address = splashFrame.getAddressTextField().getText();
            //Verifico che il campo address non sia vuoto
                if (address.equals("")) {
                    JOptionPane.showMessageDialog(null,"Inserire l'indirizzo del server!");
                    return;
                }
            //Verifico che il campo address non sia vuoto
                if (splashFrame.getPortTextField().getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"Inserire la porta del server!");
                    return;
                }
                int port = Integer.valueOf(splashFrame.getPortTextField().getText());
                
                Events.connect(nickname, chatName, address, port);
                break;
            case "EXIT_SPLASH_BUTTON":
                CustomMouseListener.frame.dispose();
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
