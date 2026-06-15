package controller;
import views.LoginView;
import Model.LoginCredentials;
import java.sql.SQLException;
import DAO.UserDAO;
import Model.User;
import auth.AuthService;
import businessLogic.AccountManager;
import messageService.*;
import exceptions.BusinessException;
import exceptions.ExitRequestedException;

public class LoginController {
	private final LoginView lView;
	private final UserDAO uDAO;
	private final AuthService authService;
	private final AccountManager Amanager;
	private final UserSessionFactory uSF;
	/**
	 * Clase para gestionar el login del usuario, encargada de instanciar el tipo de
	 * usuario inicie sesion
	 * 
	 * @param loginView
	 * @param userDAO
	 * @param authService
	 * @param bA
	 * @param aManager
	 */
	public LoginController(LoginView loginView, UserDAO userDAO, AuthService authService,AccountManager aManager,UserSessionFactory uSF) {
		this.lView = loginView;
		this.uDAO = userDAO;
		this.authService = authService;
		this.Amanager = aManager;
		this.uSF=uSF;

	}

	/**
	 * funcion para gestionar el login
	 */
	public void LoginEntry() {
		while (true) {
			int option = lView.showMenuAndIntBack(lView::loginMenu, Messages.MENU_CHOOSE_INT);
			switch (option) {
			case 1 -> signIn();
			case 0 -> {
				lView.showMessage(Messages.THANKS_FOR_USING);
				return;
			}
			default -> lView.showMessage("Unexpected value entry");
			}
		}
	}

	/**
	 * funcion para controlar directamente la introducción y verificación de usuario
	 * y contraseña
	 */
	public void signIn() {
		while (true) {
			try {
				LoginCredentials lC = lView.askCredentials();
				User u = uDAO.findSimpleUserByNIE(lC.getNie());
				Amanager.validAccount(u);
				authService.login(u,lC);
				uSF.launch(u);
			} catch (ExitRequestedException e) {
				lView.showMessage(e.getMessage());
				return;
			} catch (BusinessException e) {			
				lView.showMessage(e.getMessage());
			} catch (SQLException e) {
				lView.showMessage("Problem with database");
				e.printStackTrace();
				return;
			}
		}
	}
}
