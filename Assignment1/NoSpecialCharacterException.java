public class NoSpecialCharacterException extends Exception
{
	//Message if there is not a special character in the password	

public NoSpecialCharacterException() {		
super("The password must contain at least one special character.");	
}
}
