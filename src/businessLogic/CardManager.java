package businessLogic;
import java.time.LocalDate;
import Model.Card;
import Model.User;
import util.randomNumberBuilder;
public class CardManager {

	public Card createNewCard(User accountAssociated) {
		randomNumberBuilder rB = new randomNumberBuilder();
		String cardNumber=rB.randomCardNumberBuilder();
		String CVV = rB.randomCVVBuilder();
		String cardPin= rB.randomPINBuilder();
		LocalDate creationDate=LocalDate.now();
		LocalDate expirationDate= LocalDate.now().plusYears(5);
		double dailyLimit=3000;
		Card newUserCard = new Card(cardNumber,CVV,creationDate,expirationDate,cardPin,dailyLimit);
		return newUserCard;
	} 
	
}
