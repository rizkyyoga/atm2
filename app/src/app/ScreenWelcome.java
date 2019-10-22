package app;

import java.util.Scanner;

public class ScreenWelcome extends Screen {

	public ScreenWelcome() {
		super.message = "";
	}

	public ScreenWelcome(String message) {
		super.message = message;
	}

	@Override
	public Screen action() {
		Scanner in = new Scanner(System.in);
		String accountNumber, pin;
		boolean stoper;

		do {
			stoper = false;
			clearConsole();
			System.out.print(message);
			System.out.print("Enter Account Number: ");
			accountNumber = in.nextLine();
			message = "";
			if (accountNumber.length() != 6) {
				message += "Account Number should have 6 digits length\n";
				stoper = true;
			}
			if (!accountNumber.matches("[0-9]+")) {
				message += "Account Number should only contains numbers\n";
				stoper = true;
			}
		} while (stoper);

		do {
			stoper = false;
			System.out.print(message);
			System.out.print("Enter PIN: ");
			pin = in.nextLine();
			message = "";
			if (pin.length() != 6) {
				message += "PIN should have 6 digits length\n";
				stoper = true;
			}
			if (!pin.matches("[0-9]+")) {
				message += "PIN should only contains numbers\n";
				stoper = true;
			}
		} while (stoper);

		Session.account = DAO.auth(accountNumber, pin);
		if (Session.account == null) {
			message += "Invalid Account Number/PIN\n";
			return new ScreenWelcome(message);
		}
		return new ScreenTransaction();
	}
}
