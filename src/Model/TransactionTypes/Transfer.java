package Model.TransactionTypes;

import Model.Transaction;

public class Transfer extends Transaction {

	private String beneficiaryAccount;
	public enum TransferType {
		INSTANT,
		RECURRING,
		SCHEDULED,
	}
	private TransferType transferType;
	private String scheduledDate;
	
	public Transfer(String beneficiaryAccount,TransferType transferType) {
		this.beneficiaryAccount=beneficiaryAccount;
		this.transferType=transferType;
	}
	

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void generateReceipt() {
		// TODO Auto-generated method stub
		
	}
	
}
