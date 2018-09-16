package sample.common;

public class Helper {
    public static void log(String msg) {
        System.out.println("\033[0;92m" + "" + "\033[0;32m" + msg);
    }

    public static void log(String msg, int level) {
        if (level > 1) {
            System.out.println("\033[0;37m" + "  " + msg);
            return;
        }
    }
}
