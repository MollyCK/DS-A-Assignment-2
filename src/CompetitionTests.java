import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class CompetitionTests {

	@Test
	public void testWeightedEdgeEquals()
	{
		Vertex testVertex1 = new Vertex(1);
		Vertex testVertex2 = new Vertex(2);
		WeightedEdge testEdge1 = new WeightedEdge(testVertex1, testVertex2, 0);
		
		//test for same object
		WeightedEdge testEdge2 = testEdge1;
		assertTrue(testEdge1.equals(testEdge2));
		
		//test for unequal destination keys
		testEdge2 = new WeightedEdge(testVertex1, testVertex1, 0);
		assertFalse(testEdge1.equals(testEdge2));
		
		//test for unequal origin keys
		testEdge2 = new WeightedEdge(testVertex2, testVertex2, 0);
		assertFalse(testEdge1.equals(testEdge2));
		
		//test for unequal weights
		testEdge2 = new WeightedEdge(testVertex1, testVertex2, 1);
		assertFalse(testEdge1.equals(testEdge2));
	}

	@Test
	public void testVertexEquals()
	{
		Vertex testVertex1 = new Vertex(1);
		
		//test for same object
		Vertex testVertex2 = testVertex1;
		assertTrue(testVertex1.equals(testVertex2));
		
		//test for null input
		testVertex2 = null;
		assertFalse(testVertex1.equals(testVertex2));
		
		//test for unequal key
		testVertex2 = new Vertex(2);
		assertFalse(testVertex1.equals(testVertex2));
		
		//test for unequal outgoingEdges lengths and unequal keys
		testVertex2.addWeightedEdge(testVertex1, 0);
		assertFalse(testVertex1.equals(testVertex2));
		
		//test for unequal outgoingEdges lengths only
		testVertex2 = new Vertex(2);
		testVertex2.addWeightedEdge(testVertex1, 2);
		assertFalse(testVertex1.equals(testVertex2));
		
		//test for unequal edges
		testVertex2 = new Vertex(1);
		testVertex2.addWeightedEdge(testVertex2, 3);
		testVertex1.addWeightedEdge(testVertex1, 2);
		assertFalse(testVertex1.equals(testVertex2));
		
		//test for false
		testVertex2 = new Vertex(1);
		testVertex1.addWeightedEdge(testVertex1, 2);
		assertFalse(testVertex1.equals(testVertex2));
		
		
	}

	@Test 
	public void testVertexPriorityQueue()
	{
		VertexPriorityQueue testPQ = new VertexPriorityQueue();
		Vertex testVertex1 = new Vertex(1);
		
		//test length()
		testPQ.enqueue(testVertex1);
		int expectedResult = 1;
		assertEquals(expectedResult, testPQ.length());
		
		//test get(i)
		Vertex expectedResult2 = testVertex1;
		assertTrue(expectedResult2.equals(testPQ.get(0)));
	}

	@Test
	public void testDiGraphEquals()
	{
		//test for unequal number of vertices
		DiGraph testGraph = new DiGraph(8);
		DiGraph testGraph2 = new DiGraph(6);
		assertFalse(testGraph.equals(testGraph2));
		
		//test for unequal vertices with unequal edges
		Vertex testVertex1 = new Vertex(1);
		Vertex testVertex2 = new Vertex(2);
		testGraph2 = new DiGraph(8);
		testGraph.addWeightedEdge(1, 2, 4);
		testGraph2.addWeightedEdge(2, 1, 3);
		assertFalse(testGraph.equals(testGraph2));
	}
	
    @Test
    public void testDijkstraConstructor() throws FileNotFoundException 
    {	
    	//test for null file
    	boolean thrown = false;
    	try {
    		new CompetitionDijkstra(null, 50, 60, 70);
    	} catch(NullPointerException e) {
    		thrown = true;
    	}
    	assertTrue(thrown);
    	
    	//test for no file
    	thrown = false;
    	try {
    		new CompetitionDijkstra("", 50, 60, 70);
    	} catch(FileNotFoundException e) {
    		thrown = true;
    	}
    	assertTrue(thrown);
    	
    	//test for valid inputs with tinyEWD.txt
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
    	assertTrue(expectedResult.equals(new CompetitionDijkstra("src/tinyEWD.txt", 50, 60, 70).graph));
    }
    
    @Test
    public void testTimeRequiredForCompetition() throws FileNotFoundException
    {
    	CompetitionDijkstra testGraph;
    	int expectedResult;
    	
    	//test for invalid speedA
    	testGraph = new CompetitionDijkstra("src/tinyEWD.txt", 1, 62, 73);
    	expectedResult = -1;
    	assertEquals(expectedResult, testGraph.timeRequiredforCompetition());
    	
    	//test for invalid speedA
    	testGraph = new CompetitionDijkstra("src/tinyEWD.txt", 101, 62, 73);
    	expectedResult = -1;
    	assertEquals(expectedResult, testGraph.timeRequiredforCompetition());
    	
    	//test for invalid speedB
    	testGraph = new CompetitionDijkstra("src/tinyEWD.txt", 51, 2, 73);
    	expectedResult = -1;
    	assertEquals(expectedResult, testGraph.timeRequiredforCompetition());
    	
    	//test for invalid speedB
    	testGraph = new CompetitionDijkstra("src/tinyEWD.txt", 51, 200, 73);
    	expectedResult = -1;
    	assertEquals(expectedResult, testGraph.timeRequiredforCompetition());
    	
    	//test for invalid speedC
    	testGraph = new CompetitionDijkstra("src/tinyEWD.txt", 51, 62, 13);
    	expectedResult = -1;
    	assertEquals(expectedResult, testGraph.timeRequiredforCompetition());
    	
    	//test for invalid speedC
    	testGraph = new CompetitionDijkstra("src/tinyEWD.txt", 51, 62, 102);
    	expectedResult = -1;
    	assertEquals(expectedResult, testGraph.timeRequiredforCompetition());
    	
    	//test for unreachable intersections
    	testGraph = new CompetitionDijkstra("src/input-C.txt", 51, 62, 73);
    	expectedResult = -1;
    	assertEquals(expectedResult, testGraph.timeRequiredforCompetition());
    	
    	//test for valid inputs
    	testGraph = new CompetitionDijkstra("src/tinyEWD.txt", 50, 60, 70);
    	expectedResult = 38;
    	assertEquals(expectedResult, testGraph.timeRequiredforCompetition());
    }

	@Test
    public void testFWConstructor() {
        //TODO
	}

	//TODO - more tests
	
}