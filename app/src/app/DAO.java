package app;

import java.util.ArrayList;
import java.util.List;

public class DAO {

	public static List<Account> accountList = new ArrayList<Account>();

	public static List<Transaction> transactionList = new ArrayList<Transaction>();

	public static final int N_LAST_TRANSACTION = 10;

	public static Account auth(String accountNumber, String pin) {
		Account retAccount = null;
		for (Account account : accountList) {
			if (account.getAccountNumber().equals(accountNumber) && account.getPin().equals(pin)) {
				retAccount = account;
				break;
			}
		}
		return retAccount;
	}

	public static Account getAccount(String accountNumber) {
		Account retAccount = null;
		for (Account account : accountList) {
			if (account.getAccountNumber().equals(accountNumber)) {
				retAccount = account;
				break;
			}
		}
		return retAccount;
	}
}
