package main;
import DAO.UserDAO;
import auth.AuthService;
import businessLogic.AccountManager;
import businessLogic.BankAccountManager;
import controller.LoginController;
import controller.UserSessionFactory;
import views.LoginView;

public class Main {

	public static final void main(String[] args) {
		LoginView lView = new LoginView();
		UserDAO uDao = new UserDAO();
		AuthService authService = new AuthService(uDao);
		BankAccountManager bManager = new BankAccountManager();
		AccountManager aManager = new AccountManager();
		UserSessionFactory uSF = new UserSessionFactory(bManager);
		LoginController lC = new LoginController(lView, uDao, authService, aManager, uSF);
		lC.LoginEntry();
	}
}
