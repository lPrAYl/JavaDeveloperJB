package client;

import com.beust.jcommander.JCommander;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Client {

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;

    public void run(String[] args) {
        try (
                Socket socket = new Socket(SERVER_ADDRESS, PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                ) {
            System.out.println("Client started");

            Args jargs = new Args();
            JCommander.newBuilder()
                    .addObject(jargs)
                    .build()
                    .parse(args);
            String sentMsg;
            if (jargs.getInputFile() != null) {
//                Path pathToDirectory = Files.createDirectories(Paths.get(System.getProperty("user.dir"),
//                        "JSON Database", "task", "src", "client", "data"));
                Path pathToDirectory = Files.createDirectories(Paths.get(System.getProperty("user.dir"),
                        "src", "client", "data"));
                Path pathToFile = pathToDirectory.resolve(jargs.getInputFile());
                sentMsg = new String(Files.readAllBytes(pathToFile));
            } else {
                sentMsg = new Gson().toJson(jargs);
            }
            System.out.println("Send: " + sentMsg);
            output.writeUTF(sentMsg);
            String receivedMsg = input.readUTF();
            System.out.println("Received: " + receivedMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
