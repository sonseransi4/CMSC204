import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {
	
	
	public static MorseCodeTree newMTree;
	
	
	//prints the tree
		public static String printTree() {
		    newMTree = new MorseCodeTree();
		    newMTree.buildTree();

		    ArrayList<String> treeList = newMTree.toArrayList();
		    String printing = "";

		    for (int i = 0; i < treeList.size(); i++)
		    {
		        printing += treeList.get(i);
		        if (i < treeList.size() - 1) 
		        {
		            printing += " ";
		        }
		    }

		    return printing;
		}
		
		
		
	//Converts the morse code from a file
	public static String convertToEnglish(File mcFile) throws FileNotFoundException {
	    Scanner reader = new Scanner(mcFile);
	    StringBuilder fileContent = new StringBuilder();

	    while (reader.hasNextLine()) 
	    {
	        fileContent.append(reader.nextLine());
	    }

	    return convertToEnglish(fileContent.toString());
	}
	
	
	//Converts the morse code into english, taking in a string
	public static String convertToEnglish(String words) {
	    newMTree = new MorseCodeTree();
	    newMTree.buildTree();

	    String[] mChars = words.split(" ");
	    String converted = "";

	    for (int i = 0; i < mChars.length; i++) 
	    {
	        String symbol = mChars[i];
	        if (symbol.equals("/")) 
	        {
	            converted += " ";
	        } 
	        else 
	        {
	            String letter = newMTree.fetch(symbol);
	            converted += letter;
	        }
	    }

	    return converted;
	}

	
}
