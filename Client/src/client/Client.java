/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.controllers.ClientController;
import client.models.ClientModel;
import client.views.ClientView;

/**
 *
 * @author 21187498
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      ClientController controller = new ClientController(new ClientModel(),new ClientView("Saxon Heritage"));
      controller.launch();
    }
}
