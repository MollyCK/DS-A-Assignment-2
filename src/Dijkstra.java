
public class Dijkstra {

	private static double[] run(DiGraph graph, int sourceKey) 
	{
		double[] distTo = new double[graph.numberOfVertices()];
		for(int i = 0 ; i < distTo.length ; i++)
		{
			distTo[i] = Double.MAX_VALUE;
		}
		distTo[sourceKey] = 0;
		
		VertexPriorityQueue vertexQueue = new VertexPriorityQueue();
		graph.graph(sourceKey).setDistTo(0);
		for(int i = 0 ; i < graph.graph().length ; i++)
		{
			if(i != sourceKey) graph.graph(i).resetDistTo();
			graph.graph(i).resetEdges();
			vertexQueue.enqueue(graph.graph(i));
		}
		
		WeightedEdge[] edgeTo = new WeightedEdge[graph.numberOfVertices()];
		boolean[] relaxed = new boolean[graph.numberOfVertices()];//this array will note whether or not a vertex has been visited and relaxed already, to prevent the algorithm back-tracking to vertices it has already covered
		for(int i = 0 ; i < relaxed.length ; i++)
		{
			relaxed[i] = false;
		}
		relaxVertex(vertexQueue.dequeue(), distTo, edgeTo, relaxed, vertexQueue);
		while(!vertexQueue.isEmpty())
		{
			relaxVertex(vertexQueue.dequeue(), distTo, edgeTo, relaxed, vertexQueue);
		}
		//check for any infinities still in distTo which indicate that a vertex is unreachable
		for(int i = 0 ; i < distTo.length ; i++)
		{
			if(distTo[i] == Double.MAX_VALUE)
			{
				return null;
			}
		}
		
		return distTo;
	}
	
	private static void relaxVertex(Vertex vertex, double[] distTo, WeightedEdge[] edgeTo, boolean[] relaxed, VertexPriorityQueue vertexQueue)
	{
		while(vertex.outgoingEdges().length() != 0)
		{
			relaxEdge(vertex.outgoingEdges().dequeue(), distTo, edgeTo);
		}
		vertexQueue.updateOrder();//need to fix the ordering as the distTo values change for each vertex
		relaxed[vertex.key()] = true;
	}
	

	private static void relaxEdge(WeightedEdge edge,double[] distTo, WeightedEdge[] edgeTo) 
	{
		if(edge.destination().distTo() > edge.origin().distTo() + edge.weight())
		{
			distTo[edge.destination().key()] = distTo[edge.origin().key()] + edge.weight();
			edge.destination().setDistTo(edge.origin().distTo() + edge.weight());
			edgeTo[edge.destination().key()] = edge;
		}
	}
	
	public static double getLongestShortestDistance(DiGraph graph, int source)
	{
		double[] shortestDistances = run(graph, source);
		if(shortestDistances == null)
		{
			return -1;
		}
		double shortestDistTo = Double.MIN_VALUE;
		for(int i = 0 ; i < shortestDistances.length ; i++)
		{
			if(i != source)
			{
				shortestDistTo = Math.max(shortestDistTo, shortestDistances[i]);
			}
		}		
		return shortestDistTo;
	}
}
