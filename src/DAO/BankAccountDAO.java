package DAO;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.connectionDB;
import exceptions.AccountNotFoundException;

public class BankAccountDAO {

	public void updateBaBalance(String IBAN,double newBalance) throws SQLException {
		 String query = "UPDATE bank_account SET account_balance = ? WHERE iban = ?";

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
	public double getidBankAccbyIBAN(String IBAN) throws SQLException {
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
}
