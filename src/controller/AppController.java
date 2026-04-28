package controller;

import views.LoginView;
import views.mainView;

public class AppController {

	private LoginView loginView;
	private LoginController loginController;
	private mainView mainView;
	private MainController mainController;
	
	public AppController(LoginView loginView, LoginController loginController, mainView mainView,
			MainController mainController) {
		super();
		this.loginView = loginView;
		this.loginController = loginController;
		this.mainView = mainView;
		this.mainController = mainController;
	}
	public void start(){
		loginController.LoginEntry();
		
		
	}
	
	
}
