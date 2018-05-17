//Imports

// Class Main
public class Main 
{
	// Objects	
	
    public static void main(String[] args)
    {
    	
        // ******* Input *******
        Tuple[] pairs = {	new Tuple("a",14), new Tuple("b",20), new Tuple("c",7),
        					new Tuple("d",10), new Tuple("e",23), new Tuple("f",5),
        					new Tuple("g",16), new Tuple("h",3)};
        
        // ******* Actions *******
        // Pairs are converted into Nodes 
        Node[] input = new Node[pairs.length];
        for (int i = 0; i < pairs.length; i++) {
        	input[i] = new Node(pairs[i].frequency);
		}

    	// Create LinkedList (only first element to avoid NullException later)
        MyLinkedList myLinkedList = new MyLinkedList(input[0]);
        
        // Rest of nodes are added (without order)
        for (int j = 1; j < input.length; j++) {
        	myLinkedList.addNodeAtHead(input[j]);
		}
        
        // myLinkedList is ordered
        myLinkedList = myLinkedList.bubleSort();
        
        // ******* Build Binary Tree ********
        while(myLinkedList.getFirstnode().getNextNode() != null)
        {
        	myLinkedList = myLinkedList.buildTree();
            // MyLinkedList.showMyLinkedList(myLinkedList);		// To see how the BT grows
            // System.out.println();        	
        }
		
        // ******* Coding our letters *******
        for (int i = 0; i < input.length; i++) {
            String code = myLinkedList.CodeMyLetter(pairs[i].frequency, myLinkedList.getFirstnode(), "" );
            System.out.println(String.format("%s -%s", pairs[i].letter, invertString(code)));
		}
               
    }
    
    public static String invertString(String str)
    {
    	String inv = "";
    	for (int i = 0; i < str.length(); i++) {
			inv += str.substring(str.length()-i-1, str.length()-i);
		}
    	return inv;
    }

}