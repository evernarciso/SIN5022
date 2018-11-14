/**
 * @author Everton
 */
package softwareTesting;

import java.util.LinkedList;

public class RandomAlphanumericGen {
    
    public static LinkedList alphaGenerator(int quantity, int length){

        String ALPHANUMERIC;
        int i, j;
        String tc;
        LinkedList randomTS;
        java.util.Random rdGenerator;
        
        ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";  // 26 positions
        ALPHANUMERIC += "abcdefghijklmnopqrstuvwxyz"; // 26 positions
        ALPHANUMERIC += "!?@#$%&()[]<>";              // 13 positions
        ALPHANUMERIC += "0123456789";                 // 10 positions
        
        rdGenerator = new java.util.Random();
        randomTS    = new LinkedList();
        
        for(i = 0; i < quantity; i++){
            tc = "";
            for(j = 0; j < length; j++){
                tc += ALPHANUMERIC.charAt(rdGenerator.nextInt(75)); // generate from 0 to 74 index
            }
            randomTS.add(tc);
        } 
        return randomTS;
    }    
}