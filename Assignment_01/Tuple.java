// Class
public class Tuple
{
    // Atributes
    public String letter;
    public int frequency;


    //Constructor
    public Tuple(String letter, int frequency)
    {
        this.letter = letter;
        this.frequency = frequency;
    }

    // Methods
    public static void showNodes(Tuple[] tuple)
    {
    	for (int i = 0; i < tuple.length; i++) 
    	{
    		System.out.println(String.format("%s%5d", tuple[i].letter, tuple[i].frequency));
		}
    	System.out.println();
    }
    public static Tuple[] bubleSort(Tuple[] input)
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
					Tuple aux = input[i];
					input[i] = input[i+1];
					input[i+1] = aux;
				}
			}
    	}while(sorted == false);
    	return input;
    }
    
}