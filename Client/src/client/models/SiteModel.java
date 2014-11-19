/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.models;

import client.controllers.ClientController;
import client.controllers.SiteController;
import client.views.SiteView;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import server.controllers.ServerController;
import server.share.IEntrySite;
import server.share.IObserver;
import server.share.ISubject;
import server.share.NetworkInfo;
import server.share.RMIConstants;
import server.share.SiteInfo;

/**
 *
 * @author 21187498
 */
public class SiteModel implements ISubject {

    private SiteController controller;
  private IEntrySite entrySite;
    private String messageError;
    private ArrayList<SiteInfo> sites;
    private TableModel tablemodel;
    
    public  SiteModel ()
    {
        sites= new ArrayList<>();
        tablemodel = new TableModel(this);
    }
    @Override
    public void attach(IObserver observer) {
        controller  = (SiteController)observer;
    }
    
    


  

  public  AbstractTableModel getTableModel()
    {
        return this.tablemodel;
    }
   


  public  String getErrorMessage() {     
        
        return  messageError;
    }
  
  //The method will load the sites from the server to the client
 public synchronized void loadSites()
 {
   
        
     
   if(makeConnection())
                {
     try
                    {
                         sites= entrySite.getSites();
                    }
                    catch(IOException err)
                    {
                       messageError=err.getMessage();
                    }
        }
    
    
 }//end loadsite message

    //The method call the rmi 
   public void createSite(SiteInfo info) {
       boolean isokay =false;
       int status =0;
       //validation here
       if(this.makeConnection())
       {
           try
           {
            isokay = this.entrySite.createSite(info);
           
            if(isokay)
            {
               
               status =1;
               
            }
            else
                this.messageError = this.entrySite.getErrorMessage(); 
           }
           catch(Exception ex)
           {
            this.messageError = ex.getMessage();
            status =0;
           }         
       } 
       this.loadSites();
       this.tablemodel.fireTableDataChanged();
       this.controller.update(status);
    
    }

 public boolean makeConnection() {
    
        //check if the security manager is setup already
      if(System.getSecurityManager() == null && ServerController.SSLStatus)
          System.setSecurityManager(new SecurityManager());
      //now get the interface from the server
     try
      { 
        
         // Set the connection information for the server
          NetworkInfo info = new NetworkInfo();
          info.setHost(RMIConstants.INTERNET_HOST);
          info.setPort(RMIConstants.INTERNET_PORT);
          Registry registry = LocateRegistry.getRegistry(info.getHost(),info.getPort());
          this.entrySite = (IEntrySite)  registry.lookup(RMIConstants.IEntrySite);          
          ClientController.IsConnected =true;         
        return true;
          
      }
      catch(RemoteException | NotBoundException err)
      {
          this.messageError=err.getMessage();
          return false;
      }
    }

  private  ArrayList<SiteInfo> getSites() {
        try
        {
            if(this.makeConnection())
            {
             return this.entrySite.getSites();
            }
           return null;
        }
        catch(RemoteException err)
        {
            this.messageError =err.getMessage();
            return null;
        }
    }
 
 
 //the inner class for the table model
 
 private class TableModel extends AbstractTableModel
 {
     
     private SiteModel parent;
     public  TableModel(SiteModel aparent)
     {
         this.parent = aparent;
         
     }
     
     /**
     
      * 
      */
    final  private static int SITE_ID =0;
    final  private static int SITE_NAME =1;
    final private static int SITE_FLAG =3;
    final private static int SITE_REGION =2;
    
     
     
        @Override
        public int getRowCount() {
           return this.parent.sites.size();
        }

        @Override
        public int getColumnCount() {
           return 4;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) 
        {
            
           
			SiteInfo info = this.parent.sites.get(rowIndex);
			// check if the return car is null
			if (info == null)
				return null;

			// else
			switch (columnIndex) {
			case SITE_ID:
				return info.getId();
			case SITE_NAME:
				return info.getName();
			case  SITE_REGION:
				return info.getRegion();
                        case SITE_FLAG :
				return info.getFlag();
			default:
				return null;
			}

        }
     
        
         @Override
	  public Class<?> getColumnClass(int col) {
			switch (col) {
			case   SITE_ID:
				return String.class;
			case  SITE_NAME:
				return String.class;
			case SITE_REGION:
				return String.class;
			case  SITE_FLAG :
				return String.class;
			default:
				return null;
			}
                }
         
        
         
              @Override
		public String getColumnName(int col) {
			switch (col) {
			case SITE_ID:
				return "Identity No";
			case SITE_NAME:
				return "Name";
			case SITE_REGION:
				return "Location";
			case  SITE_FLAG:
				return "Flag Rank";
			default:
				return null;
			}

		}
                  
 }
}
