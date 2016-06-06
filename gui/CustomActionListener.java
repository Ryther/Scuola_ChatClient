package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Edoardo Zanoni
 */
public class CustomActionListener implements ActionListener {
    
    private static JFrame frame;

    public CustomActionListener(JFrame frame) {
        
        CustomActionListener.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            switch(button.getName()) {
                case "CONNECT_SPLASH_BUTTON":
                    SplashFrame splashFrame = (SplashFrame) CustomActionListener.frame;
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
                    CustomActionListener.frame.dispose();
                    break;
            }
        }
    }
}
