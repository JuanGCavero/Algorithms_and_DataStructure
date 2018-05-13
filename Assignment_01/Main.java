//Imports

// Class Main
public class Main 
{
	// Objects
	
	
	
    public static void main(String[] args)
    {
    	
        // ******* Input *******
        Tuple[] pairs = {new Tuple("a",14), new Tuple("b",20), new Tuple("c",7), new Tuple("d",10), new Tuple("e",23),
        				 new Tuple("f",5), new Tuple("g",16), new Tuple("h",3)};
        
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
        
        // LList is ordered
        myLinkedList = myLinkedList.bubleSort();
        
        // ******* Build Binary Tree ********
        while(myLinkedList.getFirstnode().getNextNode() != null)
        {
        	myLinkedList = myLinkedList.buildTree();
            // MyLinkedList.showMyLinkedList(myLinkedList);		// To see how the BT grows
            // System.out.println();        	
        }
		
        MyLinkedList copyLL = myLinkedList;
        
        String code = myLinkedList.CodeLetter(pairs[7].frequency);
        System.out.println(String.format("%s - %s", pairs[7].letter, code));
        
        String code2 = copyLL.CodeLetter(pairs[2].frequency);
        System.out.println(String.format("%s - %s", pairs[2].letter, code2));
         
        // _______ Coding our letters ________
//        for (int i = 0; i < input.length; i++) {
//            String code3 = MyLinkedList.CodeLetter(myLinkedList, pairs[i].frequency);
//            System.out.println(String.format("%s - %s", pairs[i].letter, code3));
//            myLinkedList = copyLL;
//		}
       
        
    }    
}