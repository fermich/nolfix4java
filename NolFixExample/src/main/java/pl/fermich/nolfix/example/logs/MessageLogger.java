package pl.fermich.nolfix.example.logs;

public class MessageLogger {

    public void logError(String msg) {
        System.err.println(msg);
    }

    public void logInfo(String msg) {
        System.out.println(msg);
    }
}
