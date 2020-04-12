
public class DiGraph 
{
	private int numberOfVertices = -1;
	private Vertex[] graph; 
	
	DiGraph(int numberofVertices)
	{
		this.numberOfVertices = numberofVertices;
		graph = new Vertex[numberofVertices];
		for(int i = 0 ; i < numberOfVertices ; i++)
		{
			graph[i] = new Vertex(i, numberofVertices);
		}
	}
	
	public int numberOfVertices()
	{
		return numberOfVertices;
	}
	
	public Vertex[] graph()
	{
		return graph;
	}
	
	public Vertex graph(int index)
	{
		return this.graph[index];
	}
	
	public void addWeightedEdge(int originKey, int destinationKey, double weight) 
	{
		graph[originKey].addWeightedEdge(graph[destinationKey], weight);
	}
	
	public WeightedEdge getEdge(int originKey, int destinationKey)
	{
		return graph[originKey].getEdgeTo(destinationKey);
	}

	public boolean equals(DiGraph graph2)
	{
		if(this.numberOfVertices != graph2.numberOfVertices())
		{
			return false;
		}
		for(int i = 0 ; i < this.numberOfVertices ; i++)
		{
			Vertex vertex1 = this.graph(i);
			Vertex vertex2 = graph2.graph(i);
			if(!vertex1.equals(vertex2))
			{
				return false;
			}
		}
		return true;
	}
}
