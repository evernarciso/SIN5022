/** *
 * @author Everton
 */

package softwareTesting;

public class DistanceFunctions {
    
    protected static int distManhattan (int A, int B){
        
        return Math.abs(A - B);
    }
    
    protected static double distManhattan (double A, double B){
        
        return Math.abs(A - B);
    }
    
    public static double distJaccard(String str1, String str2) {
        // Hint: if you consider that a = A, uncomment uppercase statements
        
        int i, j;
        double intersection, union;
        StringBuilder string1, string2;
        
        if (str1 == null || str2 == null) {
            System.out.println("String is null");
            System.exit(0);
        }
        
        // str1 = str1.toUpperCase();
        // str2 = str2.toUpperCase();
        
        if (str1.compareTo(str2) == 0)
            return 0;
        
        intersection = 0;
        
        // get the intersection        
        // eliminating char redundance from strings
        string1 = new StringBuilder(str1);
        for(i = 0; i < string1.length() -1; i++)
            for(j = i+1; j < string1.length(); j++)
                if(string1.charAt(i) == string1.charAt(j)){
                     string1.deleteCharAt(j);
                     j --; // return to position i+1
                }
        
        string2 = new StringBuilder(str2);
        for(i = 0; i < string2.length() -1; i++)
            for(j = i+1; j < string2.length(); j++)
                if(string2.charAt(i) == string2.charAt(j)){
                     string2.deleteCharAt(j);
                     j --; // return to position i+1
                }
        
        for(i = 0; i < string1.length(); i++)
            for (j = 0; j < string2.length(); j++)
                if(string1.charAt(i) == string2.charAt(j))
                    intersection ++;
        
        // get the union
        union = string1.length() + string2.length() - intersection;
        
        if(union == 0)
            return 0;
        
        return 1-(intersection/union);
    }
    
    public static int distLevenshtein(String str1, String str2) {
        // Source: adapted from https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Java
        // Hint: if you consider that a = A, uncomment uppercase statements
    
        if (str1 == null || str2 == null) {
            System.out.println("String is null");
            System.exit(0);
        }

        int len0 = str1.length() + 1;
        int len1 = str2.length() + 1; 
        
        // the array of distances                                                       
        int[] cost = new int[len0];                                                     
        int[] newcost = new int[len0];
        
        int[] swap;
        int cost_replace;              
        int cost_insert;
        int cost_delete;
        
        // str1 = str1.toUpperCase();
        // str2 = str2.toUpperCase();

        // initial cost of skipping prefix in String s0                                 
        for (int i = 0; i < len0; i++) cost[i] = i;                                     

        // dynamically computing the array of distances                                  

        // transformation cost for each letter in s1                                    
        for (int j = 1; j < len1; j++) {                                                
            // initial cost of skipping prefix in String s1                             
            newcost[0] = j;                                                             

            // transformation cost for each letter in s0                                
            for(int i = 1; i < len0; i++) {                                             
                // matching current letters in both strings                             
                int match = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1;             

                // computing cost for each transformation                               
                cost_replace = cost[i - 1] + match;                                 
                cost_insert  = cost[i] + 1;                                         
                cost_delete  = newcost[i - 1] + 1;

                // keep minimum cost                                                    
                newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
            }                                                                           

            // swap cost/newcost arrays                                                 
            swap = cost; cost = newcost; newcost = swap;
        }                                                                               

        // the distance is the cost for transforming all letters in both strings        
        return cost[len0 - 1];
    }
    
    protected static int distHamming(String str1, String str2){
        // Needs to receive two strings with the same length
        // Hint: if you consider that a = A, uncomment uppercase statements
        
        int i, hDist;
        
        if (str1 == null || str2 == null) {
            System.out.println("String is null");
            System.exit(0);
        }
        
        if (str1.length() != str2.length()) {
            System.out.println("The string are not equal in length.");
            System.exit(0);
        }
        
        //str1 = str1.toUpperCase();
        //str2 = str2.toUpperCase();
        
        hDist = 0;

        for (i = 0; i < str1.length(); i++){
            if (str1.charAt(i) != str2.charAt(i))
                hDist ++;
        }
        
        return hDist;
    }
}