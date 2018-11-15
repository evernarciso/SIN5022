/**
 *
 * @author Everton
 */
package softwareTesting;

public class SUTPatterns {
    
    static int time = 1000; // miliseconds
    
    public static double stripOracle (double x, double y) throws InterruptedException{
        
        double z;
        if(x-y > 8.2)
            z = x+y;
        else
            z = 100;
        
        // Thread.sleep(time);
        // used for test execution time
        return z;
    }
    
    public static double stripSeeded (double x, double y) throws InterruptedException{
        
        double z;
        
        if(x-y > 8.5)
            z = x+y;
        else
            z = 100;
        
        // Thread.sleep(time);
        // used for test execution time
        return z;
    }
    
    public static double blockOracle (double x, double y) throws InterruptedException{
        
        double z;
        
        if( (x >= 4 && x <= 6) && (y >= 4 && y <= 6))
            z = x - y;
        else
            z = 100;
        
        // Thread.sleep(time);
        // used for test execution time
        return z;
    }
    
    public static double blockSeeded (double x, double y) throws InterruptedException{
        
        double z;
        
        if( (x >= 4 && x <= 6) && (y >= 4 && y <= 6))
            z = x + y;
        else
            z = 100;
        
        // Thread.sleep(time);
        // used for test execution time
        return z;
    }
}