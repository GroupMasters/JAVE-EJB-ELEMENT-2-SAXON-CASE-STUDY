/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.models;

import client.controllers.ProxySettingController;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import server.controllers.ServerController;
import server.share.IConnection;
import server.share.IObserver;
import server.share.ISubject;
import server.share.RMIConstants;

/**
 *
 * @author 21187498
 */
public class ProxySettingModel implements ISubject {
 private ProxySettingController controller;
 
 public ProxySettingModel()
 {
     controller=null;
 }
  public boolean testConnection(String host, int port)
    {
        boolean isokay=false;
        if(System.getSecurityManager() ==null)
        {
         if (ServerController.SSLStatus) {
             System.setSecurityManager(new SecurityManager());
         }
        }
        try
        {
            Registry registry = LocateRegistry.getRegistry(host, port);           
            IConnection con;
            con = (IConnection) registry.lookup(RMIConstants.ICONNECTION);
            String localIp = "Ip";
            if(con.connect(localIp))
            {
                isokay=true;
            }
         
            
        }
        catch(RemoteException | NotBoundException err)
        {
          return isokay;   
        }
         return isokay;
      
    }
    @Override
    public void attach(IObserver observer) {
        this.controller = (ProxySettingController)controller;
        }
    
}
