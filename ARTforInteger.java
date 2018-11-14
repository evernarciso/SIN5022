/**
 * @author Everton
 */
package softwareTesting;

import java.util.LinkedList;

public class ARTforInteger {
    
    public int tcForExecution;
    public LinkedList testSuite;
    
    public void artTCS(int quantity, int maxRange){
        
        int i, j, tcR, tcM;
        double pairDistance, minDistance;
        LinkedList randomTS;
        
        randomTS    = new LinkedList();
        minDistance = Integer.MIN_VALUE;
                
        if(testSuite.isEmpty())
            testSuite.add((int)Math.floor(Math.random() * maxRange +1));
        
        randomTS.clear();
        for(i = 0; i < quantity; i++)
            randomTS.add((int)Math.floor(Math.random() * maxRange +1));
        
        for(i = 0; i < randomTS.size(); i++){
            tcR = (int)randomTS.get(i);
            for(j = 0; j < testSuite.size(); j++){
                tcM = (int)testSuite.get(j);
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
        
        int i, tci, tcj, position, condition;
        double distance, maxDistance;
        LinkedList TSuiteP;
        
        if(TSuite.size() <= blocks)
            return TSuite;
        
        TSuiteP = new LinkedList();
        
        condition = TSuite.size();
        while(condition >= (2*blocks)){
            maxDistance = 0;
            position = 0;
            tci = (int)TSuite.get(0);
            for(i = 0; i < blocks; i++){
                TSuiteP.add(TSuite.get(0));
                TSuite.remove(0);
            }
            condition = TSuite.size();
            for(i = 0; i < condition -1; i+=blocks){
                tcj = (int) TSuite.get(i);
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
        
        int i, position, condition, tci, tcj;
        double distance, maxDistance;
        LinkedList TSuiteP;
        
        if(TSuite.size() <= 1)
            return TSuite;
        
        TSuiteP = new LinkedList();
        
        tci = (int)TSuite.get(0);
        TSuiteP.add(TSuite.get(0));
        TSuite.remove(0);
        condition = TSuite.size();
        
        while(condition > 0){
            maxDistance = 0;
            position = 0;
            for(i = 0; i < condition; i++){
                tcj = (int)TSuite.get(i);
                distance = DistanceFunctions.distManhattan(tci, tcj);
                if(distance > maxDistance){
                    maxDistance = distance;
                    position = i;
                }
            }
            TSuiteP.add(TSuite.get(position));
            tci = (int)(TSuite.get(position));
            TSuite.remove(position);
            condition = TSuite.size();
        }
        for(i = 0; i < TSuite.size(); i++)
            TSuiteP.add(TSuite.get(i));
        
        return TSuiteP;
    }
}