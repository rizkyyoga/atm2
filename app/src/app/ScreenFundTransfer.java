package app;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Scanner;

public class ScreenFundTransfer extends Screen {

	@Override
	public Screen action() {
		boolean stoper;
		Scanner in = new Scanner(System.in);
		String dest, amount, ref;
		message = "";
		Account accDest;
		do {
			stoper = false;
			clearConsole();
			System.out.println(message);
			System.out.println("Please enter destination account and press enter to continue or");
			System.out.print("press enter to go back to Transaction: ");
			dest = in.nextLine();
			if (dest.isEmpty())
				return new ScreenTransaction();
			message = "";
			if (!dest.matches("[0-9]+")) {
				message += "Invalid account\n";
				stoper = true;
			}
			accDest = DAO.getAccount(dest);
			if (accDest == null) {
				message += "Invalid account\n";
				stoper = true;
			}
		} while (stoper);

		do {
			stoper = false;
			clearConsole();
			System.out.println(message);
			System.out.println("Please enter transfer amount and");
			System.out.println("press enter to continue or");
			System.out.print("press enter to go back to Transaction: ");
			amount = in.nextLine();
			if (amount.isEmpty())
				return new ScreenTransaction();
			message = "";
			if (!amount.matches("[0-9]+")) {
				message += "Amount should only contains numbers\n";
				stoper = true;
			} else {
				if (Long.valueOf(amount) < 1) {
					message += "Minimum amount to withdraw is $1\n";
					stoper = true;
				}
				if (Long.valueOf(amount) > 1000) {
					message += "Maximum amount to withdraw is $1000\n";
					stoper = true;
				}
				if (Long.valueOf(amount) > Session.account.getBalance()) {
					message += "Insufficient balance $" + Session.account.getBalance() + "\n";
					stoper = true;
				}
			}
		} while (stoper);

		do {
			stoper = false;
			ref = generateTransferId();
			clearConsole();
			System.out.println(message);
			System.out.println("Reference Number: " + ref);
			System.out.print("press enter to continue");
			in.nextLine();
			message = "";
			if (!ref.matches("[0-9]+")) {
				message += "Invalid Reference Number\n";
				stoper = true;
			}
		} while (stoper);

		clearConsole();
		System.out.println("Destination Account : " + dest);
		System.out.println("Transfer Amount     : $" + amount);
		System.out.println("Reference Number    : " + ref);
		System.out.println();
		System.out.println("1. Confirm Trx");
		System.out.println("2. Cancel Trx");
		System.out.print("Choose option[2]: ");
		String option = in.nextLine();
		Screen screen = null;
		switch (option) {
		case "1":
			Session.account.setBalance(Session.account.getBalance() - Double.valueOf(amount));
			accDest.setBalance(accDest.getBalance() + Double.valueOf(amount));
			DAO.transactionList.add(new TransactionFundTransfer(Session.account.getAccountNumber(),
					Double.valueOf(amount), new Date(), dest));
			screen = new ScreenFundTransferSummary(dest, Double.valueOf(amount), ref);
			break;
		case "2":
			screen = new ScreenTransaction();
			break;
		default:
			screen = new ScreenTransaction();
		}
		return screen;
	}

	private String generateTransferId() {
		String NUMBER = "0123456789";
		String DATA_FOR_RANDOM_STRING = NUMBER;
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder(6);
		for (int i = 0; i < 6; i++) {
			int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
			char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
			sb.append(rndChar);
		}
		return sb.toString();
	}

}
