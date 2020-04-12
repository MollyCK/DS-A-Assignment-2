import java.util.ArrayList;

public class VertexPriorityQueue {

	ArrayList<Vertex> queue;

	VertexPriorityQueue()
	{
		this.queue = new ArrayList<Vertex>();
	}

	public void enqueue(Vertex vertex)
	{
		if(this.queue.isEmpty())
		{
			this.queue.add(vertex);
		}
		else
		{
			for(int i = 0 ; i < this.queue.size(); i++)
			{
				if(vertex.distTo() < this.queue.get(i).distTo())
				{
					this.queue.add(i, vertex);
					return;
				}
			}
			this.queue.add(vertex);
		}
	}

	public Vertex dequeue()
	{
		return this.queue.remove(0);
	}
	
	public int length()
	{
		return this.queue.size();
	}
	
	public Vertex get(int index)
	{
		return this.queue.get(index);
	}
	
	public boolean isEmpty()
	{
		return this.queue.isEmpty();
	}

	public void updateOrder()
	{
		VertexPriorityQueue sortedQueue = new VertexPriorityQueue();
		for(int i = 0 ; i < this.queue.size(); i++)
		{
			sortedQueue.enqueue(this.queue.get(i));
		}
		this.queue = sortedQueue.queue;
	}
}
