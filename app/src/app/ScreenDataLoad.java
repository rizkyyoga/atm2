package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ScreenDataLoad extends Screen {

	private static final String FILE_PATH = "C:\\Users\\Yoga_RYO495\\eclipse-workspace\\atm2\\data.csv";

	public ScreenDataLoad() {
		super.message = "";
	}

	public ScreenDataLoad(String message) {
		super.message = message;
	}

	@Override
	public Screen action() {
		Screen screen = null;
		Scanner in = new Scanner(System.in);
		clearConsole();
		System.out.println(message);
		System.out.println("Please wait for loading file ...");
		List<Account> inputList = new ArrayList<Account>();
		try {
			File inputF = new File(FILE_PATH);
			InputStream inputFS = new FileInputStream(inputF);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

			// Load from csv to list parallel
			inputList = br.lines().skip(1).map(mapToItem).parallel().collect(Collectors.toList());

			// Validate uniqueness of an account
			inputList = inputList.stream().filter(distinctByKey(Account::getAccountNumber))
					.collect(Collectors.toList());
			DAO.accountList = inputList;
			br.close();
			screen = new ScreenWelcome();
			System.out.print("Load complete. Press Enter to Continue ");
			in.nextLine();
		} catch (FileNotFoundException e) {
			message = "File not found. Please set FILE_PATH to the correct path.\n";
			screen = new ScreenDataLoad(message);
		} catch (IOException e) {
			message = e.getMessage() + "\n";
			screen = new ScreenDataLoad(message);
		} catch (Exception e) {
			message = e.getMessage() + "\n";
			screen = new ScreenDataLoad(message);
		}
		return screen;
	}

	private Function<String, Account> mapToItem = (line) -> {
		String[] p = line.split(",");// a CSV has comma separated lines
		Account item = new Account();
		item.setName(p[0]);
		item.setPin(p[1]);
		item.setBalance(Double.valueOf(p[2]));
		item.setAccountNumber(p[3]);
		return item;
	};

	private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}

}
