package views.employee;

import views.BaseView;

/**
 * Vista del panel de empleado.
 * Renderiza el menú simplificado de acciones administrativas y de gestión de cuentas.
 */
public class EmployeeView extends BaseView {

	/**
	 * Muestra el menú unificado de empleados con todas las funcionalidades disponibles.
	 * @param employeeName Nombre del empleado logueado
	 * @param employeeId ID del usuario empleado
	 */
	public void showEmployeeMenu(String employeeName, int employeeId) {
		System.out.println("\n========================================================");
		System.out.println("                BANKAPP - EMPLOYEE PANEL                ");
		System.out.println("========================================================");
		System.out.println("  Employee: " + employeeName + " [ID: " + employeeId + "]");
		System.out.println("--------------------------------------------------------");

		System.out.println("  1. Search Holder by IBAN");
		System.out.println("  2. Check Account Details by NIE");
		System.out.println("  3. Check Account Details by IBAN");
		System.out.println("  4. Perform Transfer on Behalf of Customer");
		System.out.println("  5. Deposit Money");
		System.out.println("  6. Withdraw Money");
		System.out.println("  7. View Transaction History");

		System.out.println("--------------------------------------------------------");
		System.out.println("  0. LOGOUT");
		System.out.println("========================================================");
		System.out.print("  Select an option: ");
	}
}
