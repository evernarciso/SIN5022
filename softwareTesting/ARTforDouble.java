/**
 * @author Everton
 */
package softwareTesting;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class ARTforDouble {
    
    public double tcForExecution;
    public LinkedList testSuite;
    
    public void artTCS(int quantity, double minRange, double maxRange){
        
        int i, j;
        double tcR, tcM, pairDistance, minDistance;
       
        LinkedList randomTS;
        
        randomTS  = new LinkedList();
        
        minDistance = Integer.MIN_VALUE;
        
        if(testSuite.isEmpty())
            testSuite.add(ThreadLocalRandom.current().nextDouble(minRange, maxRange));
        
        randomTS.clear();
        for(i = 0; i < quantity; i++)
            randomTS.add(ThreadLocalRandom.current().nextDouble(minRange, maxRange));
        
        for(i = 0; i < randomTS.size(); i++){
            tcR = (double)randomTS.get(i);
            for(j = 0; j < testSuite.size(); j++){
                tcM = (double)testSuite.get(j);
                pairDistance = DistanceFunctions.distManhattan(tcR, tcM);
                if(pairDistance > minDistance){
                    minDistance = pairDistance;
                    tcForExecution = tcR;
                }
            }
        }
        testSuite.add(tcForExecution); // add a test case to the test suite
    }
    
    public LinkedList artTCP(LinkedList TSuite, int blocks){
        
        int i, position, condition;
        double tci, tcj, distance, maxDistance;
        LinkedList TSuiteP;
        
        if(TSuite.size() <= blocks)
            return TSuite;
        
        TSuiteP = new LinkedList();
        
        condition = TSuite.size();
        while(condition >= (2*blocks)){
            maxDistance = 0;
            position = 0;
            tci = (double)TSuite.get(0);
            for(i = 0; i < blocks; i++){
                TSuiteP.add(TSuite.get(0));
                TSuite.remove(0);
            }
            condition = TSuite.size();
            for(i = 0; i < condition -1; i+=blocks){
                tcj = (double) TSuite.get(i);
                distance = DistanceFunctions.distManhattan(tci, tcj);
                if(distance > maxDistance){
                    maxDistance = distance;
                    position = i;
                }
            }
            for(i = 0; i < blocks; i++){
                TSuiteP.add(TSuite.get(position));
                TSuite.remove(position);
            }
            condition = TSuite.size();
        }
        
        for(i = 0; i < TSuite.size(); i++)
            TSuiteP.add(TSuite.get(i));
        
        return TSuiteP;
    }
    
    public LinkedList artTCP(LinkedList TSuite){
        
        int i, position, condition;
        double distance, maxDistance, tci, tcj;
        LinkedList TSuiteP;
        
        if(TSuite.size() <= 1)
            return TSuite;
        
        TSuiteP = new LinkedList();
        
        tci = (double)TSuite.get(0);
        TSuiteP.add(TSuite.get(0));
        TSuite.remove(0);
        condition = TSuite.size();
        
        while(condition > 0){
            maxDistance = 0;
            position = 0;
            for(i = 0; i < condition; i++){
                tcj = (double)TSuite.get(i);
                distance = DistanceFunctions.distManhattan(tci, tcj);
                if(distance > maxDistance){
                    maxDistance = distance;
                    position = i;
                }
            }
            TSuiteP.add(TSuite.get(position));
            tci = (double)(TSuite.get(position));
            TSuite.remove(position);
            condition = TSuite.size();
        }
        for(i = 0; i < TSuite.size(); i++)
            TSuiteP.add(TSuite.get(i));
        
        return TSuiteP;
    }
}