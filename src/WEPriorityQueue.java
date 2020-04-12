import java.util.ArrayList;

public class WEPriorityQueue {

	ArrayList<WeightedEdge> queue;

	WEPriorityQueue()
	{
		this.queue = new ArrayList<WeightedEdge>();
	}

	public void enqueue(WeightedEdge edge)
	{
		if(this.queue.isEmpty())
		{
			this.queue.add(edge);
		}
		else
		{
			for(int i = 0 ; i < this.queue.size(); i++)
			{
				if(edge.weight() < this.queue.get(i).weight())
				{
					this.queue.add(i, edge);
					return;
				}
			}
			this.queue.add(edge);
		}
	}

	public WeightedEdge dequeue()
	{
		return this.queue.remove(0);
	}
	
	public int length()
	{
		return this.queue.size();
	}
	
	public WeightedEdge get(int index)
	{
		return this.queue.get(index);
	}
	
	public WeightedEdge getEdgeTo(int originKey, int destinationKey)
	{
		for(int i = 0 ; i < this.queue.size() ; i++)
		{
			if(this.queue.get(i).origin().key() == originKey && this.queue.get(i).destination().key() == destinationKey)
			{
				return this.queue.get(i);
			}
		}
		return null;
	}
}
