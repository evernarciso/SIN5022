package softwareTesting;
/**
 *
 * @author Everton
 */

public class Evaluation {
    
    int fMeasure, fMeasureACC, minFMeasure, maxFMeasure;
    boolean result;
    
    public void initializeEvaluation(){
        
        result = true;
        fMeasure = 1;
        fMeasureACC = 1;
        minFMeasure = Integer.MAX_VALUE;
        maxFMeasure = Integer.MIN_VALUE;
    }
    
    public boolean fMeasure(double result1, double result2){
        
        result = true;
        if(result1 == result2)
            fMeasure ++;
        else{
            result = false;
            if(fMeasure < minFMeasure)
                minFMeasure = fMeasure;
            if(fMeasure > maxFMeasure)
                maxFMeasure = fMeasure;
            fMeasureACC += fMeasure;
            fMeasure = 1;
        }
        return result;
    }
}