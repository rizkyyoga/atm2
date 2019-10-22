package app;

import java.util.Date;
import java.util.Scanner;

public class ScreenWithdraw extends Screen {

	public ScreenWithdraw() {
		super.message = "";
	}

	public ScreenWithdraw(String message) {
		super.message = message;
	}

	@Override
	public Screen action() {
		clearConsole();
		System.out.print(message);
		System.out.println("1. $10");
		System.out.println("2. $50");
		System.out.println("3. $100");
		System.out.println("4. Other");
		System.out.println("5. Back");
		System.out.print("Please choose option[5]: ");
		Scanner in = new Scanner(System.in);
		String option = in.nextLine();
		Screen screen = null;
		message = "";
		switch (option) {
		case "1":
			if (Session.account.getBalance() < 10) {
				message += "Insufficient balance $" + Session.account.getBalance() + "\n";
				screen = new ScreenWithdraw(message);
			} else {
				Session.account.setBalance(Session.account.getBalance() - 10);
				DAO.transactionList.add(new TransactionWithdraw(Session.account.getAccountNumber(), 10, new Date()));
				screen = new ScreenWithdrawSummary(10);
			}
			break;
		case "2":
			if (Session.account.getBalance() < 50) {
				message += "Insufficient balance $" + Session.account.getBalance() + "\n";
				screen = new ScreenWithdraw(message);
			} else {
				Session.account.setBalance(Session.account.getBalance() - 50);
				DAO.transactionList.add(new TransactionWithdraw(Session.account.getAccountNumber(), 50, new Date()));
				screen = new ScreenWithdrawSummary(50);
			}
			break;
		case "3":
			if (Session.account.getBalance() < 100) {
				message += "Insufficient balance $" + Session.account.getBalance() + "\n";
				screen = new ScreenWithdraw(message);
			} else {
				Session.account.setBalance(Session.account.getBalance() - 100);
				DAO.transactionList.add(new TransactionWithdraw(Session.account.getAccountNumber(), 100, new Date()));
				screen = new ScreenWithdrawSummary(100);
			}
			break;
		case "4":
			screen = new ScreenWithdrawOther();
			break;
		case "5":
			screen = new ScreenTransaction();
			break;
		default:
			screen = new ScreenTransaction();
		}
		return screen;
	}

}
