import java.util.ArrayList;

public class WEPriorityQueue {

	ArrayList<WeightedEdge> queue;
	ArrayList<WeightedEdge> backup;

	WEPriorityQueue()
	{
		this.queue = new ArrayList<WeightedEdge>();
		this.backup = new ArrayList<WeightedEdge>();;
	}

	public void enqueue(WeightedEdge edge)
	{
		if(this.queue.isEmpty())
		{
			this.queue.add(edge);
			this.backup.add(edge);
		}
		else
		{
			for(int i = 0 ; i < this.queue.size(); i++)
			{
				if(edge.weight() < this.queue.get(i).weight())
				{
					this.queue.add(i, edge);
					this.backup.add(i, edge);
					return;
				}
			}
			this.queue.add(edge);
			this.backup.add(edge);
		}
	}

	public WeightedEdge dequeue()
	{
		WeightedEdge removedEdge = this.queue.remove(0);
//		this.backup.add(removedEdge);
		return removedEdge;
	}
	
	public int length()
	{
		return this.queue.size();
	}
	
	public WeightedEdge get(int index)
	{
		return this.queue.get(index);
	}
	
	public void reset()
	{
		for(int i = 0 ; i < this.backup.size() ; i++)
		{
			this.queue.add(this.backup.get(i));
		}
	}
}
