/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.share;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author 21187498
 */
public class Validator {
    
    
    static boolean isMatch(String pattern, String value)
    {
        boolean isokay=false;
       												// matched the give
															// value with
		Pattern p = Pattern.compile(pattern); // This method compile the pattern
												// to the understand of Java
		Matcher m = p.matcher(value.trim()); // this object create a matcher
												// that will match the value
												// give
		if (m.matches()) 
                {
                    isokay=true;
                }
		
        return isokay;
    }
    
}
