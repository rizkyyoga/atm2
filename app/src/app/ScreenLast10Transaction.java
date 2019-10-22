package app;

import java.util.Scanner;

public class ScreenLast10Transaction extends Screen {

	@Override
	public Screen action() {
		Scanner in = new Scanner(System.in);
		clearConsole();
		int showed = 0;
		for (int i = DAO.transactionList.size() - 1; i >= 0; i--) {
			if (Session.account.getAccountNumber().equals(DAO.transactionList.get(i).getAccountNumber())) {
				System.out.println(DAO.transactionList.get(i));
				showed++;
				if (showed >= DAO.N_LAST_TRANSACTION)
					break;
			}
		}
		if (showed == 0) {
			System.out.println("There is no transaction yet");
		}
		System.out.print("Press enter to go back to Transaction");
		in.nextLine();
		return new ScreenTransaction();
	}
}
