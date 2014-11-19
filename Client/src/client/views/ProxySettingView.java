/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.views;

import client.controllers.ProxySettingController;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import server.share.IObserver;
import server.share.ISubject;
import server.share.NetworkInfo;
import server.share.RMIConstants;
import server.share.View;

/**
 *
 * @author 21187498
 */
public class ProxySettingView extends View implements ISubject {
     //the controller
 private ProxySettingController controller;
 
  
    private static String CMD_SAVE_SETTINGS="Save Settings";
    private static String CMD_TEST_CONNECTION="Test Connection";
   private static String CMD_CHECKED_STATUS_ON ="Enabled";
    private static String CMD_CHECKED_STATUS_OFF ="Disabled"; 
    
    private static boolean ALLOW_POLICY_FILE_STATUS  =false;
    //The fields
    
    private JPanel pnlProxySetting ;
    private JTextField txtHostAddressIP ;
    private JTextField txtPort;
    private JTextField txtPolicyFile ;
    private JCheckBox chkPolicySecuriy ;    
  private  JLabel lblHostname;
  private  JLabel lblPort;
  private  JLabel lblSecurityPolicy;
  private JLabel lblPolicyFile ;
  
  
  //create the buttons
  
  private JButton btnTestConnection;
  private JButton btnSaveSettings;
  
    //net information
    NetworkInfo info;
 
 public ProxySettingView(String title)
 {
    
      this.setTitle(title);
        this.setModal(true);
        initGUI();
        info= new NetworkInfo();
       info.setHost(RMIConstants.INTERNET_HOST);
       info.setPort(RMIConstants.INTERNET_PORT);
       this.txtHostAddressIP.setText(info.getHost());
       this.txtPort.setText(String.valueOf(info.getPort()));
 }
    @Override
    public void attach(IObserver observer) {
        this.controller= (ProxySettingController)observer;
        }
      private void initGUI()
    {
        // setting and initialising the private controller fields
        
        this.pnlProxySetting= new JPanel(new GridBagLayout());
        this.pnlProxySetting.setBorder(BorderFactory.createEtchedBorder(1));
        GridBagConstraints gc = new  GridBagConstraints();
        
        
        //controls initialising
        this.lblHostname = new JLabel("Hostname or IP");
        this.txtHostAddressIP = new JTextField(30);
        this.lblPort = new JLabel("Port");
        this.txtPort = new JTextField(10);
        lblPolicyFile = new JLabel("Upload policy");
        txtPolicyFile = new JTextField(30);
        //the combos
        this.lblSecurityPolicy = new JLabel("Network Security ");
        this.chkPolicySecuriy = new  JCheckBox("Disabled");
        
        this.btnSaveSettings= new JButton(ProxySettingView.CMD_SAVE_SETTINGS);
        this.btnTestConnection = new JButton(ProxySettingView.CMD_TEST_CONNECTION);
        
        
        
        /// lay the controls
        
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill =  GridBagConstraints.HORIZONTAL;
        gc.gridx=0;
        gc.gridy=0;
        gc.insets = new Insets(4,4,4,4);
        this.pnlProxySetting.add(this.lblHostname,gc);
        
        
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill =  GridBagConstraints.HORIZONTAL;
        gc.gridx=1;
        gc.gridy=0;
        gc.gridwidth =2;
        this.pnlProxySetting.add(this.txtHostAddressIP,gc);
        
        
        
         gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill =  GridBagConstraints.HORIZONTAL;
        gc.gridx=0;
        gc.gridy=1;
        
        this.pnlProxySetting.add(this.lblPort,gc);
        
        
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill =  GridBagConstraints.HORIZONTAL;
        gc.gridx=1;
        gc.gridy=1;
        gc.gridwidth =1;
        this.pnlProxySetting.add(this.txtPort,gc);
        
        
        //add the policy pnl
        JPanel pnlPolicy= new JPanel();
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill =  GridBagConstraints.HORIZONTAL;
        gc.gridx=2;
        gc.gridy=1;
        gc.gridwidth =1;    
        pnlPolicy.add(this.lblSecurityPolicy);
        pnlPolicy.add(this.chkPolicySecuriy);
        this.chkPolicySecuriy.addActionListener(new EventHandler(this));
        this.pnlProxySetting.add(pnlPolicy,gc);
         
        //Show the policy upload field
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill =  GridBagConstraints.HORIZONTAL;
        gc.gridx=0;
        gc.gridy=2;
        gc.gridwidth =1;   
      
        this.pnlProxySetting.add(this.lblPolicyFile,gc);
        
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill =  GridBagConstraints.HORIZONTAL;
        gc.gridx=1;
        gc.gridy=2;
        gc.gridwidth =2;        
        this.pnlProxySetting.add(this.txtPolicyFile,gc);
        
        // the button 
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill =  GridBagConstraints.HORIZONTAL;
        gc.gridx=1;
        gc.gridy=3;
        gc.gridwidth =1;    
        this.btnSaveSettings.addActionListener(new EventHandler(this));
        this.pnlProxySetting.add(this.btnSaveSettings,gc);
        
         gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill =  GridBagConstraints.HORIZONTAL;
        gc.gridx=2;
        gc.gridy=3;
        gc.gridwidth =1;  
        this.btnTestConnection.addActionListener(new EventHandler(this));
        this.pnlProxySetting.add(this.btnTestConnection,gc);
        
      
       //set the main pnl on the window
        this.setLayout(new GridBagLayout());
         gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill =  GridBagConstraints.HORIZONTAL;
        gc.gridx=0;
        gc.gridy=0;
        gc.gridwidth =1;  
        gc.insets = new Insets(14,4,4,5);
        this.showPolicyField(ALLOW_POLICY_FILE_STATUS);
        this.add(this.pnlProxySetting,gc);
        
        
    }

  
    public NetworkInfo getNetworkInfo() 
    {
        info = new NetworkInfo();
         info.setHost(this.txtHostAddressIP.getText());
         int port = Integer.parseInt(this.txtPort.getText());         
         info.setPort(port);  
         if(!info.validate())
             return null;
         return info;     
    }

    private void showPolicyField(boolean status) {
           
           this.lblPolicyFile.setVisible(status);
           this.txtPolicyFile.setVisible(status);
           this.repaint();
           this.pack();        
         }
    
    //EventHAndler
    
    private class EventHandler implements ActionListener
    {
       ProxySettingView view; 
        //Constructor
        EventHandler(ProxySettingView aview)
        {
            this.view = aview;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            
            if(e.getActionCommand().equalsIgnoreCase(ProxySettingView.CMD_TEST_CONNECTION))
            {
                this.view.controller.xhsTestConnection();
                JOptionPane.showMessageDialog(this.view,this.view.controller.getErrorMessage());
            }
            else if(e.getActionCommand().equalsIgnoreCase(CMD_CHECKED_STATUS_ON) || e.getActionCommand().equalsIgnoreCase(CMD_CHECKED_STATUS_OFF))
            {
              
              if( this.view.chkPolicySecuriy.isSelected())
              {
                 this.view.chkPolicySecuriy.setText(CMD_CHECKED_STATUS_ON);  
                 ALLOW_POLICY_FILE_STATUS = true;
              }
              else
              {
                   this.view.chkPolicySecuriy.setText(CMD_CHECKED_STATUS_OFF);
                   ALLOW_POLICY_FILE_STATUS =false;
              }
              this.view.showPolicyField(ALLOW_POLICY_FILE_STATUS);
             
            }
        }
        
    }
}
