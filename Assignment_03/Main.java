import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Juan Gonzalez Cavero, 118928, Digital Engineering.");
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Type anything you want:");		
		String input = scan.nextLine();
		System.out.println("Type a pattern to be found in the text:");
		String pattern = scan.nextLine();
		boolean show;
		System.out.println("Do you want to see the Hash values? Type: 'y'(Yes) or 'other'(No)");
		if(scan.nextLine().equals("y"))
			show = true;
		else
			show = false;
		scan.close();
		
		int d = NumberOfSimbols(input);
		int m = pattern.length();
		
		// Hash Pattern
		int kp = MappingSubString(pattern, d);
		int p = MaxPrimeNumber(kp); // 
		int Hp = kp % p;
		System.out.println("Hash of pattern = "+ Hp);
		
		// Hash Input and show results
		HashInput(input,d,m,Hp,p,show);		
	}
		
	
	public static int NumberOfSimbols(String input) {
		int d=0;		
		for (int i = 0; i < input.length(); i++) {
			for (int j = 0; j < i; j++) {
				if(input.charAt(i) == input.charAt(j)) {
					d--; break;
				}
			}
			d++;			
		}		
		return d;
	}
	
	public static int[] SimbolsToAscii(String input) {
		int[] inputASCII = new int[input.length()];
		for (int i = 0; i < input.length(); i++) {
			inputASCII[i] = (int)input.charAt(i);
		}
		return inputASCII;
	}
	
	public static int MappingSubString(String s, int d)
	{
		// Represent symbols as Integers, patternASCII ==> a
		// Mapping the subString
		int[] a = SimbolsToAscii(s);
		int k = 0;
		for (int i = 0; i < a.length; i++) {
			k += a[i] * (int) (Math.pow(d, a.length-i-1));
		}		
		return k;
	}
	
	public static int MaxPrimeNumber(int n) {
		boolean isPrime;
		for(int i = n; i > 0; i--) {
			isPrime = true;
			for (int j = 2; j < i; j++) {
				if(i%j == 0) {
					isPrime = false; break;
				}
			}
			if(isPrime && i > 1)
				return i;
		}
		return 37;
	}

	public static void HashInput(String input, int d, int m, int Hp, int p, boolean show) {
		// First: represent symbols as Integers. (ascii)
		// Split input in subStrings
		// Map subStrings
		// Hash function of subStrings
		
		String result = "";
		int counter =0;

		for (int i = 0; i < input.length()-m; i++) {
			
			String subString = input.substring(i, i+m);
			int k = MappingSubString(subString, d);
			int Hi = k % p;
			// HashNext. Not performed. Anyway, I have to calculate k again.
			//Hi = ((k - a[i-1]* (int)(Math.pow(d, m-1)))*d+ a[i-1+m]) % p;

			Hi = k%p;
			if(show == true) 
				System.out.println("Hash of H("+(i+1)+") = "+Hi);
			if(Hi == Hp) {				
				result += "Position: "+(i+1)+"\n";
				counter++;
			}
		}		
		System.out.println(counter + " times found:");
		System.out.println(result);
	}
	
	//Not used
	public static void HashInputNext(String input, int d, int m, int Hp, int p) {
			// First: represent symbols as Integers. (ascii)
			// Mapping the first subString
			// Find  Maximum prime number
			// Hash function of the first subString
			// Hashing next subStrings	
			
			String result = "";
			//int[] a = SimbolsToAscii(input);
			
			// First subString of our input with legth = m
			String subString = input.substring(0, m);
			int k = MappingSubString(subString, d);
			
			int Hi = k % p;
			
//			int[] a = SimbolsToAscii(subInput);
//			int k = MappingSubstring(a, d);
//			int p = 89; //MaxPrimeNumber(k);
//			int H = k%p;
			System.out.println("Hash of H(1) = "+Hi);
			int counter =0;
			if(Hi == Hp) {
				System.out.println("Position: 1");			
				counter++;
			}
			
			// Rest of subStrings with legth = m
			for (int i = 1; i < input.length()-m; i++) {
				
				subString = input.substring(i, i+m);
				
				// HashNext. Not performed. Anyway, I have to calculate k again.
				//Hi = ((k - a[i-1]* (int)(Math.pow(d, m-1)))*d+ a[i-1+m]) % p;
				
				k = MappingSubString(subString, d);
				Hi = k%p;
				System.out.println("Hash of H("+(i+1)+") = "+Hi);
				if(Hi == Hp) {				
					result += "Position: "+(i+1)+"\n";
					counter++;
				}
			}		
			System.out.println(counter + " times found:");
			System.out.println(result);
		}
		
}
