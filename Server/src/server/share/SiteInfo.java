/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.share;

import java.io.Serializable;

/**
 *
 * @author 21187498
 */
public final class SiteInfo implements Comparable<SiteInfo>, Serializable {
    //private fields

    private String id;
    private String name;
   private String flag;
   private String region;
    /*Site constructor
    
     */
    private String error = null;

    public SiteInfo() {
        this.set(null, null, null, null);
    }

    //setters and getters
    public void set(String id, String name, String reg, String f) {
        this.setFlag(f);
        this.setId(id);
        this.setName(name);
        this.setRegion(reg);
    }

    public void setFlag(String flg) {
        this.flag = flg;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String aname) {
        this.name = aname;
    }

    public void setRegion(String aregion) {
        this.region = aregion;
    }

    //getters
    public String getFlag() {
        return this.flag;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String  getRegion() {
        return this.region;
    }

    //the method to check if the class object are same
    @Override
    public int compareTo(SiteInfo t) {
        return this.getId().compareToIgnoreCase(t.getId());
    }

    // the method will validate the object attributes
    public boolean validate() {
        boolean isOkay = false;
        if (!Validator.isMatch("^[a-zA-Z0-9\\_\\.]+$", this.getId())) {
            this.setErrorMessage("Enter a valid site identity in the right format!");
        } else if (!Validator.isMatch("^[a-zA-Z\\.]+[a-zA-Z\\.\\_ 0-9]+$", this.getName())) {
            this.setErrorMessage("Enter a valid site name please!");
        } else if (!Validator.isMatch("^[a-zA-Z 0-9]+$", this.getRegion())) {            
            this.setErrorMessage("Select or enter a valid region please!");
        }else if(!Validator.isMatch("^[a-zA-Z 0-9 \\.\\_]+$", this.getFlag()))
        {
            this.setErrorMessage("Select or enter site flag");
        }
       else
        {
            isOkay=true;
        }


        return isOkay;
    }

    public String getErrorMessage() {
        return error;
    }

    private void setErrorMessage(String e) {
        this.error = e;
    }
}
