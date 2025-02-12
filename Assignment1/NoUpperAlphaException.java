public class NoUpperAlphaException extends Exception
{	
//Message if there is not an uppercase alphabetic character in the password	
public NoUpperAlphaException() {		
super("The password must contain at least one uppercase alphabetic character.");	
}	
}
