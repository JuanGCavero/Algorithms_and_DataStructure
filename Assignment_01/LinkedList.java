// Class
public class LinkedList
{
	// Atributes
	Node firstnode;

	// Inner classes
	public class NodeData
	{
		// Constructor
		public NodeData(NodeData copy)		// Data cell for Node
		{
		}
	}
	public class Node
	{
		// Atributes
		public NodeData data;
		public Node nextnode;		
		// Constructor
		public Node(NodeData newdat)
		{
			data = newdat;
			nextnode = null;
		}
	}
	
	// Methods
	public void initializeHead(NodeData thisdata)
	{
		Node newhead = new Node(thisdata);
		newhead.nextnode = newhead;			// Points to itself
	}
	public boolean CheckIfEmpty( )
	{
		boolean empty = false;
		if(this.firstnode == null)
			empty = true;
		return empty;
	}
	public void addNodeAfter(Node previousnode, Node tobeadded)
	{
			tobeadded.nextnode = previousnode.nextnode;
			previousnode.nextnode = tobeadded;
	}
	public void addNodeAtHead(Node tobeadded)
	{
		tobeadded.nextnode = this.firstnode;
		firstnode = tobeadded ;
	}
	public void removeNode(Node previousnode, Node toberemoved)
	{
		previousnode.nextnode =	toberemoved.nextnode;
	}
	public Node SearchNode(Node tobeseeked)
	{
		NodeData data;
		Node jumptonext = firstnode;;
		Node previousnode = null;
		while(previousnode != jumptonext || jumptonext.data != tobeseeked.data)
		{
			previousnode = jumptonext;
			jumptonext = jumptonext.nextnode;
		}
		if(previousnode == jumptonext)
		{
			previousnode = null;
			jumptonext = null;
		}
		return(previousnode);  				// Inevitably modified from the slides
	}
	
	
	// Constructor
	public LinkedList(Node newhead)
	{
		//initialize LinkedList anew
		newhead.nextnode = newhead;		// 
		firstnode = newhead;
	}
}
