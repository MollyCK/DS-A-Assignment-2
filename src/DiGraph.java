import java.util.ArrayList;

public class DiGraph 
{
	private int numberOfVertices = -1;
	private Vertex[] graph; 
	
	private class Vertex 
	{
		private WeightedEdge[] outgoingEdges;
		private int key;
		
		Vertex(int key)
		{
			this.key = key;
			outgoingEdges = new WeightedEdge[numberOfVertices];
		}
		
		private int key() 
		{
			return key;
		}
		
		private void addWeightedEdge(Vertex destination, double weight) 
		{
			outgoingEdges[destination.key()] = new WeightedEdge(this, destination, weight);
		}
		
		private WeightedEdge getEdgeTo(int destinationKey)
		{
			return outgoingEdges[destinationKey];
		}
		
		public boolean equals(Vertex vertex2)
		{
			if(this.key != vertex2.key() || this.outgoingEdges.length != vertex2.outgoingEdges.length)
			{
				return false;
			}
			for(int i = 0 ; i < this.outgoingEdges.length ; i++)
			{
				if(this.outgoingEdges[i] != null) {
					if(!this.outgoingEdges[i].equals(vertex2.getEdgeTo(i)))
					{
						return false;
					}
				}
			}
			return true;
		}
	}
	
	private class WeightedEdge 
	{
		private Vertex origin;
		private Vertex destination;
		private double weight = -1;
		
		WeightedEdge(Vertex origin, Vertex destination, double weight) 
		{
			this.origin = origin;
			this.destination = destination;
			this.weight = weight;
		}
		
		private double weight()
		{
			return weight;
		}
		
		private Vertex origin()
		{
			return origin;
		}
		
		private Vertex destination()
		{
			return destination;
		}
		
		public boolean equals(WeightedEdge edge2)
		{
			if(this.origin.key() == edge2.origin().key() && this.destination.key() == edge2.destination().key() &&
					this.weight == edge2.weight())
			{
				return true;
			}
			else return false;
		}
	} 
	
	DiGraph(int numberofVertices)
	{
		this.numberOfVertices = numberofVertices;
		graph = new Vertex[numberofVertices];
		for(int i = 0 ; i < numberOfVertices ; i++)
		{
			graph[i] = new Vertex(i);
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
