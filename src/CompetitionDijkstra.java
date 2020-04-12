import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * 	   streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {

	DiGraph graph;
	int speedA;
	int speedB;
	int speedC;
	
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     * @throws FileNotFoundException 
    */
    CompetitionDijkstra (String filename, int sA, int sB, int sC)
    {
    	try {
    	File inputFile = new File(filename);
    	Scanner fileScanner;
    	fileScanner = new Scanner(inputFile);
    	graph = new DiGraph(fileScanner.nextInt());
    	speedA = sA;
    	speedB = sB;
    	speedC = sC;
    	fileScanner.nextLine(); 
    	fileScanner.nextLine();
    	while(fileScanner.hasNextLine())
    	{
    		int key1 = fileScanner.nextInt();
    		int key2 = fileScanner.nextInt();
    		double weight = fileScanner.nextDouble();
    		graph.addWeightedEdge(key1, key2, weight);
    		if(fileScanner.hasNextLine())
    		{
    			fileScanner.nextLine();
    		}
    	}
    	fileScanner.close();
    	} catch (FileNotFoundException e) {
    	} catch( NullPointerException e) {
    	}
    	
    }


    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
    */
    public int timeRequiredforCompetition()
    {
    	if(speedA < 50 || speedB < 50 || speedC < 50 || speedA > 100 || speedB > 100 || speedC > 100 || graph.numberOfVertices() == 0)
    	{
    		return -1;
    	}
    	double minimumMinutesToMeeting = -1;
    	double longestShortestDistance = -1;
    	int slowestSpeed = Math.min(speedA, Math.min(speedB, speedC));
    	double shortestDistance = -1;
    	for(int i = 0 ; i < graph.numberOfVertices() ; i++)
    	{
    		shortestDistance = Dijkstra.getLongestShortestDistance(graph, i);
    		if(shortestDistance == -1)
    		{
    			return -1;
    		}
    		longestShortestDistance = Math.max(longestShortestDistance, shortestDistance);
    	}
    	minimumMinutesToMeeting = longestShortestDistance*1000/slowestSpeed;
    	return (int)Math.ceil(minimumMinutesToMeeting);
    }
}