
public class Vertex {
	
	private WEPriorityQueue outgoingEdges;
	private int key;
	private double distTo = Double.MAX_VALUE;
	
	Vertex(int key)
	{
		this.key = key;
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
	
	public void resetDistTo()
	{
		this.distTo = Double.MAX_VALUE;
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

	public boolean equals(Vertex vertex2)
	{
		if(vertex2 == null || this.key != vertex2.key() || this.outgoingEdges.length() != vertex2.outgoingEdges.length())
		{
			return false;
		}
		for(int i = 0 ; i < this.outgoingEdges.length() ; i++)
		{
			if(!this.outgoingEdges.get(i).equals(vertex2.outgoingEdges.get(i)))
			{
				return false;
			}
		}
		return true;
	}
	
	public void resetEdges()
	{
		this.outgoingEdges.reset();
	}
}
