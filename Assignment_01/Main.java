//Imports

// Class Main
public class Main 
{
    public static void main(String[] args)
    {
    	//Atributes
    	
        // ******* Input *******
        Tuple[] pairs = {new Tuple("a",14), new Tuple("b",20), new Tuple("c",7), new Tuple("d",10), new Tuple("e",23),
        				 new Tuple("f",5), new Tuple("g",16), new Tuple("h",3)};
        
        // ******* Actions *******
        //  Pairs are converted into Nodes 
        Node[] input = new Node[pairs.length];
        for (int i = 0; i < pairs.length; i++) {
        	input[i] = new Node(pairs[i].frequency);
		}
        
        MyLinkedList[] myLinkedList = new MyLinkedList[pairs.length];
        // I need as many LList as letter, for each letter I kill a BTree.
        // Loop:
        for (int i = 0; i < pairs.length; i++) {
        	
        	// Create LinkedList (one element to avoid NullException later)
            myLinkedList[i] = new MyLinkedList(input[0]);
            // Rest of nodes are added (without order)
            for (int j = 1; j < input.length; j++) {
            	MyLinkedList.addNodeAfter(input[j-1],input[j]);
    		}
            
            // LList is ordered
            myLinkedList[i] = MyLinkedList.bubleSort(myLinkedList[i]);
            
            // ******* Build Binary Tree ********
            while(myLinkedList[i].firstnode.nextNode != null)
            {
            	myLinkedList[i] = MyLinkedList.buildTree(myLinkedList[i]);
                // MyLinkedList.showMyLinkedList(myLinkedList);		// To see how the BT grows
                // System.out.println();        	
            }
		}

        // _______ Coding our letters ________
        for (int i = 0; i < input.length; i++) {
            String code = MyLinkedList.CodeLetter(myLinkedList[i], pairs[i].frequency);
            System.out.println(String.format("%s - %s", pairs[i].letter, code));
		}
       
        
    }    
}