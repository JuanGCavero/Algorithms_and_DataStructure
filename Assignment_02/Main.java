import java.util.LinkedList;
import java.util.Random;

// Class Main
public class Main {

	public static void main(String[] args) {
		System.out.println("Juan González Cavero, 118928");
		System.out.println("Digital Engineering");

		LinkedList<Integer> lkList = new LinkedList<Integer>();
		// Arbitrary numbers
		lkList.add(83);
		lkList.add(21);
		lkList.add(121);
		lkList.add(8);
		lkList.add(4);
		lkList.add(40);
		lkList.add(112);
		lkList.add(1);
		lkList.add(98);
		lkList.add(67);
		lkList.add(33);

		LinkedList<Integer> lkShorted = new LinkedList<Integer>();

		lkList = QuickShort(lkList, lkShorted);

		for (Integer number : lkList) {
			System.out.println(number);
		}
	}

	public static LinkedList<Integer> QuickShort(LinkedList<Integer> lkList, LinkedList<Integer> lkShorted) {

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
				lkShorted.add(lkLeft.getFirst());
			// Do the procedure recursively
			else {
				QuickShort(lkLeft, lkShorted);
			}
		}
		lkShorted.add(lkList.get(pivot));
		if (!lkRight.isEmpty()) {
			// Break if in the set are two or less Elements
			if (lkRight.size() == 1)
				lkShorted.add(lkRight.getFirst());
			// Do the procedure recursively
			else {
				QuickShort(lkRight, lkShorted);
			}
		}
		return lkShorted;
	}
}
