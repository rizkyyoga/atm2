package app;

abstract class Screen {
	protected String message;

	public abstract Screen action();

	protected final static void clearConsole() {
		for (int i = 0; i < 50; ++i)
			System.out.println();
	}
}
