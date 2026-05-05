package controller;

import views.LoginView;

public class AppController {

	private LoginView loginView;
	private LoginController loginController;

	
	public AppController(LoginView loginView, LoginController loginController) {
		super();
		this.loginView = loginView;
		this.loginController = loginController;
	}
	public void start(){
		loginController.LoginEntry();
		
		
	}
	
	
}
