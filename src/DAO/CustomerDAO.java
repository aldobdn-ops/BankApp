package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import DB.connectionDB;
import Model.BankAccount;
import Model.Card;
import Model.Card.cardType;
import Model.Card.status;
import exceptions.AccountNotFoundException;

/**
 * DAO encargado del acceso y manipulación de datos sobre clientes,
 * incluyendo sus cuentas bancarias, tarjetas asociadas y teléfonos de contacto.
 */
public class CustomerDAO {

	public String findIBANbyId(int idUser) throws SQLException {
		String query = "SELECT IBAN FROM BANK_ACCOUNT WHERE ID_USER =?";
		try(Connection cn = connectionDB.connect();
			PreparedStatement pstmt = cn.prepareStatement(query)){
				pstmt.setInt(1, idUser);
				ResultSet rs= pstmt.executeQuery();
				String IBAN =rs.getString("iban");
				return IBAN;
			
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			return null;
	}
	 

	public double findBalancebyId(int idUser) throws SQLException {
		String query = "SELECT ACCOUNT_BALANCE FROM BANK_ACCOUNT WHERE ID_USER =?";
		try(Connection cn = connectionDB.connect();
			PreparedStatement pstmt = cn.prepareStatement(query)){
				pstmt.setInt(1, idUser);
				ResultSet rs= pstmt.executeQuery();
				Double balance =rs.getDouble("account_balance");
				return balance;
			
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AccountNotFoundException();
			}			
			
	}
	public BankAccount getBankAccbyId(int idUser) throws SQLException {
		String query = "SELECT * FROM BANK_ACCOUNT WHERE ID_USER = ?";
		BankAccount cBankAc = new BankAccount();
		try(Connection cn=connectionDB.connect();
				PreparedStatement psmt = cn.prepareStatement(query)){
				psmt.setInt(1,idUser);
				ResultSet rs=psmt.executeQuery();
				if(rs.next()) {
					cBankAc=mapBankAccount(rs);
					return cBankAc;
				}
		return null;
		}
	}
	public BankAccount mapBankAccount(ResultSet rs) throws SQLException {
		int idUser= rs.getInt("id_user");
		int idAccount=rs.getInt("id_account");
		double currentBalance=rs.getDouble("current_balance");
		String iban=rs.getString("iban");
		double transferLimit=rs.getDouble("transfer_limit");
		double overdraftLimit=rs.getDouble("overdraft_limit");
		String Phone = rs.getString("BIZUM_PHONE");
		//List<Card> associatedCards= getCardsByAccountID(idAccount);
		return new BankAccount(idUser,idAccount,currentBalance,iban,transferLimit,overdraftLimit,Phone);
	}

	private Card mapCard(ResultSet rs) throws SQLException {
		int idCard = rs.getInt("id_card");
		int idAccount = rs.getInt("id_account");
		String cardNumber = rs.getString("card_number");
		String hashCVV = rs.getString("hash_cvv");
		LocalDate creationDate = rs.getDate("creation_date").toLocalDate();
		LocalDate expirationDate = rs.getDate("expiration_date").toLocalDate();
		String hashPin = rs.getString("hash_pin");
		double dailyLimit = rs.getDouble("daily_limit");
		cardType cardType = Card.cardType.valueOf(rs.getString("card_type"));
		status cardStatus = Card.status.valueOf(rs.getString("card_status"));

		return new Card(idCard, idAccount, cardNumber, hashCVV, creationDate, expirationDate, hashPin, dailyLimit,
				cardType, cardStatus);
	}

	public List<Card> getCardsByAccountID(int accountID) throws SQLException {
		String query = "Select * from card where id_account=?";
		List<Card> cardList = new ArrayList<Card>();
		try (Connection cn = connectionDB.connect(); 
				PreparedStatement pstmt = cn.prepareStatement(query)) {
			pstmt.setInt(1, accountID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Card c = mapCard(rs);
				cardList.add(c);
			}
		}
		return cardList;
	}
	public List<BankAccount> getCustomerBankAccounts (int id_user) throws SQLException{
		String query = "SELECT * FROM bank_account WHERE id_user = ?";

	    List<BankAccount> accounts = new ArrayList<>();

	    try (Connection cn = connectionDB.connect();
	         PreparedStatement psmt = cn.prepareStatement(query)) {

	        psmt.setInt(1, id_user);
	        ResultSet rs = psmt.executeQuery();

	        while (rs.next()) {
	            BankAccount account = mapBankAccount(rs);
	            accounts.add(account);
	        }
	    }

	    return accounts;
	}

	public List<String> getCustomerPhoneNumbers(int id_user) throws SQLException {

		List<String> phoneNumbers = new ArrayList<>();
		String query = """
						SELECT PHONE_NUMBER
						FROM USER_PHONE
						WHERE ID_USER = ?
				""";
		try (Connection cn = connectionDB.connect(); PreparedStatement ps = cn.prepareStatement(query)) {
			ps.setInt(1, id_user);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				phoneNumbers.add(rs.getString("PHONE_NUMBER"));
			}
		}
		return phoneNumbers;
	}
}

