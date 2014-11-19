/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.resources;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import server.share.View;
/**
 *
 * @author 21187498
 */
public class ResourceMenuBar extends JMenuBar {
    //The static commands ID
    View view;
   public final   String START_SERVER_COMMAND      ="Start server";
   public  final  String CLOSE_SERVER_COMMAND    = "Close connections";
   public final   String EXIT_APPLICATION_COMMAND ="Exit Application";
 
   //fonts
  private  Font menuItemFont;   
   //The Menu items
  private  JMenu mnuFile; 
  JMenuItem iMStartServer;
  JMenuItem iMExitWindow;
  
   
    public ResourceMenuBar(View view)
     {
         super(); 
         this.view =view;
        setBorder(BorderFactory.createLineBorder( new Color(245,245,245)));
        setBackground(new Color(245,245,245));
        initMenuItems();
       
        
     }
    
    /**
     * The method will add and initialised the menu item 
     */
    private void initMenuItems()
    {
       menuItemFont = new Font(Font.SANS_SERIF,Font.PLAIN,14);  
       iMStartServer = new JMenuItem(START_SERVER_COMMAND);
       iMStartServer.setFont(menuItemFont);
     
       //create a keystroke  for the start server
       KeyStroke f1_start = KeyStroke.getKeyStroke(KeyEvent.VK_F1,InputEvent.ALT_DOWN_MASK,true);      
       iMStartServer.setAccelerator(f1_start);//set the shortcut        
       //add it to the menu file
       mnuFile.add(iMStartServer);
       //add a seperator
       mnuFile.addSeparator();
       //add the exist command
       iMExitWindow = new JMenuItem(EXIT_APPLICATION_COMMAND,KeyEvent.VK_E);
       iMExitWindow.setFont(menuItemFont);
       KeyStroke altF4 = KeyStroke.getKeyStroke(KeyEvent.VK_F4,InputEvent.ALT_DOWN_MASK,true);
       iMExitWindow.setAccelerator(altF4);      
       //add it to the file menu      
       mnuFile.add(iMExitWindow);
       
       
       //add the file menu to the menubar
       this.add(mnuFile);
         
    }
  
    //the method will set the event
 public  void setActionListenerHandler(ActionListener eventListener)
     {
          iMStartServer.addActionListener(eventListener);
          iMExitWindow.addActionListener(eventListener);
    }
    
    
  
    
}
