package controller;

import views.LoginView;
import views.mainView;
import DAO.UserDAO;
import Model.User;
import auth.AuthService;
import java.io.*;
import messageService.*;
public class LoginController {
	 private final LoginView lView;
	    private final UserDAO uDAO;
	    private final AuthService authService;

	    public LoginController(LoginView loginView, UserDAO userDAO, AuthService authService) {
	        this.lView = loginView;
	        this.uDAO = userDAO;
	        this.authService = authService;
	    }
	/**
	 * funcion para gestionar el login
	 */
	public void LoginEntry(){
		int option=lView.showMenuAndIntBack(lView::loginMenu, Messages.MENU_CHOOSE_INT);
		switch(option) {
		case 1 -> signIn();
		//case 2 -> forgotPassword();
		//case 3 -> exit();
		default-> throw new IllegalArgumentException("Unexpected value entry");
		}
	}
	/**
	 * funcion para controlar directamente la introducción y verificación de usuario y contraseña
	 */
	public void signIn() {
		String user;
		String password;
		user=lView.showMenuAndStringBack(lView::enterNIEView, Messages.LOGIN_ASK_NIE);
		password=lView.showMenuAndStringBack(lView::enterPasswordView, Messages.LOGIN_ASK_PASSWORD);
		User u = uDAO.findUserByNIE(user);
		if(authService.login(u, user, password)) {
			mainView mView= new mainView();
			mView.startMain();
		}
		else {
			lView.showMessage(Messages.LOGIN_ERROR);
		}
		
	}
	
}
