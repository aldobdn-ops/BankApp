package Model;

import java.time.LocalDateTime;

/**
 * clase abstracta con atributos comunes de las operaciones
 */
/**
 * Clase modelo de transferencia para guaradar en bd
 */
public class Transaction {
	protected double amount;
	protected Integer idAccountOrigin;
	protected Integer idAccountDestination;
	protected String originIBAN;
	protected String destinyIBAN;
	protected String originPhone;
	protected String destinyPhone;
	public enum TransactionType {
		TRANSFER,
		BIZUM,
		CARDPAYMENT,
		DEPOSIT,
		WITHDRAWAL
	}

	protected TransactionType tType;

	public enum TransactionStatus {
		COMPLETED,
		CANCELED
	}
	public TransactionStatus tStatus;
	//Constructor para transferencias
	public Transaction(double amount, Integer idAccountOrigin, Integer idAccountDestination, String originIBAN,
			String destinyIBAN, TransactionType tType, TransactionStatus tStatus) {
		super();
		this.amount = amount;
		this.idAccountOrigin = idAccountOrigin;
		this.idAccountDestination = idAccountDestination;
		this.originIBAN = originIBAN;
		this.destinyIBAN = destinyIBAN;
		this.tType = tType;
		this.tStatus = tStatus;
	}
	
	//Constructor para Cash transactions
	public Transaction(double amount, Integer idAccountOrigin, String originIBAN, TransactionType tType,
			TransactionStatus tStatus) {
		super();
		this.amount = amount;
		this.idAccountOrigin = idAccountOrigin;
		this.originIBAN = originIBAN;
		this.tType = tType;
		this.tStatus = tStatus;
	}
	
// constructor para bizum
	public Transaction(double amount, Integer idAccountOrigin, Integer idAccountDestination, String originIBAN,
			String destinyIBAN, String originPhone, String destinyPhone, TransactionType tType,
			TransactionStatus tStatus) {
		super();
		this.amount = amount;
		this.idAccountOrigin = idAccountOrigin;
		this.idAccountDestination = idAccountDestination;
		this.originIBAN = originIBAN;
		this.destinyIBAN = destinyIBAN;
		this.originPhone = originPhone;
		this.destinyPhone = destinyPhone;
		this.tType = tType;
		this.tStatus = tStatus;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Integer getIdAccountOrigin() {
		return idAccountOrigin;
	}

	public void setIdAccountOrigin(Integer idAccountOrigin) {
		this.idAccountOrigin = idAccountOrigin;
	}

	public Integer getIdAccountDestination() {
		return idAccountDestination;
	}

	public void setIdAccountDestination(Integer idAccountDestination) {
		this.idAccountDestination = idAccountDestination;
	}

	public String getOriginIBAN() {
		return originIBAN;
	}

	public void setOriginIBAN(String originIBAN) {
		this.originIBAN = originIBAN;
	}

	public String getDestinyIBAN() {
		return destinyIBAN;
	}

	public void setDestinyIBAN(String destinyIBAN) {
		this.destinyIBAN = destinyIBAN;
	}

	public String getOriginPhone() {
		return originPhone;
	}

	public void setOriginPhone(String originPhone) {
		this.originPhone = originPhone;
	}

	public String getDestinyPhone() {
		return destinyPhone;
	}

	public void setDestinyPhone(String destinyPhone) {
		this.destinyPhone = destinyPhone;
	}

	public TransactionType gettType() {
		return tType;
	}

	public void settType(TransactionType tType) {
		this.tType = tType;
	}

	public TransactionStatus gettStatus() {
		return tStatus;
	}

	public void settStatus(TransactionStatus tStatus) {
		this.tStatus = tStatus;
	}
	
	
}



