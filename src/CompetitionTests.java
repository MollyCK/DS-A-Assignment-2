import static org.junit.Assert.*;

import org.junit.Test;

/*
 * #intersection = #vertex = N = line 1 of file
 * #street = #edge = S = line 2 of file
 * lines 3...k = pairs :
 * 						1st int = vertex 1
 * 						2nd int = vertex 2
 * 						3rd double = edge weight between vertex 1 and vertex 2 = distance in km
 * sA, sB, sC = contestant speed respectively in metres/minute between 50 & 100 inclusive
 */

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {
    	
    	DiGraph expectedResult = new DiGraph(8);
    	expectedResult.addWeightedEdge(4, 5, 0.35);
    	expectedResult.addWeightedEdge(5, 4, 0.35);
    	expectedResult.addWeightedEdge(4, 7, 0.37);
    	expectedResult.addWeightedEdge(5, 7, 0.28);
    	expectedResult.addWeightedEdge(7, 5, 0.28);
    	expectedResult.addWeightedEdge(5, 1, 0.32);
    	expectedResult.addWeightedEdge(0, 4, 0.38);
    	expectedResult.addWeightedEdge(0, 2, 0.26);
    	expectedResult.addWeightedEdge(7, 3, 0.39);
    	expectedResult.addWeightedEdge(1, 3, 0.29);
    	expectedResult.addWeightedEdge(2, 7, 0.34);
    	expectedResult.addWeightedEdge(6, 2, 0.40);
    	expectedResult.addWeightedEdge(3, 6, 0.52);
    	expectedResult.addWeightedEdge(6, 0, 0.58);
    	expectedResult.addWeightedEdge(6, 4, 0.93);
    	assertTrue(expectedResult.equals(new CompetitionDijkstra("src/tinyEWD.txt", 1, 2, 3).graph));
    	
    	Dijkstra.getShortestDistance((new CompetitionDijkstra("src/tinyEWD.txt", 1, 2, 3).graph), 0);
    }

	@Test
    public void testFWConstructor() {
        //TODO
	}

	//TODO - more tests
	
}