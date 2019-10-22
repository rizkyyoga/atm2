package app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionWithdraw extends Transaction {

	public TransactionWithdraw(String accountNumber, double amount, Date date) {
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.date = date;
	}

	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
		return accountNumber + " withdrew with amount $" + amount + " at " + dateFormat.format(date);
	}

}
