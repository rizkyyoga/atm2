package app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ScreenWithdrawSummary extends Screen {

	private double withdraw;

	public ScreenWithdrawSummary(double withdraw) {
		this.withdraw = withdraw;
	}

	@Override
	public Screen action() {
		clearConsole();
		System.out.println("Summary");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
		System.out.println("Date : " + dateFormat.format(new Date()));
		System.out.println("Withdraw : $" + withdraw);
		System.out.println("Balance : $" + Session.account.getBalance());
		System.out.println();
		System.out.println("1. Transaction");
		System.out.println("2. Exit");
		System.out.print("Choose option[2]: ");
		Scanner in = new Scanner(System.in);
		String option = in.nextLine();
		Screen screen = null;
		switch (option) {
		case "1":
			screen = new ScreenTransaction();
			break;
		case "2":
			screen = new ScreenWelcome();
			break;
		default:
			screen = new ScreenWelcome();
		}
		return screen;
	}

}
