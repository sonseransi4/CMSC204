
public class WeakPasswordException extends Exception{
	//Message when the password is weak
	public WeakPasswordException() {
		super("The password is OK but weak - it contains fewer than 10 characters");
	}
}
