
public class WeightedEdge {
	private Vertex origin;
	private Vertex destination;
	private double weight = -1;
	
	WeightedEdge(Vertex origin, Vertex destination, double weight) 
	{
		this.origin = origin;
		this.destination = destination;
		this.weight = weight;
	}
	
	public double weight()
	{
		return weight;
	}
	
	public Vertex origin()
	{
		return origin;
	}
	
	public Vertex destination()
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
