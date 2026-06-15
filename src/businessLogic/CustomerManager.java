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

	private UserDAO uDAO;
	private CustomerDAO cDAO;


	public CustomerManager() {
		this.uDAO = new UserDAO();
		this.cDAO = new CustomerDAO();
	}
/**
 * recoge la cuenta y la cuenta bancaria del usuario
 * @param nie
 * @return CustomerAccountDetails
 * @throws SQLException
 */
	public CustomerAccountDetails getCustomerAccountDetails(String nie) throws SQLException {
		Customer customer = (Customer) uDAO.findUserByNIE(nie);
		
		if (customer == null) {
			throw new UserNotFoundException();
		}

		List<BankAccount> BankAccounts = cDAO.getCustomerBankAccounts(customer.getIdUser());

		return new CustomerAccountDetails(customer, BankAccounts);
	}
	public boolean checkOwnerbyIBAN(Customer c,String IBAN) throws BusinessException {
		for(BankAccount bAcc:c.getBankAccounts()) {
			if(IBAN.equalsIgnoreCase(bAcc.getIBAN())) {
				return true;
			}
		}
		throw new CustomerNotOwnerException();
	}
	public boolean checkOwnerbyPhone(Customer c,String Phone) throws BusinessException {
		for(BankAccount bAcc:c.getBankAccounts()) {
			if(Phone.equalsIgnoreCase(bAcc.getBizumPhone())) {
				return true;
			}
		}
		throw new CustomerNotOwnerException();
	}
	/**
	 * metodo para obetner las cuentas con las tarjetas del cliente, recorre cuenta a cuenta y en cada
	 * cuenta recorre todas las tarjetas si coincide el id lo añade a una lista que se añade posteriormente a la 
	 * cuenta bancaria que se encuentre en al iteracion
	 * @param userId
	 * @return
	 * @throws BusinessException
	 * @throws SQLException
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
	public Customer getUserifItsCustomer (String Nie) throws BusinessException,SQLException {
		User u = uDAO.findUserByNIE(Nie);
		if (u.getRole()==Role.CUSTOMER) {
			Customer c = (Customer) u;
			c.setBankAccounts(cDAO.getCustomerBankAccounts(u.getIdUser()));
			return c;
		}
		throw new UserIsNotCustomerException();
	}
	public Customer updateCustomerBankInApp(Customer c) throws SQLException {
		c.setBankAccounts(cDAO.getCustomerBankAccounts(c.getIdUser()));
		return c;
	}
}

