/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.views;

import java.awt.Container;
import server.controllers.ServerController;
import server.resources.ResourceMenuBar;
import server.share.IObserver;
import server.share.ISubject;

/**
 *
 * @author 21187498
 */
public class ServerView  implements ISubject {

          //set the menu bar
    private ResourceMenuBar menubar ;
    private Container container; 
    //create the controller
    private ServerController controller ;
    
    //constructor    
    public ServerView(String title)
    {    
     
    }
    @Override
    public void attach(IObserver observer){       
        this.controller =(ServerController) observer;        
                
    }
   
   
    
}
