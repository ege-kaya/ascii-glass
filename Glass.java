package glass;

public class Glass 
{
	public static void main(String[] args) 
	{
		int glassSize = Integer.parseInt(args[0]); // Stores the first argument passed in by the user in an int variable called glassSize
		int strawPos  = Integer.parseInt(args[1]); // Stores the second argument passed in by the user in an int variable called strawPos
		
		// Prints out the expected output
		glass(glassSize, strawPos);
	}
	
	public static void glass(int glassSize, int strawPos)
	{
		// Save the initial value of glassSize inside another variable because we'll later make operations on glassSize
		int initialGlassSize = glassSize;
		
		// Get the total number of glasses to print from returnNumberOfGlasses method and assign it to an integer called totalGlasses
		int totalGlasses = returnNumberOfGlasses(glassSize, strawPos);
		
		// A for-loop that will print out NumberOfGlasses many glasses. 
		for (int currentGlass = 1; currentGlass <= totalGlasses; currentGlass++) // currentGlass shows at any time the number of the glass currently being printed
		{
			// For each time the loop iterates, we need to set the glassSize variable back to its original value
			glassSize = initialGlassSize;
			
			// Call the straw method to print out part of the straw sticking out from the top
			straw(strawPos);
		
			int currentTotalRow = 0; // Declare a variable of type int which will store the number of the total (counting both empty and full rows) row currently being printed 
			int currentEmptyRow = 0; // Declare a variable of type int which will store the number of the empty row currently being printed 
			int totalRightSpaces = ((2 * glassSize) - strawPos); // Declare a variable of type int which shows the number of spaces to be printed to the right of the straw in an empty row

			// Another for-loop that will print out empty rows for glasses
			for ( ; currentEmptyRow < (currentGlass - 1); currentEmptyRow++, totalRightSpaces -= 2, currentTotalRow++)
			{
				// Print spaces at the beginning of the row
				printSpaces(currentEmptyRow);
				
				System.out.print("\\");
				
				// Print out (strawPos - 1) spaces to the left of the straw
				printSpaces(strawPos - 1);
				
				System.out.print("o");
				
				// Print out totalRightSpaces spaces to the right of the straw
				printSpaces(totalRightSpaces);
				
				System.out.println("/");
			}
			
			// Another for-loop that will print out full rows for glasses
			for (int currentFullRow = 0; currentFullRow < (initialGlassSize - currentGlass + 1); currentFullRow++, glassSize--, currentTotalRow++) // Declare a variable of type int which will store the number of the full row currently being printed 
			{
				printSpaces(currentTotalRow);
				
				// Left edge of the glass
				System.out.print("\\");
				
				// For-loop for printing out (2 * (glassSize - currentEmptyRow)) many asterisks
				for (int asterisks = 0; asterisks < (2 * (glassSize - currentEmptyRow)); asterisks++) // asterisks is a counter integer counting up to the number of asterisks in a row (2 * (glassSize - currentEmptyRow)
				{
					System.out.print("*");
				}
				
				// Right edge of the glass 
				System.out.println("/");
			}
			
			// Call the glassBottom method with the initial size of the glass as input to print out the bottom part of the glass
			glassBottom(initialGlassSize);
		}
	}
	
	// This method takes two inputs, the integers glassSize and strawPos and performing certain arithmetic operations on them, calculates & returns the number of glasses to be printed
	public static int returnNumberOfGlasses(int glassSize, int strawPos)
	{
		// If strawPos is less than or equal to half the glassSize, the number of glasses to be printed is just (glassSize + 1)
		if ((2 * strawPos) <= glassSize)
		{
			return glassSize + 1;
		}
		
		// Otherwise, we need to go through a more complex process to find out the number of glasses to be printed
		else
		{
			// We need to check if strawPos is an even number
			if (strawPos % 2 == 0)
			{
				// If strawPos happens to be an even number, the number of glasses to be printed is expressed by ((((2 * glassSize) - strawPos) / 2) + 2) (I found this relation by drawing a value-table on paper)
				return (((2 * glassSize) - strawPos) / 2) + 2;
			}
			else
			{
				// Otherwise if strawPos is an odd number, the number of glasses to be printed is expressed by ((((2 * glassSize) - strawPos) / 2) + 1), rounded UP to the nearest integer (I also found this relation by drawing a value-table on paper)
				return (int) Math.ceil((((2.0 * glassSize) - strawPos) / 2) + 1);
			}
		}
	}
	
	// This method prints out the bottom part of the glass (the handle)
	public static void glassBottom(int glassSize)
	{
		// Print out glassSize spaces at the beginning of the row
		printSpaces(glassSize);
		
		System.out.println("--");
		
		for (int rows = 0; rows < glassSize; rows++) // rows is a counter integer that counts up to the number of rows for the handle, which is equal to glassSize
		{
			// Print out glassSize spaces at the beginning of each row
			printSpaces(glassSize);
			
			System.out.println("||");
		}
	}
	
	// This method prints out the part of the straw sticking out from the top
	public static void straw(int strawPos)
	{
		// For-loop for printing out the rows of the straw
		for (int rows = 1; rows <= strawPos; rows++) // rows is a counter integer that counts up to the number of rows for the straw sticking out from the top, which is equal to strawPos
		{
			printSpaces(rows - 1);
		
			System.out.println("o");
		}
	}
	
	// Since we need to print out spaces many times while printing out the glasses, this method using a for-loop to print out a specified number of spaces will be useful
	public static void printSpaces(int totalSpaces)
	{
		for (int spaceCount = 0; spaceCount < totalSpaces; spaceCount++) //spaceCount is a counter that's used to count up to the wanted number of spaces (the value input when calling the method)
		{
			System.out.print(" ");
		}
	}
}
