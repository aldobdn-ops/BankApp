package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
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

	public List<Transaction> getTransactionsByAccountId(int accountId) throws SQLException {
		String query = """
						SELECT * FROM TRANSACTIONS
						WHERE ORIGIN_ACCOUNT_ID = ? OR DESTINY_ACCOUNT_ID = ?
						ORDER BY CREATED_AT DESC
				""";
		List<Transaction> transactions = new ArrayList<>();
		try (Connection cn = connectionDB.connect(); PreparedStatement ps = cn.prepareStatement(query)) {
			ps.setInt(1, accountId);
			ps.setInt(2, accountId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					double amount = rs.getDouble("AMOUNT");
					int originId = rs.getInt("ORIGIN_ACCOUNT_ID");
					int destinyId = rs.getInt("DESTINY_ACCOUNT_ID");
					String originIban = rs.getString("ORIGIN_IBAN");
					String destinyIban = rs.getString("DESTINY_IBAN");
					String originPhone = rs.getString("ORIGIN_PHONE");
					String destinyPhone = rs.getString("DESTINY_PHONE");
					String typeStr = rs.getString("TRANSACTION_TYPE");
					String statusStr = rs.getString("TRANSACTION_STATUS");
					java.sql.Timestamp ts = rs.getTimestamp("CREATED_AT");
					String desc = rs.getString("DESCRIPTION");

					Transaction.TransactionType tType = typeStr != null ? Transaction.TransactionType.valueOf(typeStr) : null;
					Transaction.TransactionStatus tStatus = statusStr != null ? Transaction.TransactionStatus.valueOf(statusStr) : null;
					LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;

					Transaction t;
					if (tType == Transaction.TransactionType.BIZUM) {
						t = new Transaction(amount, originId, destinyId, originIban, destinyIban, originPhone, destinyPhone, tType, tStatus);
					} else if (tType == Transaction.TransactionType.DEPOSIT || tType == Transaction.TransactionType.WITHDRAWAL) {
						t = new Transaction(amount, originId, originIban, tType, tStatus);
					} else {
						t = new Transaction(amount, originId, destinyId, originIban, destinyIban, tType, tStatus);
					}
					t.setCreatedAt(createdAt);
					t.setDescription(desc);
					transactions.add(t);
				}
			}
		}
		return transactions;
	}
}

