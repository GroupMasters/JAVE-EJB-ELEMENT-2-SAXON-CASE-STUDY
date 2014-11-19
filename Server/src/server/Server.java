/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import server.controllers.ServerController;
import server.models.ServerModel;
/**
 *
 * @author 21187498
 */
public class Server {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerController controller = new ServerController(new ServerModel());        
        controller.startServer();
    }
    
}
