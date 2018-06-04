package application;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JUnitTests {
	 @Test
	 //Checks to see if there is only one instance of the ocean map object
	 public void Test1() {
	    assertTrue(OceanMap.getInstance() == Main.oceanMap.getInstance());
	 }
	 @Test
	 //tests that the correct number of islands have been added to the ocean map
	 //also sees that the islands don't overlap
	  public void Test2() {
	        int islandCount = 0;
	        for(int i = 0; i < OceanMap.DIMENSION; i++){
	            for (int ii = 0; ii < OceanMap.DIMENSION; ii++){
	                if(OceanMap.getInstance().getMap()[i][ii] == CellTypes.ISLAND){
	                    islandCount++;
	                }
	            }
	        }
	        assertTrue((islandCount == OceanMap.ISLAND_COUNT));
	    }
}
