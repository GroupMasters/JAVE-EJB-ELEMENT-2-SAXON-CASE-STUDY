/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.share;

/**
 *
 * @author 21187498
 */

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * 
 * @author obaro
 * The IConnection class is used to test if there is a connection
 */
public interface IConnection extends Remote{
    
   public boolean connect(String host) throws RemoteException;
    
}
