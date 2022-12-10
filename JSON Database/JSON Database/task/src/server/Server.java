package server;

import java.io.*;
import java.net.ServerSocket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum Server {

    INSTANCE;
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final int PORT = 23456;
    private static boolean stopServer = false;

    public void run() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server started!");
            while (!executor.isShutdown()) {
                if (executor.submit(new Session(server.accept())).get()) {
                    executor.shutdown();
                }
//                executor.submit(() -> new Session(server.accept()));
            }
            executor.shutdown();
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
