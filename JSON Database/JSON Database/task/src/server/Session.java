package server;

import com.google.gson.Gson;
import server.request.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Callable;

public class Session implements Callable<Boolean> {
    private final Socket socket;
    private Controller controller;

    private boolean isClosedByClient;

    public Session(Socket socket) {
        this.socket = socket;
        this.isClosedByClient = false;
    }

    private Response execute(Request request) {
        controller = new Controller();
        switch (request.getType()) {
            case "get" -> {
                Command get = new Get(request);
                controller.setCommand(get);
            }
            case "set" -> {
                Command set = new Set(request);
                controller.setCommand(set);
            }
            case "delete" -> {
                Command delete = new Delete(request);
                controller.setCommand(delete);
            }
            case "exit" -> {
                Command exit = new Exit(request);
                controller.setCommand(exit);
                isClosedByClient = true;
            }
        }

        return controller.executeCommand();
    }

//    @Override
//    public void run() {
//        try(
//                socket;
//                DataInputStream input = new DataInputStream(socket.getInputStream());
//                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
//        ) {
//            Request request = new Gson().fromJson(input.readUTF(), Request.class);
//            String sentMsg = new Gson().toJson(execute(request));
//            output.writeUTF(sentMsg);
//            if (request.getType().equals("exit")) {
//                Server.INSTANCE.exit();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public Boolean call() {
        try(
                socket;
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {
            Request request = new Gson().fromJson(input.readUTF(), Request.class);
            String sentMsg = new Gson().toJson(execute(request));
            output.writeUTF(sentMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isClosedByClient;
    }
}
