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

/*
Problem description:
A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random city intersections. 
In order to win, the three contestants need all to meet at any intersection of the city as fast as possible.
The contestants may arrive at the intersections at different times, in which case, the first to arrive can wait 
until the others arrive.
From an estimated walking speed for each one of the three contestants, ACM wants to determine the minimum time 
that a live TV broadcast should last to cover their journey regardless of the contestants’ initial positions and 
the intersection at which they finally meet. You are hired to help ACM answer this question.

You may assume the following:
• Each contestant walks at a given estimated speed.
• The city is a collection of intersections in which some pairs are connected by one-way streets that the 
  contestants can use to traverse the city. Two intersections can be connected by two one-way streets allowing 
  travel in opposite directions of each other.
*/

public class CompetitionDijkstra {

	DiGraph graph;
	int speedA;
	int speedB;
	int speedC;
	
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    *//*
 * #intersection = #vertex = N = line 1 of file
 * #street = #edge = S = line 2 of file
 * lines 3...k = pairs :
 * 						1st int = vertexKey 1
 * 						2nd int = vertexKey 2
 * 						3rd double = edge weight between vertex 1 and vertex 2 = distance in km
 * sA, sB, sC = contestant speed respectively in metres/minute between 50 & 100 inclusive
 */
    CompetitionDijkstra (String filename, int sA, int sB, int sC)
    {
    	File inputFile = new File("src/tinyEWD.txt");
    	Scanner fileScanner;
    	try {
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
    			fileScanner.nextLine();
    		}
    	} catch (FileNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }


    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
/*
	public int timeRequiredforCompetition()- this method should return an integer indicating the minimum 
	number of minutes that will pass before the three contestants can meet in the city generated in your 
	constructor, if they start to walk immediately after the show starts. Remember that the contestants 
	can be originally located at any random (unknown) intersection and can decide to meet at any random 
	unknown intersection: you need to account for the worst case scenario. The answer should be given 
	rounding decimals to the next integer (e.g., 2.9 minutes rounds up to 3 minutes and 3.2 minutes rounds
	up to 4 minutes). If it is not possible to run the given competition in a given city represented by 
	the map you generated (i.e., if there are 2 random locations in a city between which no path exists), 
	the method should return -1, as should for any other errors (eg input walking speeds outside the 
	specified range). To implement this method you have to use Dijkstra’s shortest path algorithm.
*/
    /*
     * Run Dijkstra for all the vertices as sources and multiply the longest of these shortest distances by the slowest 
     * contestant's speed
     */
    public int timeRequiredforCompetition()
    {
    	double minimumMinutesToMeeting = -1;
    	double longestShortestDistance = -1;
    	int slowestSpeed = Math.min(speedA, Math.min(speedB, speedC));
    	for(int i = 0 ; i < graph.numberOfVertices() ; i++)
    	{
    		double ithShortestDistance = Dijkstra.getShortestDistance(graph, i);
    		if (ithShortestDistance > longestShortestDistance)
    		{
    			longestShortestDistance = ithShortestDistance;
    		}
    		
    	}
    	
    	
    	minimumMinutesToMeeting = longestShortestDistance*slowestSpeed;
        
        return (int)Math.ceil(minimumMinutesToMeeting);
    }

}
