package businessLogic;
/**
 * Gestiona la obtencion de la cuenta y cuenta bancaria 
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAO.CustomerDAO;
import DAO.UserDAO;
import Model.BankAccount;
import Model.Card;
import Model.Customer;
import Model.CustomerAccountDetails;
import Model.User;
import Model.User.Role;
import exceptions.BusinessException;
import exceptions.CustomerNotOwnerException;
import exceptions.UserIsNotCustomerException;
import exceptions.UserNotFoundException;

public class CustomerManager {

	private final UserDAO uDAO;
	private final CustomerDAO cDAO;


	public CustomerManager(UserDAO uDAO) {
		this.uDAO = uDAO;
		this.cDAO = new CustomerDAO();
	}
	/**
	 * Recoge la cuenta y los detalles de las cuentas bancarias del cliente buscando por NIE.
	 * @param nie El NIE del usuario
	 * @return Un objeto conteniendo el Customer y la lista de cuentas bancarias
	 * @throws SQLException Si ocurre algún fallo de base de datos
	 */
	public CustomerAccountDetails getCustomerAccountDetails(String nie) throws SQLException {
		Customer customer = (Customer) uDAO.findUserByNIE(nie);
		
		if (customer == null) {
			throw new UserNotFoundException();
		}

		List<BankAccount> BankAccounts = cDAO.getCustomerBankAccounts(customer.getIdUser());

		return new CustomerAccountDetails(customer, BankAccounts);
	}

	/**
	 * Verifica si un IBAN pertenece a la lista de cuentas del cliente dado.
	 * @param c El cliente a verificar
	 * @param IBAN El IBAN de la cuenta
	 * @return true si es propietario, de lo contrario lanza una excepción
	 * @throws BusinessException Si el cliente no es el propietario de la cuenta
	 */
	public boolean checkOwnerbyIBAN(Customer c,String IBAN) throws BusinessException {
		for(BankAccount bAcc:c.getBankAccounts()) {
			if(IBAN.equalsIgnoreCase(bAcc.getIBAN())) {
				return true;
			}
		}
		throw new CustomerNotOwnerException();
	}

	/**
	 * Verifica si un número de teléfono pertenece a las cuentas de Bizum del cliente dado.
	 * @param c El cliente a verificar
	 * @param Phone El número de teléfono
	 * @return true si es propietario, de lo contrario lanza una excepción
	 * @throws BusinessException Si el cliente no es propietario del número
	 */
	public boolean checkOwnerbyPhone(Customer c,String Phone) throws BusinessException {
		for(BankAccount bAcc:c.getBankAccounts()) {
			if(Phone.equalsIgnoreCase(bAcc.getBizumPhone())) {
				return true;
			}
		}
		throw new CustomerNotOwnerException();
	}

	/**
	 * Obtiene las cuentas bancarias y sus tarjetas asociadas del cliente.
	 * Recorre cuenta a cuenta y asigna sus tarjetas correspondientes.
	 * @param userId ID del usuario
	 * @return Una lista de BankAccount con sus tarjetas asociadas cargadas
	 * @throws BusinessException Si hay errores lógicos
	 * @throws SQLException Si ocurre algún fallo de base de datos
	 */
	public List <BankAccount> getCustomerBankAccountsAndCards (int userId) throws BusinessException,SQLException {
		List <BankAccount> cBanks = cDAO.getCustomerBankAccounts(userId);
		List <Card> cCards = cDAO.getCardsByAccountID(userId);
		for(BankAccount b:cBanks) {
			List <Card> ownerList = new ArrayList<Card>();
			for(Card c:cCards) {
				if(c.getIdAccount()==b.getIdUser()) {
					ownerList.add(c);
				}
			}
			b.setAssociatedCards(ownerList);
		}
		return cBanks;
		
	}

	/**
	 * Comprueba si un usuario (por NIE) tiene el rol de cliente, devolviendo el objeto Customer cargado.
	 * @param Nie El NIE a buscar
	 * @return El objeto Customer correspondiente
	 * @throws BusinessException Si el usuario no es de tipo cliente
	 * @throws SQLException Si ocurre algún fallo de base de datos
	 */
	public Customer getUserifItsCustomer (String Nie) throws BusinessException,SQLException {
		User u = uDAO.findUserByNIE(Nie);
		if (u == null) {
			throw new UserNotFoundException();
		}
		if (u.getRole()==Role.CUSTOMER) {
			Customer c = (Customer) u;
			c.setBankAccounts(cDAO.getCustomerBankAccounts(u.getIdUser()));
			return c;
		}
		throw new UserIsNotCustomerException();
	}

	/**
	 * Actualiza los datos de las cuentas del cliente cargándolas desde la base de datos.
	 * @param c El cliente a actualizar
	 * @return El objeto Customer actualizado
	 * @throws SQLException Si ocurre algún fallo de base de datos
	 */
	public Customer updateCustomerBankInApp(Customer c) throws SQLException {
		c.setBankAccounts(cDAO.getCustomerBankAccounts(c.getIdUser()));
		return c;
	}
}

