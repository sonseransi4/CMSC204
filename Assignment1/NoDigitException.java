public class NoDigitException extends Exception{
	//Message if there is not at least one digit in the password
	public NoDigitException() {
	super("The password must contain at least one digit.");
	}
}


