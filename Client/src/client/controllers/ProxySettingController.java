/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controllers;

import client.models.ProxySettingModel;
import client.views.ProxySettingView;
import server.share.Controller;
import server.share.IObserver;
import server.share.ISubject;
import server.share.NetworkInfo;
import server.share.RMIConstants;

/**
 *
 * @author 21187498
 */
public class ProxySettingController extends IObserver implements Controller {
    private final  ProxySettingModel model;
    private final  ProxySettingView view;
    static private ProxySettingController instance = null;
    private String Constants;
    private String error;
   // the static method get the current instance(Singleton pattern)
   public static ProxySettingController getInstance()
    {
         if(instance ==null)
             instance = new ProxySettingController(new ProxySettingModel(), new  ProxySettingView("Proxy Setting"));
         return instance;
    } 
    
   
    private ProxySettingController(ProxySettingModel aModel,ProxySettingView aView)
    {
        this.model =aModel;
        this.view =aView;
        this.model.attach(this);
        this.view.attach(this);
        
    }

    
   @Override
    public void launch()
    {
        // this will set proxy settings window      
         this.view.center();
         this.view.pack();
         this.view.setResizable(false);
        this.view.setVisible(true);
    }
public String getErrorMessage()
{
    return error;
}

    @Override
    public ISubject getModel() {
        return this.model;
          }

    @Override
    public ISubject getView() {
        
        return this.view;
        }



   public boolean xhsTestConnection()
    {    
     
     NetworkInfo info;
     info=  this.view.getNetworkInfo();
     
     if(info==null){
          error ="Provid connection information please!";
         return false;
     }
     info.setHost(RMIConstants.INTERNET_HOST);
     info.setPort(RMIConstants.INTERNET_PORT);
      boolean status_con; 
      
     status_con = this.model.testConnection(info.getHost(), info.getPort());
     
    if(status_con )
        {
           error = "Test Successful";
            return  true;
        }
         error ="Check your internet connections or contact administrator";
          return false;
    }
   
  
    
}
