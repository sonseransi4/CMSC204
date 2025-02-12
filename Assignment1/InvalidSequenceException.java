public class InvalidSequenceException extends Exception{
	//Message If there is more than 2 of the same character in a sequence
	public InvalidSequenceException() {
		super("The password cannot contain more than two of the same character in sequence.");
	}
}
