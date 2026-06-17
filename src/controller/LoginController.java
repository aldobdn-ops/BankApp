package controller;
import views.LoginView;
import Model.LoginCredentials;
import java.sql.SQLException;
import Model.User;
import auth.AuthService;
import businessLogic.AccountManager;
import messageService.*;
import exceptions.BusinessException;
import exceptions.ExitRequestedException;

public class LoginController {
	private final LoginView lView;
	private final AuthService authService;
	private final AccountManager Amanager;
	private final UserSessionFactory uSF;
	/**
	 * Clase para gestionar el login del usuario, encargada de instanciar el tipo de
	 * usuario inicie sesion
	 * 
	 * @param loginView
	 * @param authService
	 * @param aManager
	 * @param uSF
	 */
	public LoginController(LoginView loginView, AuthService authService,AccountManager aManager,UserSessionFactory uSF) {
		this.lView = loginView;
		this.authService = authService;
		this.Amanager = aManager;
		this.uSF=uSF;

	}

	/**
	 * funcion para gestionar el login
	 */
	public void LoginEntry() {
		while (true) {
			try {
				int option = lView.showMenuAndIntBack(lView::loginMenu, Messages.MENU_CHOOSE_INT);
				switch (option) {
				case 1 -> signIn();
				case 0 -> {
					lView.showMessage(Messages.THANKS_FOR_USING);
					return;
				}
				default -> lView.showMessage("Unexpected value entry");
				}
			} catch (ExitRequestedException e) {
				lView.showMessage(Messages.THANKS_FOR_USING);
				return;
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
				User u = Amanager.findSimpleUserByNIE(lC.getNie());
				Amanager.validAccount(u);
				authService.login(u,lC);
				uSF.launch(u);
				return;
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
