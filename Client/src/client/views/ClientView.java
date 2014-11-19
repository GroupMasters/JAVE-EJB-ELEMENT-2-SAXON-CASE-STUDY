/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.views;

import client.controllers.ClientController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import server.share.IObserver;
import server.share.ISubject;
import server.share.SiteInfo;
import server.share.View;


/**
 *
 * @author 21187498
 */
public class ClientView extends View implements ISubject{
      final private static String CMD_TEST_CONNECTION = "Test connection";
    final private  static String EXIT_APPLICATION_CMD ="Exit Application";
   private static java.net.URL ICON_EXIT_APPLICATION =null;
   private static java.net.URL  ICON_FILE_MENU =null;
   final private static String  CMD_PROXY_SETTINGS ="Proxy settings ..."; 
   //menu commands id
 final  private  static  String NEW_SITE_CMD ="New";
 private static URL ICON_NEW_SITE=null, APP_ICON=null;
 private static URL ICON_TEST_NETWORK=null;
  static private URL ICON_NETWORK_SETTING=null, ICON_SITE_SETTING =null,ICON_PROXY_SETTING=null;
 
    
  private ClientController controller;
  
   private JToolBar toolbar;
    private JMenuBar menubar;
     JMenuItem iMTestConnection ;
   
    private JPanel content;
    private GridBagLayout contentLayout;
   JMenu mSiteManager;
  
  
     JMenuItem iMNewSite;
      
      JMenu mNetwork;
         JMenuItem iMProxySettings;
         
         
  public  ClientView(String title)
    {
       //set the window default looks
       ICON_EXIT_APPLICATION = this.getClass().getResource("../resources/close_app.png");
       ICON_NEW_SITE= this.getClass().getResource("../resources/new_site.png");
      ICON_NETWORK_SETTING = this.getClass().getResource("../resources/network_settings.png");
      ICON_PROXY_SETTING =this.getClass().getResource("../resources/proxy_settings.png");
      ICON_SITE_SETTING=this.getClass().getResource("../resources/site_icon.png");
      ICON_FILE_MENU = this.getClass().getResource("../resources/file_icon_menu.png");
      ICON_TEST_NETWORK = this.getClass().getResource("../resources/test_network.png");
      APP_ICON =this.getClass().getResource("../resources/app_icon.png"); 
        this.initGui();
        this.setTitle(title);    
        
    }
    @Override
    public void attach(IObserver observer) {
        this.controller = (ClientController)observer;
    }
    
    
    


    private void initGui()
    {
        
        this.setLayout(new BorderLayout());
        this.contentLayout = new GridBagLayout();
        this.content = new JPanel();
        this.initMenuItems();
        
        this.setJMenuBar(menubar);
        this.toolbar = new JToolBar();
        this.initToolBarGui();
      
        this.initStatus();
        
        this.add(this.toolbar,BorderLayout.PAGE_START);
        this.add(content,BorderLayout.CENTER);        
        //now we are going to initialised the form controls
        this.addWindowListener(new  ClientView.CustomizeWindowAdaptor(this));
        this.initContentGui();
        //set the application incon
        Image img = (View.getPngTransparentImageIcon(new ImageIcon(APP_ICON))).getImage();
        this.setIconImage(img);
       
        
    }
    
 


 

    private void initMenuItems() {
        try
        {
            //This will get the system look for the UI
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
        }
       catch(Exception err)
       {
           
       }
      this.menubar = new JMenuBar();
      this.menubar.setBorder(BorderFactory.createLineBorder( new Color(245,245,245)));
       this.menubar.setBackground(new Color(245,245,245));
       
      Font menuFont = new Font(Font.SANS_SERIF,Font.PLAIN,14);
      JMenu mFile = new JMenu("File");
      mFile.setIcon(View.getPngTransparentImageIcon(new ImageIcon(ICON_FILE_MENU)));
      mFile.setMnemonic(KeyEvent.VK_F);
      mFile.setFont(menuFont);
      
          
       //add exist application
       
       JMenuItem  iMExitApplication = new JMenuItem(EXIT_APPLICATION_CMD,KeyEvent.VK_E);
        KeyStroke altQ = KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_DOWN_MASK, true);
       iMExitApplication.setAccelerator(altQ);
       iMExitApplication.addActionListener( new ClientView.EventHandler(this));
       iMExitApplication.setFont(menuFont);
       iMExitApplication.setIcon(View.getPngTransparentImageIcon(new ImageIcon(ICON_EXIT_APPLICATION)));
       mFile.add(iMExitApplication );
       
      
      menubar.add(mFile);
      
      ///menu for the site managements command
      
   
       mSiteManager = new JMenu("Site");
       mSiteManager.setFont(menuFont);
       mSiteManager.setMnemonic(KeyEvent.VK_S);
       mSiteManager.setIcon(View.getPngTransparentImageIcon(new ImageIcon(ICON_SITE_SETTING)));
       
       //Jmenuitem
     
       iMNewSite =  new JMenuItem(NEW_SITE_CMD,KeyEvent.VK_N);
       iMNewSite.setFont(menuFont);
       KeyStroke altN = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK, true);
       iMNewSite.setAccelerator(altN);  
       iMNewSite.addActionListener( new ClientView.EventHandler(this));
       iMNewSite.setIcon(View.getPngTransparentImageIcon(new ImageIcon(ICON_NEW_SITE)));
       //Add the
       mSiteManager.add(iMNewSite);
       menubar.add(mSiteManager);
       
       //add the site settings nmenu
       
     
       mNetwork = new JMenu("Network");
       mNetwork.setFont(menuFont);
       mNetwork.setIcon(View.getPngTransparentImageIcon(new ImageIcon(ICON_NETWORK_SETTING)));
       mNetwork.setMnemonic(KeyEvent.VK_S);
       
       //add the menu
       
       iMProxySettings= new JMenuItem(CMD_PROXY_SETTINGS,KeyEvent.VK_P);
       iMProxySettings.setFont(menuFont);
       iMProxySettings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_DOWN_MASK, true));
       iMProxySettings.addActionListener(new ClientView.EventHandler(this));
       iMProxySettings.setIcon(View.getPngTransparentImageIcon(new ImageIcon(ICON_PROXY_SETTING)));
       this.mNetwork.add(iMProxySettings);
       
       
       menubar.add(this.mNetwork);
      
      
       iMTestConnection  = new  JMenuItem(CMD_TEST_CONNECTION,KeyEvent.VK_T);
       iMTestConnection.setFont(menuFont);
       KeyStroke altM = KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_DOWN_MASK, true);
       iMTestConnection.setAccelerator(altM);
       iMTestConnection.setIcon(View.getPngTransparentImageIcon(new ImageIcon(ICON_TEST_NETWORK)));
       iMTestConnection.addActionListener( new ClientView.EventHandler(this) );    
       
        this.mNetwork.add(this.iMTestConnection);
      
      
      this.setJMenuBar(menubar);
    }

    private void initToolBarGui() {
      
    }

    private void initStatus() {
       
    }

    private void initContentGui() {
         this.content.setLayout(contentLayout);
        GridBagConstraints gc = new GridBagConstraints();   
     
    }

    public SiteInfo getSiteInfo() {
      SiteInfo info = null;
      
      return info;
    }
    
   
    private class CustomizeWindowAdaptor extends WindowAdapter
    {
          ClientView window;
          CustomizeWindowAdaptor(ClientView window)
         {
             this.window = window;
         }
         
     public  void windowClosing(WindowEvent evt)
         {
           this.window.controller.xhsExitApplication();
         }
    }
    
    
  /**
   * The inner class handle all the menu item command actions
   */
    
    
    private class EventHandler  implements ActionListener{
       ClientView parent;
       EventHandler(ClientView view)
       {
           this.parent = view;
       }

        @Override
        public void actionPerformed(ActionEvent ae) {
           
            if(CMD_TEST_CONNECTION.equalsIgnoreCase(ae.getActionCommand()))
            {
                this.parent.controller.xhsTestConnection();
            }
            else if(EXIT_APPLICATION_CMD.equalsIgnoreCase(ae.getActionCommand()))
            {
                this.parent.controller.xhsExitApplication();
            }
            else if (ae.getActionCommand().equalsIgnoreCase(NEW_SITE_CMD))
            {
                this.parent.controller.xhsOpenNewForm();
            }
            else if (ae.getActionCommand().equalsIgnoreCase(CMD_PROXY_SETTINGS))
            {
                this.parent.controller.xhsProxySettingOpenView();
            }
           
        }
       
       
    }
    
    
    // inner class to handler view commands
    

    //override window mains
    
   @Override
    public void paint(Graphics g)
    {
       super.paint(g);
       if(ClientController.IsConnected)
       {
          //do notthing for now
       }else
       this.iMTestConnection.setText(CMD_TEST_CONNECTION); 
    }
 
}
