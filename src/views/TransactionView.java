package views;

import Model.TransactionTypes.BizumRequest;
import Model.TransactionTypes.CashOperationRequest;
import Model.TransactionTypes.TransferRequest;
import messageService.Messages;
/**
 * Vista para pedir y validar objetos y datos de operaciones
 */
public class TransactionView extends BaseView {
	
		public TransferRequest askTransferRequest() {
			String originIBAN = askForIBAN(Messages.ASK_FOR_ORIGIN_IBAN);
			String destinyIBAN = askForIBAN(Messages.ASK_FOR_DESTINY_IBAN);
			double amount = getDoubleAmount();

			return new TransferRequest(originIBAN, destinyIBAN, amount);
		}

		public BizumRequest askBizumRequest() {
			String originPhone = askForPhoneNumber(Messages.ASK_FOR_ORIGIN_PHONE);
			String destinyPhone = askForPhoneNumber(Messages.ASK_FOR_DESTINY_PHONE);
			double amount = getDoubleAmount();

			return new BizumRequest(originPhone, destinyPhone, amount);
		}
		public CashOperationRequest askCashOperationRequest() {
			String IBAN = askForIBAN(Messages.ASK_FOR_IBAN);
			Double amount = getDoubleAmount();
			return new CashOperationRequest (IBAN,amount);
		}
	}

	
