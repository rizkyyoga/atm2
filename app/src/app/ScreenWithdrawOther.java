package app;

import java.util.Date;
import java.util.Scanner;

public class ScreenWithdrawOther extends Screen {

	public ScreenWithdrawOther() {
		super.message = "";
	}

	public ScreenWithdrawOther(String message) {
		super.message = message;
	}

	@Override
	public Screen action() {
		clearConsole();
		System.out.print(message);
		System.out.println("Other Withdraw");
		System.out.print("Enter amount to withdraw: ");
		Scanner in = new Scanner(System.in);
		String amount = in.nextLine();
		message = "";
		Screen screen = null;
		boolean goToSummary = true;
		if (!amount.matches("[0-9]+")) {
			goToSummary = false;
			message += "Amount should only contains numbers\n";
		} else {
			if (Double.valueOf(amount) % 10 != 0) {
				goToSummary = false;
				message += "Invalid ammount\n";
			}
			if (Double.valueOf(amount) > 1000) {
				goToSummary = false;
				message += "Maximum amount to withdraw is $1000\n";
			}
			if (Double.valueOf(amount) > Session.account.getBalance()) {
				goToSummary = false;
				message += "Insufficient balance $" + Session.account.getBalance() + "\n";
			}
		}
		if (goToSummary) {
			Session.account.setBalance(Session.account.getBalance() - Double.valueOf(amount));
			DAO.transactionList.add(
					new TransactionWithdraw(Session.account.getAccountNumber(), Double.valueOf(amount), new Date()));
			screen = new ScreenWithdrawSummary(Double.valueOf(amount));
		} else {
			screen = new ScreenWithdrawOther(message);
		}
		return screen;
	}
}
