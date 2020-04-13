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
    public void testDijkstraConstructor()
    {	
    	//test for null file
    	assertEquals(null, new CompetitionDijkstra(null, 50, 60, 70).graph);
    	
    	//test for no file
    	assertEquals(null, new CompetitionDijkstra("", 50, 60, 70).graph);
    	
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
    	assertTrue(expectedResult.equals(new CompetitionDijkstra("tinyEWD.txt", 50, 60, 70).graph));
    }
    
    @Test
    public void testTimeRequiredForCompetition()
    {
    	CompetitionDijkstra testDijkstraGraph;
    	CompetitionFloydWarshall testFWDistTo;
    	int expectedResult;
    	
    	//test for invalid speedA
    	testDijkstraGraph = new CompetitionDijkstra("tinyEWD.txt", 1, 62, 73);
    	testFWDistTo = new CompetitionFloydWarshall("tinyEWD.txt", 1, 62, 73);
    	expectedResult = -1;
    	assertEquals(expectedResult, testDijkstraGraph.timeRequiredforCompetition());
    	assertEquals(expectedResult, testFWDistTo.timeRequiredforCompetition());
    	
    	//test for invalid speedA
    	testDijkstraGraph = new CompetitionDijkstra("tinyEWD.txt", 101, 62, 73);
    	testFWDistTo = new CompetitionFloydWarshall("tinyEWD.txt", 101, 62, 73);
    	expectedResult = -1;
    	assertEquals(expectedResult, testDijkstraGraph.timeRequiredforCompetition());
    	assertEquals(expectedResult, testFWDistTo.timeRequiredforCompetition());
    	
    	//test for invalid speedB
    	testDijkstraGraph = new CompetitionDijkstra("tinyEWD.txt", 51, 2, 73);
    	testFWDistTo = new CompetitionFloydWarshall("tinyEWD.txt", 51, 2, 73);
    	expectedResult = -1;
    	assertEquals(expectedResult, testDijkstraGraph.timeRequiredforCompetition());
    	assertEquals(expectedResult, testFWDistTo.timeRequiredforCompetition());
    	
    	//test for invalid speedB
    	testDijkstraGraph = new CompetitionDijkstra("tinyEWD.txt", 51, 200, 73);
    	testFWDistTo = new CompetitionFloydWarshall("tinyEWD.txt", 51, 200, 73);
    	expectedResult = -1;
    	assertEquals(expectedResult, testDijkstraGraph.timeRequiredforCompetition());
    	assertEquals(expectedResult, testFWDistTo.timeRequiredforCompetition());
    	
    	//test for invalid speedC
    	testDijkstraGraph = new CompetitionDijkstra("tinyEWD.txt", 51, 62, 13);
    	testFWDistTo = new CompetitionFloydWarshall("tinyEWD.txt", 51, 62, 13);
    	expectedResult = -1;
    	assertEquals(expectedResult, testDijkstraGraph.timeRequiredforCompetition());
    	assertEquals(expectedResult, testFWDistTo.timeRequiredforCompetition());
    	
    	//test for invalid speedC
    	testDijkstraGraph = new CompetitionDijkstra("tinyEWD.txt", 51, 62, 102);
    	testFWDistTo = new CompetitionFloydWarshall("tinyEWD.txt", 51, 62, 102);
    	expectedResult = -1;
    	assertEquals(expectedResult, testDijkstraGraph.timeRequiredforCompetition());
    	assertEquals(expectedResult, testFWDistTo.timeRequiredforCompetition());
    	
    	//test for unreachable intersections
    	testDijkstraGraph = new CompetitionDijkstra("input-C.txt", 51, 62, 73);
    	testFWDistTo = new CompetitionFloydWarshall("input-C.txt", 51, 62, 73);
    	expectedResult = -1;
    	assertEquals(expectedResult, testDijkstraGraph.timeRequiredforCompetition());
    	assertEquals(expectedResult, testFWDistTo.timeRequiredforCompetition());
    	
    	//test for valid inputs
    	testDijkstraGraph = new CompetitionDijkstra("tinyEWD.txt", 50, 60, 70);
    	testFWDistTo = new CompetitionFloydWarshall("tinyEWD.txt", 50, 60, 70);
    	expectedResult = 38;
    	assertEquals(expectedResult, testDijkstraGraph.timeRequiredforCompetition());
    	assertEquals(expectedResult, testFWDistTo.timeRequiredforCompetition());
    	
    	//test input-B
    	testDijkstraGraph = new CompetitionDijkstra("input-B.txt", 60, 80, 50);
    	testFWDistTo = new CompetitionFloydWarshall("input-B.txt", 60, 80, 50);
    	expectedResult = 10000;
    	assertEquals(expectedResult, testDijkstraGraph.timeRequiredforCompetition());
    	assertEquals(expectedResult, testFWDistTo.timeRequiredforCompetition());
    
    	//test input-J
    	testDijkstraGraph = new CompetitionDijkstra("input-J.txt", 60, 75, 61);
    	testFWDistTo = new CompetitionFloydWarshall("input-J.txt", 60, 75, 61);
    	expectedResult = -1;
    	assertEquals(expectedResult, testDijkstraGraph.timeRequiredforCompetition());
    	assertEquals(expectedResult, testFWDistTo.timeRequiredforCompetition());
    	
    	//test input-I
    	testDijkstraGraph = new CompetitionDijkstra("input-I.txt", 72, 70, 65);
    	testFWDistTo = new CompetitionFloydWarshall("input-I.txt", 72, 70, 65);
    	expectedResult = 185;
    	assertEquals(expectedResult, testDijkstraGraph.timeRequiredforCompetition());
    	assertEquals(expectedResult, testFWDistTo.timeRequiredforCompetition());
    	
    	//test input-K
    	testDijkstraGraph = new CompetitionDijkstra("input-K.txt", 51, 70, 88);
    	testFWDistTo = new CompetitionFloydWarshall("input-K.txt", 51, 70, 88);
    	expectedResult = 314;
    	assertEquals(expectedResult, testDijkstraGraph.timeRequiredforCompetition());
    	assertEquals(expectedResult, testFWDistTo.timeRequiredforCompetition());
    }

	@Test
    public void testFWConstructor() 
	{
		//test null file
		assertArrayEquals(null, new CompetitionFloydWarshall(null, 50, 60 ,70).distTo);
		
		//test for no file
		assertArrayEquals(null, new CompetitionFloydWarshall("", 50, 60, 70).distTo);
		
        double[][] testDistTo = new double[8][8];
        for(int i = 0 ; i < 8 ; i++)
        {
        	for(int j = 0 ; j < 8 ; j++)
        	{
        		testDistTo[i][j] = Double.MAX_VALUE;
        	}
        }
        for(int i = 0 ; i < 8 ; i++)
        {
        	testDistTo[i][i] = 0;
        }
        testDistTo[4][5] = 0.35;
        testDistTo[5][4] = 0.35;
        testDistTo[4][7] = 0.37;
        testDistTo[5][7] = 0.28;
        testDistTo[7][5] = 0.28;
        testDistTo[5][1] = 0.32;
        testDistTo[0][4] = 0.38;
        testDistTo[0][2] = 0.26;
        testDistTo[7][3] = 0.39;
        testDistTo[1][3] = 0.29;
        testDistTo[2][7] = 0.34;
        testDistTo[6][2] = 0.40;
        testDistTo[3][6] = 0.52;
        testDistTo[6][0] = 0.58;
        testDistTo[6][4] = 0.93;
        assertArrayEquals(testDistTo, new CompetitionFloydWarshall("tinyEWD.txt", 50, 60, 70).distTo);
	}

	//TODO - more tests
	
}