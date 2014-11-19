/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.share;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author Obaro
 */
public class EntrySite implements IEntrySite {
//all private fields
 
   private static boolean isCreate;
   private ArrayList<SiteInfo> sites;
    private String error;


    
public EntrySite()
    {//call the super class constructor
        super();        
        //initialised the fields;
        isCreate = false;
        sites = new ArrayList<SiteInfo>();
      
    }

//Using the synchronized modifier to avoid prevent interference of client trying to modifier or create a site with same id or accessing
//the same variable
    @Override
    public synchronized boolean createSite(SiteInfo site) throws RemoteException{
      
        isCreate =false;
        if(this.isValidate(site))
        {
            this.createNewRecord(site);
            return isCreate;
        }
        return false;
                  
    }//end method
    
    
    //function that will check if site details are valid
    
    private boolean isValidate(SiteInfo siteinfo)
    {
       return siteinfo.validate();
    }
    
    private void createNewRecord(SiteInfo siteinfo) 
    {
        if(this.isExists(siteinfo))
        {
          
           isCreate= false;
           this.error = "Oops this site already exists with the given name or identity number";
           return ;
        }
        //add the site
        this.sites.add(siteinfo);        
        isCreate= true;       
                        
    }
    
    
    private boolean isExists(SiteInfo info)
    { boolean isOkay=false;
      
      if(!this.sites.isEmpty())
      {
          //iterator the object and compare the name and the id if they matched 
          Iterator<SiteInfo> iter=  this.sites.iterator();
          //now loop the elements in the contain
          while(iter.hasNext())
          {
              SiteInfo tempInfo = iter.next();
              //return 0 if equals
              if(tempInfo.compareTo(info)==0)
              {
                  isOkay=true;
                  break;
              }
          }
      }
    
      return isOkay;
        
    }

    //this return the current error message
    @Override
    public final synchronized String  getErrorMessage() throws RemoteException {
        
        return this.error;
    }

    @Override
    public final synchronized ArrayList<SiteInfo> getSites() throws RemoteException {
       return this.sites;
    }
    


    
    
}
