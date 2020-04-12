
public class DijkstraResults {
	
	double[] distTo;
	WeightedEdge[] edgeTo;

	DijkstraResults(double[] distTo, WeightedEdge[] edgeTo)
	{
		this.distTo = distTo;
		this.edgeTo = edgeTo;
	}
	
	public double[] distTo()
	{
		return this.distTo;
	}
	
	public WeightedEdge[] edgeTo()
	{
		return edgeTo;
	}
}
