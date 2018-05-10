// Class LinkedList from Lecture 1 with some more methods implemented

// Class
public class MyLinkedList
{
	// Atributes
	Node firstnode;

	// Methods from Lecture 1
	public static void initializeHead(int freq)
	{
		Node newhead = new Node(freq);
		newhead.nextNode = newhead;			// Points to itself
	}
	public boolean CheckIfEmpty( )
	{
		boolean empty = false;
		if(this.firstnode == null)
			empty = true;
		return empty;
	}
	public static void addNodeAfter(Node prevNode, Node tobeadded)
	{
			tobeadded.nextNode = null;
			prevNode.nextNode = tobeadded;
	}
	public static void addNodeAtHead(MyLinkedList myLinkedList, Node tobeadded)
	{
		tobeadded.nextNode = myLinkedList.firstnode;
		myLinkedList.firstnode = tobeadded ;
	}
	public static void removeTwoFirstNodes(MyLinkedList myLinkedList)
	{
		myLinkedList.firstnode = myLinkedList.firstnode.nextNode.nextNode;
	}
	public static void showMyLinkedList(MyLinkedList linkedList)
	{
		Node currentNode = linkedList.firstnode;
		while(currentNode != null)
		{
			System.out.println(String.format("%s", currentNode.frequency));
			currentNode = currentNode.nextNode;			
		}		
	}
	
	// Methods implemented by myself
	public static MyLinkedList bubleSort(MyLinkedList myLinkedList)
    {
		// Following intructions from the slides of Lecture 2
    	boolean sorted = false;
    	do
    	{
    		sorted = true;
    		Node currentNode = myLinkedList.firstnode;
    		while(currentNode.nextNode != null)
    		{
				if(currentNode.frequency > currentNode.nextNode.frequency)
				{
					sorted = false;
					// Frequency (int), r&l-Child Nodes of currentNode are saved
					int aux = currentNode.frequency;
					Node auxRight = currentNode.rightChild;
					Node auxLeft = currentNode.leftChild;
					
					// Data from the next node is passed to the current node
					currentNode.frequency = currentNode.nextNode.frequency;
					currentNode.rightChild = currentNode.nextNode.rightChild;
					currentNode.leftChild = currentNode.nextNode.leftChild;
					
					// Next node is refill with the saved data
					currentNode.nextNode.frequency = aux;
					currentNode.nextNode.rightChild = auxRight;
					currentNode.nextNode.leftChild = auxLeft;
					
				}
				currentNode = currentNode.nextNode;
			}
    	}while(sorted == false);
    	return myLinkedList;
    }
	public static MyLinkedList buildTree(MyLinkedList myLinkedList)
	{
		// Binary Tree is created consisting in 2 childs, right & left,
		// and a head (or root) of both of them.
		
		int minor = myLinkedList.firstnode.frequency;				// LList sorted, so firstNode is the smallest
		int bigger = myLinkedList.firstnode.nextNode.frequency;
		Node headNode = new Node(minor + bigger);					// Root is the sum o the first two members
		Node restNode = myLinkedList.firstnode.nextNode.nextNode;	// I save this node bcause I delete the two in the front
		
		headNode.rightChild = myLinkedList.firstnode;				// Smallest freq		
		headNode.leftChild = myLinkedList.firstnode.nextNode;		// Second smallest
		
		headNode.rightChild.nextNode = null;
		headNode.leftChild.nextNode = null;							// Not needed anymore
		
		addNodeAtHead(myLinkedList, headNode);						// headNode of r&l-childs (BTree), added to the LList
		headNode.nextNode = restNode;								// My Llist have again all members except for 2smallest
		myLinkedList = MyLinkedList.bubleSort(myLinkedList);		// LinkedList members sorted
		return myLinkedList;
	}
	public static String CodeLetter(MyLinkedList myLinkedList, int freq)
	{
		MyLinkedList bTree = myLinkedList;
		String code = "";
		Node currentNode = bTree.firstnode;		// Root of Binary Tree
		do {
			boolean right = true;
			// 1. Existence of leafs is checked.
			// 2. Go right as deep as possible, if not possible, go left, otherwise go up.
			// 3. previousNode is created to be able to go up in the Binary Tree
			// 4. If the reached leaf is not the objective, it is deleted.
			// 5. If the only Node alive is firstNode, there is no such a frequency
			while(currentNode.rightChild != null || currentNode.leftChild != null)
			{
				if(currentNode.rightChild != null)	
				{
					right = true;
					currentNode.rightChild.previousNode = currentNode;
					currentNode = currentNode.rightChild;
					code += "1 ";	
					// System.out.println(code);	// To see the way of the search
				}
				else
				{
					right = false;
					currentNode.leftChild.previousNode = currentNode;
					currentNode = currentNode.leftChild;
					code += "0 ";	
					// System.out.println("0 ");
				}
			}
			if(currentNode.frequency == freq)
			{
				return code;
			}
			else if(currentNode == myLinkedList.firstnode)
			{
				return "There is not such a frequency";	
			}
			else
			{
				if(right)
				{
					currentNode = currentNode.previousNode;
					currentNode.rightChild = null;
					code = code.substring(0, code.length()-2);
				}
				else
				{
					currentNode = currentNode.previousNode;
					currentNode.leftChild = null;
					code = code.substring(0, code.length()-2);
				}
			}
		} while(bTree.firstnode != null);
		
		return "There is not such a frequency";		
	}

	// Constructor
	public MyLinkedList(Node newhead)
	{
		//initialize LinkedList anew
		newhead.nextNode = newhead;	
		firstnode = newhead;
	}
	
	// Constructor
	public MyLinkedList()
	{

	}
}