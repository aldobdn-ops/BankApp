package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DB.connectionDB;
import Model.BankAccount;
import exceptions.AccountNotFoundException;

/**
 * Lanza consultas a la tabla BankAccount
 */
public class BankAccountDAO {

	public void updateBaBalance(String IBAN, double newBalance) throws SQLException {
		String query = "UPDATE bank_account SET current_balance = ? WHERE iban = ?";

		try (Connection cn = connectionDB.connect();
				PreparedStatement ps = cn.prepareStatement(query)) {
			ps.setDouble(1, newBalance);
			ps.setString(2, IBAN);
			ps.executeUpdate();
		}

	}

	public double getBalancebyIBAN(String IBAN) throws SQLException {
		String query = "Select current_balance from bank_account where IBAN = ?";
		try (Connection cn = connectionDB.connect();
				PreparedStatement ps = cn.prepareStatement(query)) {
			ps.setString(1, IBAN);
			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				throw new AccountNotFoundException();
			}

			return rs.getDouble("current_balance");
		}
	}

	public double getTrLimitbyIBAN(String IBAN) throws SQLException {
		String query = "Select transfer_limit from bank_account where IBAN = ?";
		try (Connection cn = connectionDB.connect();
				PreparedStatement ps = cn.prepareStatement(query)) {
			ps.setString(1, IBAN);
			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				throw new AccountNotFoundException();
			}

			return rs.getDouble("transfer_limit");
		}
	}

	public double getTransferLimitbyIBAN(String IBAN) throws SQLException {
		String query = "Select transfer_limit from bank_account where IBAN = ?";
		try (Connection cn = connectionDB.connect();
				PreparedStatement ps = cn.prepareStatement(query)) {
			ps.setString(1, IBAN);
			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				throw new AccountNotFoundException();
			}

			return rs.getDouble("transfer_limit");
		}
	}

	public String getIBANbyphone(String phone) throws SQLException {
		String query = "SELECT B.IBAN FROM BANK_ACCOUNT B WHERE B.BIZUM_PHONE = ?";
		try (Connection cn = connectionDB.connect();
				PreparedStatement ps = cn.prepareStatement(query)) {
			ps.setString(1, phone);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				throw new AccountNotFoundException();
			}
			return rs.getString("iban");
		}
	}

	public int getIdBankbyIBAN(String IBAN) throws SQLException {
		String query = """
						SELECT ID_ACCOUNT
						FROM BANK_ACCOUNT
						WHERE IBAN = ?
				""";
		try (Connection cn = connectionDB.connect(); PreparedStatement ps = cn.prepareStatement(query)) {
			ps.setString(1, IBAN);

			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				throw new AccountNotFoundException();
			}
			return rs.getInt("ID_ACCOUNT");
		}

	}

	public String getAccountOwnerByIBAN(String iban) throws SQLException {
		String query = "Select a.name from APP_USER a"
				+ " join BANK_ACCOUNT b on a.id_user=b.id_user"
				+ " where b.iban = ?";
		try (Connection cn = connectionDB.connect();
				PreparedStatement ps = cn.prepareStatement(query)) {
			ps.setString(1, iban);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				throw new AccountNotFoundException();
			}
			return rs.getString("name");
		}
	}

	/**
	 * Busca una cuenta bancaria completa en la base de datos por su IBAN.
	 * 
	 * @param iban El IBAN de la cuenta
	 * @return El objeto BankAccount correspondiente
	 * @throws SQLException Si ocurre algún fallo de base de datos o la cuenta no
	 *                      existe
	 */
	public BankAccount getBankAccountByIBAN(String iban) throws SQLException {
		String query = "SELECT * FROM bank_account WHERE iban = ?";
		try (Connection cn = connectionDB.connect(); PreparedStatement ps = cn.prepareStatement(query)) {
			ps.setString(1, iban);
			try (ResultSet rs = ps.executeQuery()) {
				if (!rs.next()) {
					throw new AccountNotFoundException();
				}
				int idUser = rs.getInt("id_user");
				int idAccount = rs.getInt("id_account");
				double currentBalance = rs.getDouble("current_balance");
				double transferLimit = rs.getDouble("transfer_limit");
				double overdraftLimit = rs.getDouble("overdraft_limit");
				String phone = rs.getString("BIZUM_PHONE");
				return new BankAccount(idUser, idAccount, currentBalance, iban, transferLimit, overdraftLimit, phone);
			}
		}
	}
}
