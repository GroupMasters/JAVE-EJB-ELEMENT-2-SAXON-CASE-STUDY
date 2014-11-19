/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.views;

import client.controllers.SiteController;
import client.models.SiteModel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import server.share.IObserver;
import server.share.ISubject;
import server.share.RMIConstants;
import server.share.SiteInfo;
import server.share.View;

/**
 *
 * @author 21187498
 */
public class SiteView extends View implements ISubject{
     private static final String CMD_CREATE_SITE = "Create";
     private static final String CMD_CREATE_SITE_RUNNING= "Cancel";
      
    //defined the form object for the sites
   private JPanel pnlTable;
   private JTable tblSites;
   private JScrollPane jsclSite;
   private  JPanel pnlForm;
    private JTextField txtName;
    private JTextField txtSiteId;
    private JComboBox<String> cboRegion;
    private JTextField txtFlag;
    //labels
    private JLabel lblName;
    private JLabel lblSiteId;
    private JLabel lblRegion;
    private JLabel lblFlags;
    //buttons
    private JButton btnCreateSite;
    SiteController controller;
    
     // The constuctor
    public SiteView(String title) 
    {
        super();
        this.setTitle(title);        
        this.setModal(true);
        initGui();
        this.pack();
        

    }

    private void initGui() {
        //initialised the form objects
        txtName =   new JTextField(30);
        txtSiteId = new JTextField(30);
        cboRegion = new JComboBox<>();
        txtFlag =      new JTextField(30);
        //labels
        lblName =   new JLabel("Name:");
        lblSiteId = new JLabel("Identity Number:");
        lblRegion = new JLabel("Region:");
        lblFlags =  new JLabel("Rank:");
        //buttons

        btnCreateSite = new JButton(CMD_CREATE_SITE);
        
        
        //add the controls to the panel
        
        this.pnlForm = new JPanel(new GridBagLayout());
         this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        //add to the first row
       
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx= 0;
        gc.gridy =0;
        gc.insets = new Insets(4,4,4,4);
        this.pnlForm.add(this.lblSiteId,gc);
        
        
  
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx= 1;
        gc.gridy =0;
          gc.gridwidth=2;
        this.pnlForm.add(this.txtSiteId,gc);
        
        
        
         //add to the first row
        gc.anchor= GridBagConstraints.NORTHWEST;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx= 0;
        gc.gridy =1;
        this.pnlForm.add(this.lblName,gc);
        
        
        
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx= 1;
        gc.gridy =1;
          gc.gridwidth=2;
        this.pnlForm.add(this.txtName,gc);
        
        
          //add to the first row
      
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx= 0;
        gc.gridy =2;
        this.pnlForm.add(this.lblRegion,gc);
        
        
      
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx= 1;
        gc.gridy =2;
          gc.gridwidth=2;
           this.cboRegion.addItem("London");
           this.cboRegion.addItem("South West");
           this.cboRegion.addItem("Midlands");
           this.cboRegion.addItem("South East");
           this.cboRegion.addItem("North East");
           this.cboRegion.addItem("North West");
        this.pnlForm.add(this.cboRegion,gc);
        
        
            //add to the first row
   
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx= 0;
        gc.gridy =3;
        this.pnlForm.add(this.lblFlags,gc);
        
        
        gc.anchor= GridBagConstraints.NORTHWEST;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx= 1;
        gc.gridy =3;
        gc.gridwidth = 2;
        this.txtFlag.setEditable(false);
       
        this.txtFlag.setText(RMIConstants.BRONZE);
        this.pnlForm.add(this.txtFlag,gc);
        
        
        
        //the buttons pnl
         this.pnlTable= new JPanel();
         this.tblSites = new JTable();
         this.jsclSite = new JScrollPane(this.tblSites);
         this.pnlTable.add(this.jsclSite);
    
        gc.anchor= GridBagConstraints.NORTHWEST;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx= 0;
        gc.gridy =5; 
        gc.gridwidth=3;
        this.pnlForm.add(this.pnlTable,gc);
        
        //add the site table to
        
        gc.anchor= GridBagConstraints.NORTHWEST;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx= 1;
        gc.gridy =4; 
        gc.gridwidth=1;
        this.btnCreateSite.addActionListener(new EventHandler(this));
        this.pnlForm.add(btnCreateSite,gc);
        
        //add the controls to the window       
       
        GridBagConstraints mainGC= new GridBagConstraints();
        mainGC.anchor= GridBagConstraints.NORTHWEST;
        mainGC.fill = GridBagConstraints.HORIZONTAL;
        mainGC.gridx= 0;
        mainGC.gridy =0;
        this.add(this.pnlForm,mainGC);
        
    }

    @Override
    public void attach(IObserver observer) {
        controller = (SiteController) observer;
         SiteModel aModel =(SiteModel) controller.getModel();
         this.tblSites.setModel(aModel.getTableModel());
        
    }

    

      public SiteInfo getSiteInfo() {
      SiteInfo info = new SiteInfo();
      info.setId(this.txtSiteId.getText());
      info.setName(this.txtName.getText());
      info.setRegion(this.cboRegion.getSelectedItem().toString());
      info.setFlag(this.txtFlag.getText());
     //JOptionPane.showMessageDialog(null,txtFlag.getText());
      
      return info;
    }

    public void setChangeCreateButtonStatus(boolean b) {
        if(b){
            this.btnCreateSite.setText(CMD_CREATE_SITE_RUNNING);
       }else
       {
          this.btnCreateSite.setText(CMD_CREATE_SITE);  
       }
    }
    // the  event handler

    public void errorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this,errorMessage,"Error",JOptionPane.ERROR_MESSAGE);
    }

    public void successMessage(String msg) {
      JOptionPane.showMessageDialog(this,msg,"Message",JOptionPane.INFORMATION_MESSAGE);
    }

    public void changeCreateStatus(int i) {
       if(i==1)
       {
           this.btnCreateSite.setText(CMD_CREATE_SITE);
       }
       else
       {
          this.btnCreateSite.setText( CMD_CREATE_SITE_RUNNING);  
          
       }
    }
    
    private class EventHandler implements ActionListener
    {
       private SiteView view;
     
       EventHandler(SiteView aView)
       {
           this.view = aView;
       }
        @Override
        public void actionPerformed(ActionEvent e) {
          if(e.getActionCommand() == CMD_CREATE_SITE)
          {
              this.view.controller.xhsCreateSite();
          }
              else if(e.getActionCommand().equalsIgnoreCase(CMD_CREATE_SITE_RUNNING))
         {
               this.view.controller.xhsCancelCreateSite();
         }
         
        }
        
    }

}
