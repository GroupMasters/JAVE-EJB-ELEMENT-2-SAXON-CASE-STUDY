/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.share;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import javax.swing.ImageIcon;

import javax.swing.JDialog;

/**
 *
 * @author 21187498
 */
public class View extends JDialog{

  
  public   View()
    {
      
      
       
    }
    
    public void center() {
		int width, height;
                Dimension dim;		
                dim = Toolkit.getDefaultToolkit().getScreenSize();
               
		width = dim.width;
	 	height = dim.height;

		// calculate the new location of the window
		int w = this.getSize().width;
		int h =this.getSize().height;
		int x = (width - w) / 2;
		int y = (height - h) / 2;
		// moves this component to a new location, the top-left corner of
		// the new location is specified by the x and y
		// parameters in the coordinate space of this component's parent
		this.setLocation(x, y);
              
                
	}
    
    
    public static ImageIcon getPngTransparentImageIcon(ImageIcon icon)
    {
        
        
        ImageFilter filter = new RGBImageFilter() {
         int transparentColor = Color.white.getRGB() | 0x00FFFFFF ;

         @Override
         public final int filterRGB(int x, int y, int rgb) {
            if ((rgb | 0xFF000000) == transparentColor) {
               return 0x00FFFFFF & rgb;
            } else {
               return rgb;
            }
         }
        };
      ImageProducer filteredImgProd = new FilteredImageSource(icon.getImage().getSource(), filter);
      Image transparentImg = Toolkit.getDefaultToolkit().createImage(filteredImgProd);  
      icon.setImage(transparentImg);
     return icon;
    }
}

