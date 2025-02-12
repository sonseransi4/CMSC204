public class NoLowerAlphaException extends Exception{	
//Message if there is no lowercase alphabetic character	
public NoLowerAlphaException() 
{		
super("The password must contain at least one lowercase alphabetic character.");	
}
}
