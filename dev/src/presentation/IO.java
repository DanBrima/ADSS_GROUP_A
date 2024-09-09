package presentation;

abstract public class IO {
    private static IO instance;

    public static IO getInstance() {
        return instance;
    }

    public static void setInstance(IO io) {
        instance = io;
    }

    abstract void print(String message);

    abstract String readString(String prompt);

    abstract int readInt(String prompt);
}
