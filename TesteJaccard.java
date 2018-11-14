
package softwareTesting;

import java.util.LinkedList;

/**
 *
 * @author Everton
 */
public class TesteJaccard {
    public static void main(String[] args) throws InterruptedException {
        
        LinkedList teste, tcp;
        
        teste = RandomAlphanumericGen.alphaGenerator(10, 10);
        
        ARTforAlphanumeric ART = new ARTforAlphanumeric();
        
        for (int i = 0; i < teste.size(); i++)
            System.out.println(teste.get(i).toString());
        System.out.println("=====");
        
        ART.testSuite = new LinkedList();
        ART.artTCS(1,teste);
        ART.artTCS(3,teste);
        ART.artTCS(3,teste);
        ART.artTCS(3,teste);
        
        for (int i = 0; i < ART.testSuite.size(); i++)
            System.out.println(ART.testSuite.get(i).toString());
        System.out.println("*************");
        
        tcp = ART.artTCP(ART.testSuite);
        
        for (int i = 0; i < tcp.size(); i++)
            System.out.println(tcp.get(i).toString());
        
         System.out.println("*************");
         System.out.println("*************");
        
        String str1 = "abcdefghijklma";
        String str2 = "aaaaaaaa";
        
        double result = DistanceFunctions.distJaccard(str1, str2);
        System.out.println(result);
        
        int result1 = SUTSIR.medianOracle(2, 3, 2);
        int result2 = SUTSIR.medianSeeded(2, 3, 2);
        
        System.out.println(result1);
        System.out.println(result2);
    
    }
    
}
