package businessLogic;

import Model.User;
import exceptions.UserNotFoundException;

public class AccountManager {

	public void validAccount(User u) {
		if (u == null) {
			throw new UserNotFoundException();
		}
	}
	
}
