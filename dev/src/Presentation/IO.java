package Presentation;

abstract public class IO {
    private static IO instance;

    public static IO getInstance() {
        return instance;
    }

    public static void setInstance(IO io) {
        instance = io;
    }

    public abstract void print(String message);

    public abstract String readString(String prompt);

    public abstract int readInt(String prompt);

//    public abstract boolean readBoolean(String prompt);
}
