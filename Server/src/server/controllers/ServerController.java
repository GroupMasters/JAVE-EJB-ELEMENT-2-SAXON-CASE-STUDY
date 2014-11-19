/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controllers;
import server.models.ServerModel;
import server.share.Console;
import server.share.IObserver;
import server.share.RMIConstants;

/**
 *
 * @author 21187498
 */
public class ServerController extends IObserver{
    public static boolean SSLStatus =false;
    
    private ServerModel model;
    public static boolean IsServerRunning =false;
 
    
    
    public ServerController(ServerModel aModel)
    {
        this.model=aModel;      
      //attach the controller        
        this.model.attach(this);
       
        
    }

    public void startServer() {
        
         this.model.startServer();
        if( IsServerRunning)
        {
            System.out.println("Server is running on ["+RMIConstants.INTERNET_HOST+" on port "+RMIConstants.INTERNET_PORT+"] ...");
            this.waitForShutDown("Enter q or Q key to shutdown server > ");
        }else
            System.out.println( this.model.getErrorMessage());
        
           }

    
    //the method wait for the user to enter a key
    private void waitForShutDown(String prompt) {
      
      char c =  Console.askChar(prompt);
      if(c=='q' || c == 'Q'){
          this.model.disconnectServer();
          Console.WriteLn("Server as be successfully shutdown");
          System.exit(0);
          return ;
      }
      //Recursive the method
      waitForShutDown(prompt);
        
    }

   

   
    
}
