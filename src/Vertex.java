
public class Vertex {
	
//	private int numberOfAttachedVertices;
	private WEPriorityQueue outgoingEdges;
	private int key;
	private double distTo = Double.MAX_VALUE;
	
	Vertex(int key, int numberOfAttachedVertices)
	{
		this.key = key;
//		this.numberOfAttachedVertices = numberOfAttachedVertices;
		outgoingEdges = new WEPriorityQueue();
	}
	
	public double distTo()
	{
		return distTo;
	}
	
	public void setDistTo(double newVal)
	{
		this.distTo = newVal;
	}
	
	public int key() 
	{
		return key;
	}
	
	public WEPriorityQueue outgoingEdges()
	{
		return outgoingEdges;
	}
	
	public void addWeightedEdge(Vertex destination, double weight) 
	{
		outgoingEdges.enqueue(new WeightedEdge(this, destination, weight));
	}
	
	public WeightedEdge getEdgeTo(int destinationKey)
	{
		return outgoingEdges.getEdgeTo(this.key, destinationKey);
	}
	
	public boolean equals(Vertex vertex2)
	{
		if(this.key != vertex2.key() || this.outgoingEdges.length() != vertex2.outgoingEdges.length())
		{
			return false;
		}
		for(int i = 0 ; i < this.outgoingEdges.length() ; i++)
		{
			if(this.outgoingEdges.get(i) != null) {
				if(!this.outgoingEdges.get(i).equals(vertex2.outgoingEdges.get(i)))
				{
					return false;
				}
			}
		}
		return true;
	}
}
