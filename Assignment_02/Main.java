import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
// Class Main
public class Main {

	public static void main(String[] args) {
		System.out.println("Juan González Cavero, 118928. Digital Engineering\n");
		System.out.println("Hi, I'm your QuickSorting application.");
		System.out.println("Type in as many integers as you want.");
		System.out.println("Press Ctrl+D or type in anything different to an integer to sort the numbers.");
		
		LinkedList<Integer> lkList = new LinkedList<Integer>();		
		Scanner scan = new Scanner(System.in);
		try {
			while(scan.hasNext()) {								
				lkList.add(scan.nextInt());
			}
		}catch(Exception e) {System.out.println("");}
		scan.close();
		
		System.out.println("\nSorted list of numbers:");
		LinkedList<Integer> lkSorted = new LinkedList<Integer>();
		try {
			lkSorted = QuickSort(lkList, lkSorted);
		}catch(Exception e) {System.out.println("No number was typed in.");}
		
		for (Integer number : lkSorted) {
			System.out.println(number);
		}
	}

	public static LinkedList<Integer> QuickSort(LinkedList<Integer> lkList, LinkedList<Integer> lkSorted) {

		// Choose an element randomly, "Pivot"
		Random rnd = new Random();
		int pivot = rnd.nextInt(lkList.size());

		// Elements smaller than Pivot go to the left,
		// bigger ones to the right.
		LinkedList<Integer> lkLeft = new LinkedList<Integer>();
		LinkedList<Integer> lkRight = new LinkedList<Integer>();
		for (Integer number : lkList) {
			if (number < lkList.get(pivot)) {
				lkLeft.addFirst(number);
			}
			if (number > lkList.get(pivot)) {
				lkRight.addFirst(number);
			}
		}

		// I already have left and right sets
		if (!lkLeft.isEmpty()) {
			// Break if in the set are two or less Elements
			if (lkLeft.size() == 1)
				lkSorted.add(lkLeft.getFirst());
			// Do the procedure recursively
			else {
				lkSorted = QuickSort(lkLeft, lkSorted);
			}
		}
		lkSorted.add(lkList.get(pivot));
		if (!lkRight.isEmpty()) {
			// Break if in the set are two or less Elements
			if (lkRight.size() == 1)
				lkSorted.add(lkRight.getFirst());
			// Do the procedure recursively
			else {
				lkSorted = QuickSort(lkRight, lkSorted);
			}
		}
		return lkSorted;
	}
}