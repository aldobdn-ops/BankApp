package main;

import java.sql.Connection;

import DAO.UserDAO;
import DB.connectionDB;
import auth.AuthService;
import controller.AppController;
import controller.LoginController;
import controller.MainController;
import views.LoginView;
import views.mainView;

public class Main {

	public static final void main(String[] args) {
		LoginView lView = new LoginView();
		UserDAO uDao = new UserDAO();
		AuthService authService = new AuthService(uDao);
		LoginController lController = new LoginController(lView,uDao,authService);
		mainView mView = new mainView();
		MainController mController = new MainController();
		AppController appController = new AppController(lView, lController, mView, mController);
		appController.start();
	}
}
