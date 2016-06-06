package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author Edoardo Zanoni
 */
public class MainFrame extends JFrame {

    private static String title = "SpChat";
    
//Pannello principale
    private static JPanel leftMainPanel = new JPanel(new BorderLayout());
    private static JPanel rightMainPanel = new JPanel(new BorderLayout());
//Pannello Sinistra/Nord
    private static JPanel leftNordPanel = new JPanel();
    private static JLabel leftNordLabel = new JLabel("Nickname");
//Pannello Sinistra/Centrale
    private static JPanel leftCenterPanel = new JPanel();
    private static JTextArea leftCenterLogTextField = new JTextArea(30,60);
//Pannello Sinistra/Sud
    private static JPanel leftSouthPanel = new JPanel();
    private static JTextField leftSouthMessageTextField = new JTextField("Message", 50);
    private static JButton leftSouthSendButton = new JButton("INVIA");
    
//Pannello Destra/Nord
    private static JPanel rightNordPanel = new JPanel();
    private static JLabel rightNordLabel = new JLabel("Utenti connessi");
//Pannello Destra/Centro
    private static JPanel rightCenterPanel = new JPanel();
    private static JTextArea rightCenterUsersTextField = new JTextArea(30, 18);
//Pannello Destra/Sud
    private static JPanel rightSouthPanel = new JPanel();
    private static JButton rightSouthDisconnectButton = new JButton("DISCONNETTI");
    private static JButton rightSouthExitButton = new JButton("ESCI");

    public MainFrame() {

        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    //Popolo il pannello Sinistra/Nord
        this.leftNordPanel.add(this.leftNordLabel);
    //Popolo il pannello Sinistra/Centrale
        this.leftCenterPanel.add(this.leftCenterLogTextField);
        this.leftCenterLogTextField.setEditable(false);
        this.leftCenterLogTextField.setPreferredSize(new Dimension(300, 300));
    //Popolo il pannello Sinistra/Sud
        this.leftSouthPanel.add(this.leftSouthMessageTextField);
        this.leftSouthPanel.add(this.leftSouthSendButton);
    //Popolo il pannello principale Sinistra
        this.leftMainPanel.add(this.leftNordPanel, BorderLayout.NORTH);
        this.leftMainPanel.add(this.leftCenterPanel, BorderLayout.CENTER);
        this.leftMainPanel.add(this.leftSouthPanel, BorderLayout.SOUTH);
        
    //Popolo il pannello Destra/Nord    
        this.rightNordPanel.add(rightNordLabel);
    //Popolo il pannello Destra/Centrale
        this.rightCenterPanel.add(rightCenterUsersTextField);
    //Popolo il pannello Destra/Sud
        this.rightSouthPanel.add(this.rightSouthDisconnectButton);
        this.rightSouthPanel.add(this.rightSouthExitButton);
    //Popolo il pannello principale Destra
        this.rightMainPanel.add(this.rightNordPanel, BorderLayout.NORTH);
        this.rightMainPanel.add(this.rightCenterPanel, BorderLayout.CENTER);
        this.rightMainPanel.add(this.rightSouthPanel, BorderLayout.SOUTH);
    
        Container mainContentPane = this.getContentPane();
        JPanel mainContent = new JPanel();
        mainContentPane.add(mainContent);
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.X_AXIS));
        mainContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainContent.add(this.leftMainPanel);
        mainContent.add(this.rightMainPanel);
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
