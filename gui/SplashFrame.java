package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Edoardo Zanoni
 */
public class SplashFrame extends JFrame {

    private static final String TITLE = "SpChat";
        
//Pannello Nord
    private static JPanel nordPanel = new JPanel();
    private static JLabel nordLabel = new JLabel("Inserire i dati del server:");
//Pannello Centrale
    private static JPanel centerPanel = new JPanel(new GridLayout(8, 1));
    private static JLabel centerNicknameLabel = new JLabel("Nickname");
    private static JTextField centerNicknameTextField = new JTextField(15);
    private static JLabel centerChatNameLabel = new JLabel("Nome chat");
    private static JTextField centerChatNameTextField = new JTextField(15);
    private static JLabel centerAddressLabel = new JLabel("Indirizzo IP");
    private static JTextField centerAddressTextField = new JTextField("127.0.0.1", 15);
    private static JLabel centerPortLabel = new JLabel("Porta");
    private static JTextField centerPortTextField = new JTextField("1337", 5);
//Pannello Sud
    private static JPanel southPanel = new JPanel(new GridLayout(1, 2));
    private static JButton southConnectButton = new JButton("CONNETTI");
    private static JButton southExitButton = new JButton("ESCI");

    public SplashFrame() {

        super(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    //Popolo il pannello Nord
        this.nordPanel.add(this.nordLabel);
    //Popolo il pannello Centrale
    this.centerNicknameTextField.setMaximumSize(this.getSize());
        this.centerPanel.add(this.centerNicknameLabel);
        this.centerPanel.add(this.centerNicknameTextField);
        this.centerPanel.add(this.centerChatNameLabel);
        this.centerPanel.add(this.centerChatNameTextField);
        this.centerPanel.add(this.centerAddressLabel);
        this.centerPanel.add(this.centerAddressTextField);
        this.centerPanel.add(this.centerPortLabel);
        this.centerPanel.add(this.centerPortTextField);
    //Popolo il pannello Sud
        this.southPanel.add(this.southConnectButton);
        this.southPanel.add(this.southExitButton);
        
    
        
    //Creo l'interfaccia
        Container splashContentPane = this.getContentPane();
        JPanel splashContent = new JPanel();
        splashContentPane.add(splashContent);
        splashContent.setLayout(new BorderLayout());
        splashContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        splashContent.add(this.nordPanel, BorderLayout.NORTH);
        splashContent.add(this.centerPanel, BorderLayout.CENTER);
        splashContent.add(this.southPanel, BorderLayout.SOUTH);
        
        
    //Imposto i listener
        CustomActionListener mouseListener = new CustomActionListener(this);
        this.southConnectButton.setName("CONNECT_SPLASH_BUTTON");
        this.southConnectButton.addActionListener(mouseListener);
        
        this.southExitButton.setName("EXIT_SPLASH_BUTTON");
        this.southExitButton.addActionListener(mouseListener);
        //Imposto il bottone connect come bottone di default alla pressione di enter
        this.getRootPane().setDefaultButton(southConnectButton);
        
    //Preparo l'interfaccia per essere mostrata
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public JTextField getNicknameTextField() {
        
        return this.centerNicknameTextField;
    }
    
    public JTextField getChatNameTextField() {
        
        return this.centerChatNameTextField;
    }
    
    public JTextField getAddressTextField() {
        
        return this.centerAddressTextField;
    }
    
    public JTextField getPortTextField() {
        
        return this.centerPortTextField;
    }
}
