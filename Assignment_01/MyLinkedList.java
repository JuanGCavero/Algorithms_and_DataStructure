// Class LinkedList from Lecture 1 with some more methods implemented

// Class
public class MyLinkedList
{
	// Atributes
	private Node firstnode;

	// Methods from Lecture 1
	public void initializeHead(int freq)
	{
		Node newhead = new Node(freq);
		newhead.setNextNode(newhead);			// Points to itself
	}
	public boolean CheckIfEmpty( )
	{
		boolean empty = false;
		if(this.firstnode == null)
			empty = true;
		return empty;
	}
	public void addNodeAfter(Node prevNode, Node tobeadded)
	{
			tobeadded.setNextNode(null);
			prevNode.setNextNode(tobeadded);
	}
	public void addNodeAtHead(Node tobeadded)
	{
		tobeadded.setNextNode(this.firstnode);
		this.firstnode = tobeadded ;
	}
	public void removeTwoFirstNodes()
	{
		this.firstnode = this.firstnode.getNextNode().getNextNode();
	}
	public void showMyLinkedList()
	{
		Node currentNode = this.firstnode;
		while(currentNode != null)
		{
			System.out.println(String.format("%s", currentNode.getFrequency()));
			currentNode = currentNode.getNextNode();			
		}		
	}
	
	// Methods implemented by myself
	public MyLinkedList bubleSort()
    {
		// Following intructions from the slides of Lecture 2
    	boolean sorted = false;
    	do
    	{
    		sorted = true;
    		Node currentNode = this.firstnode;
    		while(currentNode.getNextNode() != null)
    		{
				if(currentNode.getFrequency() > currentNode.getNextNode().getFrequency())
				{
					sorted = false;
					// Frequency (int), r&l-Child Nodes of currentNode are saved
					int aux = currentNode.getFrequency();
					Node auxRight = currentNode.getRightChild();
					Node auxLeft = currentNode.getLeftChild();
					
					// Data from the next node is passed to the current node
					currentNode.setFrequency(currentNode.getNextNode().getFrequency());
					currentNode.setRightChild(currentNode.getNextNode().getRightChild());
					currentNode.setLeftChild(currentNode.getNextNode().getLeftChild());
					
					// Next node is refill with the saved data
					currentNode.getNextNode().setFrequency(aux);
					currentNode.getNextNode().setRightChild(auxRight);
					currentNode.getNextNode().setLeftChild(auxLeft);					
				}
				currentNode = currentNode.getNextNode();
			}
    	}while(sorted == false);
    	return this;
    }
	public MyLinkedList buildTree()
	{
		// Binary Tree is created consisting in 2 childs, right & left,
		// and a head (or root) of both of them.
		
		int minor = this.firstnode.getFrequency();				// LList sorted, so firstNode is the smallest
		int bigger = this.firstnode.getNextNode().getFrequency();
		Node headNode = new Node(minor + bigger);			// Root is the sum o the first two members
		Node restNode = this.firstnode.getNextNode().getNextNode();	// I save this node bcause I delete the two in the front
		
		headNode.setRightChild(this.firstnode);				// Smallest freq		
		headNode.setLeftChild(this.firstnode.getNextNode());		// Second smallest
		
		headNode.getRightChild().setNextNode(null);
		headNode.getLeftChild().setNextNode(null);				// Not needed anymore
		
		this.addNodeAtHead(headNode);						// headNode of r&l-childs (BTree), added to the LList
		headNode.setNextNode(restNode);						// My Llist have again all members except for 2smallest
		
		return this.bubleSort();							// LinkedList members sorted
	}
	public String CodeLetter(int freq)
	{
		MyLinkedList bTree = this;
		String code = "";
		Node currentNode = bTree.firstnode;		// Root of Binary Tree
		do {
			boolean right = true;
			// 1. Existence of leafs is checked.
			// 2. Go right as deep as possible, if not possible, go left, otherwise go up.
			// 3. previousNode is created to be able to go up in the Binary Tree
			// 4. If the reached leaf is not the objective, it is deleted.
			// 5. If the only Node alive is firstNode, there is no such a frequency
			while(currentNode.getRightChild() != null || currentNode.getLeftChild() != null)
			{
				if(currentNode.getRightChild() != null)	
				{
					right = true;
					currentNode.getRightChild().setPreviousNode(currentNode);
					currentNode = currentNode.getRightChild();
					code += "1 ";	
					// System.out.println(code);	// To see the way of the search
				}
				else
				{
					right = false;
					currentNode.getLeftChild().setPreviousNode(currentNode);
					currentNode = currentNode.getLeftChild();
					code += "0 ";	
					// System.out.println("0 ");
				}
			}
			if(currentNode.getFrequency() == freq)
			{
				return code;
			}
			else if(currentNode == this.firstnode)
			{
				return "There is not such a frequency";	
			}
			else
			{
				if(right)
				{
					currentNode = currentNode.getPreviousNode();
					currentNode.setRightChild(null);
					code = code.substring(0, code.length()-2);
				}
				else
				{
					currentNode = currentNode.getPreviousNode();
					currentNode.setLeftChild(null);
					code = code.substring(0, code.length()-2);
				}
			}
		} while(bTree.firstnode != null);
		
		return "There is not such a frequency";		
	}
	
	
	public Node getFirstnode() {
		return firstnode;
	}
	public void setFirstnode(Node firstnode) {
		this.firstnode = firstnode;
	}
	
	// Constructor
	public MyLinkedList(Node newhead)
	{
		//initialize LinkedList anew
		// newhead.nextNode = newhead;	
		firstnode = newhead;
	}
	public MyLinkedList()
	{
	}
}