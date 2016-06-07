package data;

import chatUtils.data.ChatMessage;
import chatUtils.data.Consts;
import chatUtils.data.UserData;
import gui.MainFrame;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ConnectionManager;
import utils.data.CalendarUtils;

/**
 *
 * @author Edoardo Zanoni
 */
public class Reader implements Runnable{
    
    private final MainFrame frame;
    private final ConnectionManager connectionManager;

    public Reader(MainFrame frame, ConnectionManager connectionManager) {
        
        this.frame = frame;
        this.connectionManager = connectionManager;
    }

    @Override
    public void run() {
        
        Object object;
        while(true) {
            
            object = this.connectionManager.pull();
            if (object instanceof ChatMessage) {
                
                this.frame.getLeftCenterLogTextField().append(((ChatMessage) object).toString());
            } else if (object instanceof UserData) {
                
                this.frame.getRightCenterUsersTextField().append(((UserData)object).getUserName()+"\n");
                try {
                    this.frame.getLeftCenterLogTextField().append(
                            CalendarUtils.dateToString(LocalDateTime.now(), Consts.dateFormat)
                                    + " [" + ((UserData) object).getUserName()
                                    + " si è unito alla chat]");
                } catch (ParseException ex) {
                    Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
