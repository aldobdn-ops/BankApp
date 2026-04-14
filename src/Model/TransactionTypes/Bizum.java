package Model.TransactionTypes;

import Model.Transaction;

public class Bizum extends Transaction {

	private String beneficiaryPhoneNumber;
	
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
