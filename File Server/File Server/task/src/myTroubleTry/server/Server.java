package server;

import utils.Setup;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {
    private final String workingDirectory;
    private HashMap<Integer, String> dataBase;
    private final String SERVER_ADDRESS = "127.0.0.1";
    private final int SERVER_PORT = 23456;
    private final int poolSize = Runtime.getRuntime().availableProcessors();
    private final ExecutorService executor = Executors.newFixedThreadPool(poolSize);
    private AtomicBoolean stopServer;

    public Server() {
        stopServer = new AtomicBoolean(false);
        workingDirectory = Setup.createWorkingDirectory("/server/data/");
        dataBase = (HashMap<Integer, String>) DataBase.loadDB();
    }

    public void start() {
        try (ServerSocket server = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server started!");

            while (!stopServer.get()) {
                Socket socket = server.accept();
//                executor.submit(new Session(server, socket, dataBase, stopServer));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}


//package myTroubleTry.server;
//
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//public class Server {
//
//
//    private static final ExecutorService executor = Executors.newFixedThreadPool(poolSize);
//    AtomicBoolean stopServer = new AtomicBoolean(false);
//
//    public Server() {
//        dataBase = (DataBase) DataBase.loadDB();
//    }
//
//    public static File getFileDB() {
//        return new File(workingDirectory + File.separator + "data.db");
//    }
//
//
//    public void start() {
//        try (ServerSocket server = new ServerSocket(PORT)) {
//            System.out.println("Server started!");
//
//            while (!stopServer.get()) {
//                Socket socket = server.accept();
//                executor.submit(new Session(server, socket, dataBase, stopServer));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            executor.shutdown();
//            System.exit(0);
//        }
//    }
//
//    public static Path createWorkingDirectory() {
//        Path path = Path.of(System.getProperty("user.dir"),
//                "src", "server", "data");
//        try {
//            Files.createDirectories(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return path;
//    }
//
//    public static void getFile(DataInputStream input, DataOutputStream output, DataBase dataBase) throws IOException {
//        String type = input.readUTF();
//        String fileName = null;
//        if (type.equalsIgnoreCase("BY_ID")) {
//            int id = Integer.parseInt(input.readUTF());
//            fileName = dataBase.getFileById(id);
//        } else {
//            fileName = input.readUTF();
//        }
//        File file = new File(workingDirectory + File.separator + fileName);
//        if (file.exists() && !file.isDirectory()) {
//            output.writeUTF("200");
//            byte[] fileBytes = Files.readAllBytes(Path.of(String.valueOf(workingDirectory), fileName));
//            output.writeInt(fileBytes.length);
//            output.write(fileBytes);
//            output.flush();
//        } else {
//            output.writeUTF("404");
//        }
//    }
//
//    public static void putFile(DataInputStream input, DataOutputStream output, DataBase dataBase) throws IOException {
//        String fileName = input.readUTF();
//        int fileLength = input.readInt();
//        byte[] content = new byte[fileLength];
//        input.readFully(content, 0, content.length);
//        File file = new File(workingDirectory + File.separator + fileName);
//
//        try (FileOutputStream outputInFile = new FileOutputStream(file)) {
//            outputInFile.write(content);
//            int id = dataBase.put(fileName);
//            output.writeUTF("200");
//            output.writeInt(id);
//        } catch (Exception e) {
//            output.writeUTF("404");
//        }
//    }
//
//    public static void deleteFile(DataInputStream input, DataOutputStream output,
//                                  DataBase dataBase) throws IOException {
//
//        String type = input.readUTF();
//        String fileName = null;
//        if (type.equalsIgnoreCase("BY_ID")) {
//            int id = Integer.parseInt(input.readUTF());
//            fileName = dataBase.getFileById(id);
//        } else {
//            fileName = input.readUTF();
//        }
//        File file = new File(workingDirectory + File.separator + fileName);
//        if (file.delete()) {
//            output.writeUTF("200");
//        } else {
//            output.writeUTF("404");
//        }
//    }
//}

