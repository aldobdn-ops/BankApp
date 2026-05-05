package controller;

import DAO.AdminDAO;
import Model.Admin;
import views.admin.AdminView;

public class AdminController {

	private AdminDAO adminDAO;
	private AdminView aView;
	private Admin a;
	
	public AdminController(AdminDAO adminDAO, AdminView aView, Admin a) {
		super();
		this.adminDAO = adminDAO;
		this.aView = aView;
		this.a = a;
	}
	
	public void mainAdminMenu () {
		aView.displayAdminMenu(a.getName());
	}
}
