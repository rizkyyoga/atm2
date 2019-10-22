package app;

import java.util.Scanner;

public class ScreenTransaction extends Screen {

	@Override
	public Screen action() {
		clearConsole();
		System.out.println("1. Withdraw");
		System.out.println("2. Fund Transfer");
		System.out.println("3. View Last " + DAO.N_LAST_TRANSACTION + " Transaction");
		System.out.println("4. Exit");
		System.out.print("Please choose option[3]: ");
		Scanner in = new Scanner(System.in);
		String option = in.nextLine();
		Screen screen = null;
		switch (option) {
		case "1":
			screen = new ScreenWithdraw();
			break;
		case "2":
			screen = new ScreenFundTransfer();
			break;
		case "3":
			screen = new ScreenLast10Transaction();
			break;
		case "4":
			screen = new ScreenWelcome();
			break;
		default:
			screen = new ScreenWelcome();
		}
		return screen;
	}

}
