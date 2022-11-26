//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//public class Session extends Thread {
//    private final ServerSocket server;
//    private final Socket socket;
//    public DataBase dataBase;
//    private final AtomicBoolean stopServer;
//
//    public Session(ServerSocket server, Socket socket, DataBase dataBase, AtomicBoolean stopServer) {
//        this.server = server;
//        this.socket = socket;
//        this.dataBase = dataBase;
//        this.stopServer = stopServer;
//    }
//
//    @Override
//    public void run() {
//        try (
//                socket;
//                DataInputStream input = new DataInputStream(socket.getInputStream());
//                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
//        ) {
//            String command = input.readUTF();
//            switch (command) {
//                case "GET" -> Server.getFile(input, output, dataBase);
//                case "PUT" -> Server.putFile(input, output, dataBase);
//                case "DELETE" -> Server.deleteFile(input, output, dataBase);
//                case "EXIT" -> stopServer.set(true);
//                default -> output.writeUTF("Invalid command!");
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (stopServer.get()) {
//                try {
//                    DataBase.saveDB(dataBase);
//                    server.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                System.exit(0);
//            }
//        }
//    }
//}
