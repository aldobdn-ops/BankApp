package Model.TransactionTypes;

import Model.Card;
import Model.Transaction;

public class CardPayment extends Transaction {

	private Card paymentCard;
	
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
