/**
 *
 * @author Everton
 */
package softwareTesting;

public class SUTSIR {
    
    static int time = 1000; // miliseconds
    
    // median method from Jones' PhD Dissertation (Georgia Tech, 2008)
    static int medianOracle (int x, int y, int z) throws InterruptedException {
        
        int m = z;
        if (y < z) {
            if (x < y)
                m = y;
            else if (x < z)
                m = x;
        }
        else {
            if (x > y)
                m = y;
            else if (x > z)
                m = x;
        }
        // assertions below added to express correctness -- see
        // Yang et al.'s ICSE 2014 paper on iProperty for details

        assert x == y || x == z ? m == x : true ; // #1
        assert y == z ? m == y : true ;           // #2
        assert x < z && z < y ? m == z : true ;   // #3
        assert y < z && z < x ? m == z : true ;   // #4
        assert x < y && y < z ? m == y : true ;   // #5
        assert z < y && y < x ? m == y : true ;   // #6
        assert y < x && x < z ? m == x : true ;   // #7
        assert z < x && x < y ? m == x : true ;   // #8

        // Thread.sleep(time);
        // used for test execution time
        
        return m;
    }
    
    static int medianSeeded (int x, int y, int z) throws InterruptedException {
        
        int m = z;
        if (y < z) {
            if (x < y)
                m = y;
            else if (x < z)
                m = x;
        }
        else {
            if (x > y)
                m = y;
            else if (x < z) // the correct statement is x > z
            // the seeded error x >= z is unreachable
                m = x;
        }
        // assertions below added to express correctness -- see
        // Yang et al.'s ICSE 2014 paper on iProperty for details

        assert x == y || x == z ? m == x : true ; // #1
        assert y == z ? m == y : true ;           // #2
        assert x < z && z < y ? m == z : true ;   // #3
        assert y < z && z < x ? m == z : true ;   // #4
        assert x < y && y < z ? m == y : true ;   // #5
        assert z < y && y < x ? m == y : true ;   // #6
        assert y < x && x < z ? m == x : true ;   // #7
        assert z < x && x < y ? m == x : true ;   // #8

        // Thread.sleep(time);
        // used for test execution time
        
        return m;
    }
}