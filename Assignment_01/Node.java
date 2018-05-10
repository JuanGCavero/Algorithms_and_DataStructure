// Class Node based on Lecture 1. Extracted from the class LinkedList to simplify.

// Class
public class Node
{
	// Atributes
	public int frequency;
	public Node leftChild;
	public Node rightChild;
	public Node nextNode;
	public Node previousNode;
	
	// Constructor
	public Node(int frequency)
	{
		this.frequency = frequency;
		this.leftChild = null;
		this.rightChild = null;
	}
	
    // Methods (not used anymore)
    public static void showNodes(Node[] node)
    {
    	for (int i = 0; i < node.length; i++) 
    	{
    		System.out.println(String.format("%5d", node[i].frequency));
		}
    	System.out.println();
    }
    public static Node[] bubleSort(Node[] input)
    {
    	boolean sorted = false;
    	do
    	{
    		sorted = true;
    		for (int i = 0; i < input.length-1; i++) 
    		{
				if(input[i].frequency > input[i+1].frequency)
				{
					sorted = false;
					Node aux = input[i];
					input[i] = input[i+1];
					input[i+1] = aux;
				}
			}
    	}while(sorted == false);
    	return input;
    }

}