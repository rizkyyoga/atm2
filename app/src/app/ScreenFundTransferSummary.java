package app;

import java.util.Scanner;

public class ScreenFundTransferSummary extends Screen {

	private String destinationAccount;
	private double transferAmount;
	private String referenceNumber;

	public ScreenFundTransferSummary(String destinationAccount, double transferAmount, String referenceNumber) {
		this.destinationAccount = destinationAccount;
		this.transferAmount = transferAmount;
		this.referenceNumber = referenceNumber;
	}

	@Override
	public Screen action() {
		clearConsole();
		System.out.println("Fund Transfer Summary");
		System.out.println("Destination Account : " + destinationAccount);
		System.out.println("Transfer Amount     : $" + transferAmount);
		System.out.println("Reference Number    : " + referenceNumber);
		System.out.println("Balance             : $" + Session.account.getBalance());
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
