/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controllers;
import client.models.ClientModel;
import client.views.ClientView;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import server.share.IObserver;
import server.share.Controller;
import server.share.ISubject;

/**
 *
 * @author 21187498
 */
public class ClientController extends IObserver implements Controller {
     private   ClientModel model;
     private   ClientView view;
     private ProxySettingController proxyController ;
     private SiteController siteController;

 static public  boolean IsConnected =false;
 
   public ClientController(ClientModel aModel, ClientView aView)
     {
       this.model = aModel;
       this.view = aView;
       //the attachment of the controller
       this.model.attach(this);
       this.view.attach(this);
       
     }
   
    @Override
    public void launch() 
    {
      
        this.view.pack();
        this.view.setSize(new Dimension(800,this.view.getHeight()));
        this.view.center();
        this.view.setVisible(true);
       
    }

    @Override
    public ISubject getModel() {
        return this.model;
       }

    @Override
    public ISubject getView() {
        return this.view;
     }

   final public  void xhsTestConnection() {
        
        
      proxyController =  ProxySettingController.getInstance();
      boolean status=  proxyController.xhsTestConnection();
      if(status)
      {
         JOptionPane.showMessageDialog(view,proxyController.getErrorMessage());
         this.view.repaint();
         return;
      }
      
       JOptionPane.showMessageDialog(view,proxyController.getErrorMessage(),"Error",JOptionPane.ERROR_MESSAGE);
      
    }


  final public  void xhsExitApplication() {
       System.exit(0);
    }
   final public   void xhsOpenNewForm() {
      
      siteController = SiteController.GetInstance();
      siteController.launch();
    }
  
 final public   void xhsProxySettingOpenView() {
      ProxySettingController proxySettingController = ProxySettingController.getInstance();
      proxySettingController.launch();
    }  

  
    
}
