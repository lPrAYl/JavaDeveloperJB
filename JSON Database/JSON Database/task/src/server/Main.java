package server;

enum Actions {
    EXIT, WORK
}

public class Main {
    public static void main(String[] args) {
        Server.INSTANCE.run();
    }
}
