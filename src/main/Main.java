package main;
import DAO.UserDAO;
import auth.AuthService;
import businessLogic.AccountManager;
import businessLogic.BankAccountManager;
import controller.AppController;
import controller.LoginController;
import views.LoginView;

public class Main {

	public static final void main(String[] args) {
		LoginView lView = new LoginView();
		UserDAO uDao = new UserDAO();
		AuthService authService = new AuthService();
		BankAccountManager bManager = new BankAccountManager();
		AccountManager aManager = new AccountManager();
		LoginController lController = new LoginController(lView,uDao,authService,bManager,aManager);
		AppController appController = new AppController(lView, lController);
		appController.start();
	}
}
