// Class Node based on Lecture 1. Extracted from the class LinkedList to simplify.

// Class
public class Node
{
	// Atributes
	private int frequency;
	private Node leftChild;
	private Node rightChild;
	private Node nextNode;
	private Node previousNode;
	
	// Get and Set Methods
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public Node getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}
	public Node getRightChild() {
		return rightChild;
	}
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
	public Node getNextNode() {
		return nextNode;
	}
	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
	public Node getPreviousNode() {
		return previousNode;
	}
	public void setPreviousNode(Node previousNode) {
		this.previousNode = previousNode;
	}

	// Constructor
	public Node(int frequency)
	{
		this.frequency = frequency;
		this.leftChild = null;
		this.rightChild = null;
	}
}