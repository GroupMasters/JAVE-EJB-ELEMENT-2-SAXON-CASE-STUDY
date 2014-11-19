/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.models;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import server.controllers.ServerController;
import server.share.*;


/**
 *
 * @author 21187498
 */
public class ServerModel implements ISubject {
    private String errormessage;
   private Registry registry;
    @Override
    public void attach(IObserver observer) {
  
    
    }
     public boolean startServer()
    {
        
        boolean isOkay=false  ;
        //check if the security is required if required set it up
        if(System.getSecurityManager() == null && ServerController.SSLStatus)
        {
          System.setSecurityManager(new SecurityManager());
        }
        
        try
        {
              EntrySite entrySite = new EntrySite();
             IConnection connection = new ConnectionImp();
             
             NetworkInfo info = new NetworkInfo();
             info.setHost(RMIConstants.INTERNET_HOST);
             info.setPort(RMIConstants.INTERNET_PORT);
            // we have to get a strub from the object
            IEntrySite stub = (IEntrySite) UnicastRemoteObject.exportObject(entrySite,info.getPort());
            IConnection stubConnection =(IConnection) UnicastRemoteObject.exportObject(connection,info.getPort());
            //now we have to start the register
            registry=  LocateRegistry.createRegistry(info.getPort());
           
            //get the registry object          
            //register the stub object to the server , so that client application can use it
            //bind the object to the url location 
            registry.rebind(RMIConstants.ICONNECTION, stubConnection);
            registry.rebind(RMIConstants.IEntrySite,stub);  
            ServerController.IsServerRunning = true;
           isOkay=true ;
          
        }
        catch(Exception err)
        {
          this.errormessage =  err.getMessage();
           return isOkay;  
        }
        return isOkay;  
    }

  public String getErrorMessage() {
       return errormessage;
    }

   //this method will disconnected the RMI server object
  public   boolean disconnectServer() 
    {
       boolean isOkay=false;
        try
        {
             this.registry.unbind(RMIConstants.IEntrySite);
             UnicastRemoteObject.unexportObject(registry, true);            
             isOkay=true;
        }
        catch(Exception err)
        {
           this.errormessage = err.getMessage();
        }
        
        return isOkay;
    }
    
     
}
