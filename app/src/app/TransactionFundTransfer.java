package app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionFundTransfer extends Transaction {

	private String destinationAccountNumber;

	public TransactionFundTransfer(String accountNumber, double amount, Date date, String destinationAccountNumber) {
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.date = date;
		this.destinationAccountNumber = destinationAccountNumber;
	}

	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
		return accountNumber + " transferred to " + destinationAccountNumber + " with amount $" + amount + " at "
				+ dateFormat.format(date);
	}
}
