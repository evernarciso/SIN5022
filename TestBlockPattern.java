/**
 *
 * @author Everton
 */
package softwareTesting;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class TestBlockPattern {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        
        int i, j, tcRandom, repetition, blocks;
        double tc1, tc2, result1, result2, minRange, maxRange;
        long startTimeS, endTimeS, totalTimeS;    // selection time
        long startTimeES, endTimeES, totalTimeES; // execution time for TCS
        long startTimeP, endTimeP, totalTimeP;    // prioritization time
        long startTimeEP, endTimeEP, totalTimeEP; // execution time for TCP
        LinkedList testSuiteP;
        boolean condition;
        
        ARTforDouble ts = new ARTforDouble();
        ts.testSuite = new LinkedList();
        
        Evaluation evalTCS = new Evaluation();
        Evaluation evalTCP = new Evaluation();
        
        tcRandom = 6;
        minRange = 0;
        maxRange = 100.1;
        repetition = 100;
        blocks = 2;
        totalTimeS  = 0;
        totalTimeES = 0;
        totalTimeP  = 0;
        totalTimeEP = 0;
        
        // begin ART
        System.out.println("=== Block pattern tests with ART for TCS/TCP ===");
        System.out.println();
        System.out.println("Input domain: " + minRange + " to " + maxRange);
        evalTCS.initializeEvaluation();
        evalTCP.initializeEvaluation();
        for(i = 0; i < repetition; i++){
            condition = true;
            ts.testSuite.clear();
            while(condition){
                startTimeS = System.nanoTime();
                ts.artTCS(tcRandom, minRange, maxRange);
                tc1 = (double)ts.testSuite.getLast();
                ts.artTCS(tcRandom, minRange, maxRange);
                tc2 = (double)ts.testSuite.getLast();
                endTimeS = System.nanoTime();
                totalTimeS += endTimeS - startTimeS;
                startTimeES = System.nanoTime();
                result1 = SUTPatterns.blockOracle(tc1, tc2);
                result2 = SUTPatterns.blockSeeded(tc1, tc2);
                condition = evalTCS.fMeasure(result1, result2);
                endTimeES = System.nanoTime();
                totalTimeES += endTimeES - startTimeES;
            }
            System.out.println("Repetitions: " + i);
            startTimeP = System.nanoTime();
            testSuiteP = ts.artTCP(ts.testSuite, blocks);
            endTimeP = System.nanoTime();
            totalTimeP += endTimeP - startTimeP;
            condition = true;
            j = 0;
            while(condition){
                startTimeEP = System.nanoTime();
                tc1 = (double)testSuiteP.get(j);
                tc2 = (double)testSuiteP.get(j+1);
                result1 = SUTPatterns.blockOracle(tc1, tc2);
                result2 = SUTPatterns.blockSeeded(tc1, tc2);
                condition = evalTCP.fMeasure(result1, result2);
                endTimeEP = System.nanoTime();
                totalTimeEP += endTimeEP - startTimeEP;
                j += blocks;
                if(j >= testSuiteP.size()-1)
                    break;
            }
        }
        System.out.println("min  F-measure TCS: " + evalTCS.minFMeasure);
        System.out.println("máx  F-measure TCS: " + evalTCS.maxFMeasure);
        System.out.println("mean F-Measure TCS: " + evalTCS.fMeasureACC/repetition);
        System.out.println("Total time of TCS/ART for " + repetition + " repetition(s): " + totalTimeS/1000000000 + " second(s)");
        System.out.println("Total time of EXE/SUT for " + repetition + " repetition(s): " + totalTimeES/1000000000 + " second(s)");
        System.out.println();
        System.out.println("min  F-measure TCP: " + evalTCP.minFMeasure);
        System.out.println("máx  F-measure TCP: " + evalTCP.maxFMeasure);
        System.out.println("mean F-Measure TCP: " + evalTCP.fMeasureACC/repetition);
        System.out.println("Total time of TCP/ART for " + repetition + " repetition(s): " + totalTimeP/1000000000 + " second(s)");
        System.out.println("Total time of EXE/SUT for " + repetition + " repetition(s): " + totalTimeEP/1000000000 + " second(s)");
        System.out.println();
        // end ART
        
        // begin Random
        System.out.println("=== Block pattern tests with Random TCS ===");
        System.out.println();
        totalTimeS  = 0;
        totalTimeES  = 0;
        evalTCS.initializeEvaluation();
        for(i = 0; i < repetition; i++){
            condition = true;
            while(condition){
                startTimeS = System.nanoTime();
                tc1 = ThreadLocalRandom.current().nextDouble(minRange, maxRange);
                tc2 = ThreadLocalRandom.current().nextDouble(minRange, maxRange);
                endTimeS = System.nanoTime();
                totalTimeS += endTimeS - startTimeS;
                startTimeES = System.nanoTime();
                result1 = SUTPatterns.blockOracle(tc1, tc2);
                result2 = SUTPatterns.blockSeeded(tc1, tc2);
                condition = evalTCS.fMeasure(result1, result2);
                endTimeES = System.nanoTime();
                totalTimeES += endTimeES - startTimeES;
            }
        }
        
        System.out.println("min  F-measure RND: " + evalTCS.minFMeasure);
        System.out.println("máx  F-measure RND: " + evalTCS.maxFMeasure);
        System.out.println("mean F-Measure RND: " + evalTCS.fMeasureACC/repetition);
        System.out.println("Total time of TCS/RND for " + repetition + " repetition(s): " + totalTimeS/1000000000 + " second(s)");
        System.out.println("Total time of EXE/SUT for " + repetition + " repetition(s): " + totalTimeES/1000000000 + " second(s)");
        // end Random
    }
}