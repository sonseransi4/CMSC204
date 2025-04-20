 import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MorseCodeConverterTestStudent {
	
	@Test
	public void testConvertToEnglishString() {	
		
		String converter1 = MorseCodeConverter.convertToEnglish("-... . . - .... --- ...- . -. / ... -.-- -- .--. .... --- -. -.-- / -. .. -. .");
		assertEquals("beethoven symphony nine",converter1);
	}
	
	
	@Test
	public void testPrintTree() {	
		
		assertEquals("h s v i f u e l r a p w j  b d x n c k y t z g q m o", MorseCodeConverter.printTree());
	}

	
	@Test
	public void testConvertMorseStringToEnglishString() {	
		
		String converter1 = MorseCodeConverter.convertToEnglish("-- --- --.. .- .-. - / ... -.-- -- .--. .... --- -. -.-- / ..-. --- .-. - -.-- / .... .- ... / .- / -.-. --- --- .-.. / -.. . ...- . .-.. --- .--. -- . -. -");
		assertEquals("mozart symphony forty has a cool development", converter1);

	}
	@Test
	public void testConvertMorseFileToEnglishString() {	
		//Daisy.txt needs to be in the src
		File file = new File("src/Daisy.txt"); 
		try {
			assertEquals("give me your answer do", MorseCodeConverter.convertToEnglish(file));
		} catch (FileNotFoundException e) {
			assertTrue("An unwanted exception was caught", false);
		}
	}
	

}
