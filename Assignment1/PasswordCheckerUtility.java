import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public final class PasswordCheckerUtility {
   // This method checks to see the length of a string
   // @throws LengthException
   // @param passwor string to check length
   // @return true if length > 6, otherwise throws LengthException
   private static boolean checkLength(String passwor) throws LengthException {
       if (passwor.length() < 6)
           throw new LengthException();
       return true;
   }
   // This method checks if a string contains at least one lowercase character
   // @throws NoLowerAlphaException
   // @param passwor string to check for lowercase letters
   // @return true if at least one lowercase letter exists, otherwise throws NoLowerAlphaException
   private static boolean hasLowerAlpha(String passwor) throws NoLowerAlphaException {
       for (char c : passwor.toCharArray()) {
           if (Character.isLowerCase(c)) {
               return true;
           }
       }
       throw new NoLowerAlphaException();
   }
   // This method checks if a string contains at least one special character (!@#$%^&*())
   // @throws NoSpecialCharacterException
   // @param passwor string to check for special characters
   // @return true if at least one special character exists, otherwise throws NoSpecialCharacterException
   private static boolean hasSpecialChar(String passwor) throws NoSpecialCharacterException {
       char[] specialCharacs = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')'};
       for (char c : passwor.toCharArray()) {
           for (char special : specialCharacs) {
               if (c == special) {
                   return true;
               }
           }
       }
       throw new NoSpecialCharacterException();
   }
   // This method checks if a string contains at least one uppercase character
   // @throws NoUpperAlphaException
   // @param passwor string to check for uppercase letters
   // @return true if at least one uppercase letter exists, otherwise throws NoUpperAlphaException
   private static boolean hasUpperAlpha(String passwor) throws NoUpperAlphaException {
       for (char c : passwor.toCharArray()) {
           if (Character.isUpperCase(c)) {
               return true;
           }
       }
       throw new NoUpperAlphaException();
   }
   // This method checks if no character repeats 3 or more times in a row
   // @throws InvalidSequenceException
   // @param passwor string to check for consecutive character repetition
   // @return true if no character repeats 3 times, otherwise throws InvalidSequenceException
   private static boolean isValidSequence(String passwor) throws InvalidSequenceException {
       int counting = 1;
       for (int x = 1; x < passwor.length(); x++) {
           if (passwor.charAt(x) == passwor.charAt(x - 1)) {
               counting++;
               if (counting >= 3) {
                   throw new InvalidSequenceException();
               }
           } else {
               counting = 1;
           }
       }
       return true;
   }
   // This method checks if a string contains at least one digit
   // @throws NoDigitException
   // @param passwor string to check for numeric characters
   // @return true if at least one digit exists, otherwise throws NoDigitException
   private static boolean hasDigit(String passwor) throws NoDigitException {
       if (passwor.chars().anyMatch(Character::isDigit)) {
           return true;
       }
       throw new NoDigitException();
   }
   // This method checks if a password meets all validation rules
   // @throws LengthException, NoDigitException, NoUpperAlphaException, NoLowerAlphaException, NoSpecialCharacterException, InvalidSequenceException
   // @param passwordfin string to check against password requirements
   // @return true if valid, otherwise throws an exception for the first invalid rule encountered
   public static boolean isValidPassword(String passwordfin) throws LengthException, NoDigitException,
           NoUpperAlphaException, NoLowerAlphaException, NoSpecialCharacterException, InvalidSequenceException {
       checkLength(passwordfin);
       hasUpperAlpha(passwordfin);
       hasLowerAlpha(passwordfin);
       hasDigit(passwordfin);
       hasSpecialChar(passwordfin);
       isValidSequence(passwordfin);
       return true;
   }
   // This method checks if a password is weak (between 6 and 9 characters)
   // @throws WeakPasswordException
   // @param passwordfin string to check if it is weak
   // @return true if weak, false otherwise
   public static boolean isWeakPassword(String passwordfin) throws WeakPasswordException {
       if (passwordfin.length() <= 9 && passwordfin.length() >= 6) {
           return true;
       }
       return false;
   }
   // This method finds all invalid passwords from a list of passwords
   // @param passwords ArrayList of string passwords
   // @return an ArrayList of invalid passwords with error messages
   public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
       ArrayList<String> passwording = new ArrayList<>();
       for (String invaliding : passwords) {
           try {
               isValidPassword(invaliding);
           } catch (Exception e) {
               passwording.add(invaliding + " " + e.getMessage());
           }
       }
       return passwording;
   }
}

