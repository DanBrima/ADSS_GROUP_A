package Presentation;

import External.Constants;

import java.io.PrintStream;
import java.util.Scanner;

public abstract class Screen {

    protected final PrintStream out;
    protected final Scanner in;

    protected Screen(PrintStream out, Scanner in) {
        this.out = out;
        this.in = in;
    }

    public int handleMsg() {
        return Constants.USER_NO_INPUT;
    }

}
