/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.share;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author 21187498
 */
public interface IEntrySite extends Remote {
     //the interface to create new company site 
    public boolean createSite(SiteInfo site) throws RemoteException;   
    public ArrayList<SiteInfo> getSites() throws RemoteException;
    public String getErrorMessage() throws RemoteException;
}
