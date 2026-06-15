package views;
import Model.LoginCredentials;
import messageService.Messages;

public class LoginView extends BaseView {

	
	public LoginView() {
		super();
	}
	
	public void loginMenu() {
	
			System.out.println("╔══════════════════════════════════════════════╗");
		    System.out.println("║                DIGITAL BANKING               ║");
		    System.out.println("╠══════════════════════════════════════════════╣");
		    System.out.println("║                  LOGIN MENU                  ║");
		    System.out.println("╠══════════════════════════════════════════════╣");
		    System.out.println("║  1) Log in                                   ║");
		    System.out.println("║  0) Exit                                     ║");
		    System.out.println("╚══════════════════════════════════════════════╝");
		    System.out.print("Please choose an option: ");
	}
	public void enterNIEView() {

			System.out.println("╔══════════════════════════════════════════════╗");
		    System.out.println("║                DIGITAL BANKING               ║");
		    System.out.println("╠══════════════════════════════════════════════╣");
		    System.out.println("║                ENTER NIE                     ║");
		    System.out.println("╚══════════════════════════════════════════════╝");

		    System.out.print(" NIE: ");
	}
	public void enterPasswordView() {
		
			System.out.println("╔══════════════════════════════════════════════╗");
		    System.out.println("║                DIGITAL BANKING               ║");
		    System.out.println("╠══════════════════════════════════════════════╣");
		    System.out.println("║                ENTER PASSWORD                ║");
		    System.out.println("╚══════════════════════════════════════════════╝");
	
		    System.out.print(" Password: ");
		}
	public LoginCredentials askCredentials() {
		String nie = askForNIE(Messages.LOGIN_ASK_NIE);
		String password = askandGetString(Messages.LOGIN_ASK_PASSWORD);
		return new LoginCredentials(nie,password);
	}
}
