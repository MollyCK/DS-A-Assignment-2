

public class Dijkstra {

	private static DijkstraResults run(DiGraph graph, int sourceKey) 
	{
		double[] distTo = new double[graph.numberOfVertices()];
		for(int i = 0 ; i < distTo.length ; i++)
		{
			distTo[i] = Double.MAX_VALUE;
		}
		distTo[sourceKey] = 0;
		
		VertexPriorityQueue vertexQueue = new VertexPriorityQueue();
		for(int i = 0 ; i < graph.graph().length ; i++)
		{
			vertexQueue.enqueue(graph.graph(i));
		}
		graph.graph(sourceKey).setDistTo(0);
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
		return new DijkstraResults(distTo, edgeTo);
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
	
	public static double getShortestDistance(DiGraph graph, int source)
	{
		run(graph, source);
		return -1;
	}
}
