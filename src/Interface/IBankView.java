package Interface;

import java.util.ArrayList;


public interface IBankView {

	int showMenuAndReturnUserOption(Runnable showMenu);

    public void showMainMenu();

    public String askCustomerAndGetAnswer(String question);
    
    public void showMessage(String msg);

}
