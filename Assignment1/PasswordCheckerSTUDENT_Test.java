import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class PasswordCheckerSTUDENT_Test {
   ArrayList<String> password;
   ArrayList<String> pass;
   @Before
   public void setUp() throws Exception {
       password = new ArrayList<>();
       String[] passwords = {
           "ValidPass#123",   //Valid password
           "NoUppercase#123", //No uppercase letter
           "NOLOWERCASE#123", //No lowercase letter
           "NoDigit#Test!",   //No digit
           "NoSpecial123",    //No special character
           "Short1!",         //Too short
           "AAAaaa123!",      //Invalid sequence
           "Weak12!",         //Weak password
           "ValidButWeaK1!",
           "ValidStr0ng$Pass" //Valid strong password
       };
       password.addAll(Arrays.asList(passwords));
   }
   @After
   public void tearDown() throws Exception {
       password = null;
       pass = null;
   }
   @Test
   public void testIsValidPasswordTooShort() {
       try {
           assertTrue(PasswordCheckerUtility.isValidPassword("ValidPass#123"));
           PasswordCheckerUtility.isValidPassword("Sh1!");
           assertTrue("No length exception", false);
       } catch (LengthException e) {
           assertTrue("Threw Length exception", true);
       } catch (Exception e) {
           assertTrue("Threw other exception", false);
       }
   }
   @Test
   public void testIsValidPasswordNoUpperAlpha() {
       try {
           assertTrue(PasswordCheckerUtility.isValidPassword("ValidPass#123"));
           PasswordCheckerUtility.isValidPassword("nouppercase#123");
           assertTrue("Did not throw NoUpperAlpha exception", false);
       } catch (NoUpperAlphaException e) {
           assertTrue("Threw NoUpperAlpha exception", true);
       } catch (Exception e) {
           assertTrue("Threw other exception", false);
       }
   }
   @Test
   public void testIsValidPasswordNoLowerAlpha() {
       try {
           assertTrue(PasswordCheckerUtility.isValidPassword("ValidPass#123"));
           PasswordCheckerUtility.isValidPassword("NOLOWERCASE#123");
           assertTrue("Did not throw NoLowerAlpha exception", false);
       } catch (NoLowerAlphaException e) {
           assertTrue("Threw NoLowerAlpha exception", true);
       } catch (Exception e) {
           assertTrue("Threw other exception", false);
       }
   }
   @Test
	public void testIsWeakPassword()
	{
		try {
		assertTrue(PasswordCheckerUtility.isWeakPassword("Jty@45"));
		PasswordCheckerUtility.isWeakPassword("");
		}
		catch(WeakPasswordException w) {
		assertTrue("Threw weakPassword exception",true);
		}
		
	}
   @Test
   public void testIsValidPasswordInvalidSequence() {
       try {
           assertTrue(PasswordCheckerUtility.isValidPassword("ValidPass#123"));
           PasswordCheckerUtility.isValidPassword("AAAaaa123!");
           assertTrue("Did not throw InvalidSequence exception", false);
       } catch (InvalidSequenceException e) {
           assertTrue("Threw InvalidSequence exception", true);
       } catch (Exception e) {
           assertTrue("Threw other exception", false);
       }
   }
   @Test
   public void testIsValidPasswordNoDigit() {
       try {
           assertTrue(PasswordCheckerUtility.isValidPassword("ValidPass#123"));
           PasswordCheckerUtility.isValidPassword("NoDigit#Test!");
           assertTrue("Did not throw NoDigit exception", false);
       } catch (NoDigitException e) {
           assertTrue("Threw NoDigit exception", true);
       } catch (Exception e) {
           assertTrue("Threw other exception", false);
       }
   }
   @Test
   public void testValidPasswordNoSpecialChar() {
       try {
           assertTrue(PasswordCheckerUtility.isValidPassword("ValidPass#123"));
           PasswordCheckerUtility.isValidPassword("NoSpecial123");
           assertTrue("Did not throw NoSpecialCharacter exception", false);
       } catch (NoSpecialCharacterException e) {
           assertTrue("Threw NoSpecialCharacter exception", true);
       } catch (Exception e) {
           assertTrue("Threw other exception", false);
       }
   }
   @Test
   public void testIsValidPasswordSuccessful() {
       try {
           PasswordCheckerUtility.isValidPassword("ValidStr0ng$Pass");
           assertTrue("Did not throw an exception", true);
       } catch (Exception e) {
           System.out.println(e.getMessage());
           assertTrue("Threw an exception when it should not have", false);
       }
   }
 
}

