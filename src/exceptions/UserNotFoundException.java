package exceptions;

public class UserNotFoundException extends BusinessException{

	public UserNotFoundException (){
		super("User not found");
	}
	public UserNotFoundException(String message) {
		super("User not found");
	}
	
	
}
