/**
 * @author Everton
 */

package softwareTesting;

import java.util.LinkedList;
import java.util.Random;
public class ARTforAlphanumeric {
    
    public String tcForExecution;
    public LinkedList testSuite;
    
    public void artTCS(int quantity, LinkedList dictionary){
        
        int i, j, random;
        double pairDistance, minDistance;
        String tcR, tcM;
        LinkedList randomTS;
        Random rd;
        
        randomTS = new LinkedList();
        rd = new Random();
        
        minDistance = Integer.MIN_VALUE;
        
        if(quantity > dictionary.size())
            quantity = dictionary.size();
        
        if(testSuite.isEmpty())
            testSuite.add(dictionary.get(0).toString());
        
        randomTS.clear();
        for(i = 0; i < quantity; i++){
            random = rd.nextInt(dictionary.size());
            randomTS.add(dictionary.get(random).toString());
        }
        
        for(i = 0; i < randomTS.size(); i++){
            tcR = (String)randomTS.get(i);
            for(j = 0; j < testSuite.size(); j++){
                tcM = (String)testSuite.get(j);
                pairDistance = DistanceFunctions.distLevenshtein(tcR, tcM);
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
        double distance, maxDistance;
        String tci, tcj;
        LinkedList TSuiteP;
        
        if(TSuite.size() <= blocks)
            return TSuite;
        
        TSuiteP = new LinkedList();
        
        condition = TSuite.size();
        while(condition >= (2*blocks)){
            maxDistance = 0;
            position = 0;
            tci = (String)TSuite.get(0);
            for(i = 0; i < blocks; i++){
                TSuiteP.add(TSuite.get(0));
                TSuite.remove(0);
            }
            condition = TSuite.size();
            for(i = 0; i < condition -1; i+=blocks){
                tcj = (String) TSuite.get(i);
                distance = DistanceFunctions.distLevenshtein(tci, tcj);
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
        double distance, maxDistance;
        String tci, tcj;
        LinkedList TSuiteP;
        
        if(TSuite.size() <= 1)
            return TSuite;
        
        TSuiteP = new LinkedList();
        
        tci = (String)TSuite.get(0);
        TSuiteP.add(TSuite.get(0));
        TSuite.remove(0);
        condition = TSuite.size();
        
        while(condition > 0){
            maxDistance = 0;
            position = 0;
            for(i = 0; i < condition; i++){
                tcj = (String)TSuite.get(i);
                distance = DistanceFunctions.distLevenshtein(tci, tcj);
                if(distance > maxDistance){
                    maxDistance = distance;
                    position = i;
                }
            }
            TSuiteP.add(TSuite.get(position));
            tci = (String)(TSuite.get(position));
            TSuite.remove(position);
            condition = TSuite.size();
        }
        for(i = 0; i < TSuite.size(); i++)
            TSuiteP.add(TSuite.get(i));
        
        return TSuiteP;
    }
}