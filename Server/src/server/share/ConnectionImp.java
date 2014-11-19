/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.share;

import java.rmi.RemoteException;

/**
 *
 * @author 21187498
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.

*/


public class ConnectionImp implements IConnection{

    
    /**
     *
     * @param host the client ip address 
     * @return
     * @throws RemoteException 
     */
    @Override
    public synchronized boolean connect(String host) throws RemoteException 
    {
        boolean isokay=true;
        if(host.equalsIgnoreCase("test"))
            isokay=true;
        
        
        return isokay;
       
    }
    
}
