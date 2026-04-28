package views;

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
		    System.out.println("║  2) Forgot my password                       ║");
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
}
