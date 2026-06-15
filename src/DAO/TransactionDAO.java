package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DB.connectionDB;
import Model.Transaction;
import Model.TransactionTypes.TransferRequest;

/**
 * DAO de transacciones, pide y sube transacciones a la bd
 */
public class TransactionDAO {

	public void saveTransfer(Transaction t) throws SQLException {
		String query = """
						INSERT INTO TRANSACTIONS (
							ORIGIN_ACCOUNT_ID,
							DESTINY_ACCOUNT_ID,
							AMOUNT,
							TRANSACTION_TYPE,
							TRANSACTION_STATUS,
							ORIGIN_IBAN,
							DESTINY_IBAN
						)
						VALUES (?, ?, ?, ?, ?, ?, ?)
				""";

		try (Connection cn = connectionDB.connect(); PreparedStatement ps = cn.prepareStatement(query)) {

			ps.setInt(1, t.getIdAccountOrigin());
			ps.setInt(2, t.getIdAccountDestination());
			ps.setDouble(3, t.getAmount());
			ps.setString(4, t.gettType().name());
			ps.setString(5, t.gettStatus().name());
			ps.setString(6, t.getOriginIBAN());
			ps.setString(7, t.getDestinyIBAN());
			ps.executeUpdate();
		}
	}
	public void saveBizum(Transaction t) throws SQLException {
		String query = """
						INSERT INTO TRANSACTIONS (
							ORIGIN_ACCOUNT_ID,
							DESTINY_ACCOUNT_ID,
							AMOUNT,
							TRANSACTION_TYPE,
							TRANSACTION_STATUS,
							ORIGIN_IBAN,
							DESTINY_IBAN,
							ORIGIN_PHONE,
							DESTINY_PHONE
						)
						VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
				""";

		try (Connection cn = connectionDB.connect(); PreparedStatement ps = cn.prepareStatement(query)) {

			ps.setInt(1, t.getIdAccountOrigin());
			ps.setInt(2, t.getIdAccountDestination());
			ps.setDouble(3, t.getAmount());
			ps.setString(4, t.gettType().name());
			ps.setString(5, t.gettStatus().name());
			ps.setString(6, t.getOriginIBAN());
			ps.setString(7, t.getDestinyIBAN());
			ps.setString(8, t.getOriginPhone());
			ps.setString(9, t.getDestinyPhone());

			ps.executeUpdate();
		}
	}
	public void saveCashOperation(Transaction t) throws SQLException {

		String query = """
						INSERT INTO TRANSACTIONS (
							ORIGIN_ACCOUNT_ID,
							AMOUNT,
							TRANSACTION_TYPE,
							TRANSACTION_STATUS,
							ORIGIN_IBAN
						)
						VALUES (?, ?, ?, ?, ?)
				""";

		try (
				Connection cn = connectionDB.connect();
				PreparedStatement ps = cn.prepareStatement(query)
		) {

			ps.setInt(1, t.getIdAccountOrigin());
			ps.setDouble(2, t.getAmount());
			ps.setString(3, t.gettType().name());
			ps.setString(4, t.gettStatus().name());
			ps.setString(5, t.getOriginIBAN());

			ps.executeUpdate();
		}
	}
}

