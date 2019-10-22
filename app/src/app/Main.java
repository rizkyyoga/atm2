package app;

public class Main {

	public static void main(String[] args) {
		Screen screen = new ScreenDataLoad();
		while (true) {
			screen = screen.action();
		}
	}
}
