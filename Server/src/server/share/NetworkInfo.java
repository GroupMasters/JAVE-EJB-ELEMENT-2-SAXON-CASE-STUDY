/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.share;

/**
 *
 * @author 21187498
 */
public class NetworkInfo {
    
    private int port;
    private String host;
   public NetworkInfo()
    {
      port=  1099;
      host= "localhost";
    }
    public void setHost(String host)
    {
        this.host=host;
    }
    public boolean validate()
    {
        boolean isOkay =true;
        if( getHost().isEmpty() || (this.getPort()< 0))
        {
            isOkay =false;
        }
        return isOkay;
    }
    public void setPort(int port)
    {
        this.port = port;
    }
    public String getHost()
    {
        return this.host;
    }
    
    public int getPort()
    {
        return port;
    }
}
