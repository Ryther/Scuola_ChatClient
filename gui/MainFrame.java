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

    private static final String TITLE = "SpChat";
    
    private CustomActionListener actionListener;
    
//Dati utente
    private static String nickname;
    private static String chatName;
    
//Pannello principale
    private static JPanel leftMainPanel = new JPanel(new BorderLayout());
    private static JPanel rightMainPanel = new JPanel(new BorderLayout());
//Pannello Sinistra/Nord
    private static JPanel leftNordPanel = new JPanel();
    private static JLabel leftNordLabel = new JLabel();
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

    public MainFrame(String nickname, String ChatName) {
        
        this(nickname, chatName, new CustomActionListener());
    }
    public MainFrame(String nickname, String chatName, CustomActionListener actionListener) {

        super(TITLE);
        
        MainFrame.nickname = nickname;
        MainFrame.chatName = chatName;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    //Popolo il pannello Sinistra/Nord
        StringBuilder stringBuilder = new StringBuilder(MainFrame.nickname).append(" nella chat ").append(MainFrame.chatName);
        MainFrame.leftNordLabel.setText(stringBuilder.toString());
        stringBuilder = null;
        MainFrame.leftNordPanel.add(MainFrame.leftNordLabel);
    //Popolo il pannello Sinistra/Centrale
        MainFrame.leftCenterPanel.add(MainFrame.leftCenterLogTextField);
        MainFrame.leftCenterLogTextField.setEditable(false);
        MainFrame.leftCenterLogTextField.setPreferredSize(new Dimension(300, 300));
    //Popolo il pannello Sinistra/Sud
        MainFrame.leftSouthPanel.add(MainFrame.leftSouthMessageTextField);
        MainFrame.leftSouthPanel.add(MainFrame.leftSouthSendButton);
    //Popolo il pannello principale Sinistra
        MainFrame.leftMainPanel.add(MainFrame.leftNordPanel, BorderLayout.NORTH);
        MainFrame.leftMainPanel.add(MainFrame.leftCenterPanel, BorderLayout.CENTER);
        MainFrame.leftMainPanel.add(MainFrame.leftSouthPanel, BorderLayout.SOUTH);
        
    //Popolo il pannello Destra/Nord    
        MainFrame.rightNordPanel.add(rightNordLabel);
    //Popolo il pannello Destra/Centrale
        MainFrame.rightCenterPanel.add(rightCenterUsersTextField);
    //Popolo il pannello Destra/Sud
        MainFrame.rightSouthPanel.add(MainFrame.rightSouthDisconnectButton);
        this.rightSouthPanel.add(this.rightSouthExitButton);
    //Popolo il pannello principale Destra
        MainFrame.rightMainPanel.add(MainFrame.rightNordPanel, BorderLayout.NORTH);
        MainFrame.rightMainPanel.add(MainFrame.rightCenterPanel, BorderLayout.CENTER);
        MainFrame.rightMainPanel.add(MainFrame.rightSouthPanel, BorderLayout.SOUTH);
    
    //Creo l'interfaccia
        Container mainContentPane = this.getContentPane();
        JPanel mainContent = new JPanel();
        mainContentPane.add(mainContent);
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.X_AXIS));
        mainContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainContent.add(MainFrame.leftMainPanel);
        mainContent.add(MainFrame.rightMainPanel);
        
    //Imposto i listener
        this.actionListener = actionListener;
        this.actionListener.setFrame(this);
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static JTextField getLeftSouthMessageTextField() {
        
        return leftSouthMessageTextField;
    }

    public static JTextArea getLeftCenterLogTextField() {
        
        return leftCenterLogTextField;
    }

    public static JTextArea getRightCenterUsersTextField() {
        
        return rightCenterUsersTextField;
    }
}
