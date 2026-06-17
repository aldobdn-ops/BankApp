package views.admin;

import views.BaseView;

/**
 * Vista del panel de administrador.
 * Renderiza el menú unificado de acciones de administración del sistema.
 */
public class AdminView extends BaseView {

	/**
	 * Muestra el menú simplificado para el administrador del sistema con todas las opciones disponibles.
	 * @param adminName Nombre del administrador logueado
	 */
	public void displayAdminMenu(String adminName) {
		System.out.println("\n========================================================");
		System.out.println("              BANKAPP - SYSTEM ADMINISTRATION           ");
		System.out.println("========================================================");
		System.out.println("  Administrator: " + adminName);
		System.out.println("--------------------------------------------------------");

		System.out.println("  1. Reset Password Attempts to 0");
		System.out.println("  2. Check Customer Accounts & Transactions by DNI/NIE");

		System.out.println("--------------------------------------------------------");
		System.out.println("  0. LOGOUT");
		System.out.println("========================================================");
		System.out.print("  Select an option: ");
	}
}
