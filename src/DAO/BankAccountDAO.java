package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DB.connectionDB;
import exceptions.AccountNotFoundException;
/**
 * Lanza consultas a la tabla BankAccount
 */
public class BankAccountDAO {

	public void updateBaBalance(String IBAN,double newBalance) throws SQLException {
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
				PreparedStatement ps = cn.prepareStatement(query)){
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
				PreparedStatement ps = cn.prepareStatement(query)){
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
				PreparedStatement ps = cn.prepareStatement(query)){
				ps.setString(1, IBAN);
				ResultSet rs = ps.executeQuery();
				
		        if (!rs.next()) {
		            throw new AccountNotFoundException();
		        }

		        return rs.getDouble("transfer_limit");
		}
	}
	public String getIBANbyphone(String phone) throws SQLException {
		String query = "SELECT B.IBAN FROM BANK_ACCOUNT B"
				+ "JOIN APP_USER A ON A.ID_USER=B.ID_USER"
				+ "WHERE A.PHONE_NUMBER = ?";
		try(Connection cn=connectionDB.connect();
			PreparedStatement ps	= cn.prepareStatement(query)){
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
		String query="Select a.name from APP_USER a"
				+ " join BANK_ACCOUNT b on a.id_user=b.id_user"
				+ " where b.iban = ?";
		try(Connection cn= connectionDB.connect();
				PreparedStatement ps=cn.prepareStatement(query)){
			ps.setString(1,iban);
			System.out.println(ps);
			ResultSet rs= ps.executeQuery();
			if (!rs.next()) {
				throw new AccountNotFoundException();
			}
			return rs.getString("name");
		}
	}

}
