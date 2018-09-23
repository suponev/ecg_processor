package sample.common;

public class Helper {
    public static void log(Object msg) {
        if (!(msg instanceof String)) {
            msg = msg.toString();
        }
        System.out.println("\033[0;92m" + "" + "\033[0;32m" + msg);
    }

    public static void log(Object msg, int level) {
        if (!(msg instanceof String)) {
            msg = msg.toString();
        }
        if (level > 1) {
            System.out.println("\033[0;37m" + "  " + msg);
            return;
        }
    }
}
