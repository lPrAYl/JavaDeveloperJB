package client;

import utils.Setup;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Scanner scanner = new Scanner(System.in);
    private final String workingDirectory;
    private final String SERVER_ADDRESS = "127.0.0.1";
    private final int SERVER_PORT = 23456;

    public Client() {
        workingDirectory = Setup.createWorkingDirectory("/client/data/");
    }

    public void start() {
        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            System.out.print("Enter action (1 - get a file, 2 - save a file, 3 - delete a file): ");
            String choice = scanner.nextLine();
            sendToServer(choice, input, output);
//            receiveFromServer()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendToServer(String choice, DataInputStream input,
                                                  DataOutputStream output) throws IOException {
        switch (choice) {
            case "1" -> {
                System.out.print("Do you want to get the file by name or by id (1 - name, 2 - id): ");
                choice = scanner.nextLine();
                output.writeUTF("GET");
                output.writeUTF(choice);
                switch (choice) {
                    case "BY_NAME" -> {
                        System.out.print("Enter filename: ");
                        String fileName = scanner.nextLine();
                        output.writeUTF(fileName);
                    }
                    case "BY_ID" -> {
                        System.out.print("Enter id: ");
                        String id = scanner.nextLine();
                        output.writeUTF(id);
                    }
                }
                System.out.println("The request was sent.");
                String code = input.readUTF();
                switch (code) {
                    case "404" -> System.out.println("The response says that the file was not found!");
                    case "200" -> {
                        int fileLength = input.readInt();
                        byte[] fileContent = new byte[fileLength];
                        try {
                            input.readFully(fileContent, 0, fileContent.length);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String fileName = scanner.nextLine();
                        File file = new File( workingDirectory + fileName);
                        if (fileContent.length > 0) {
                            try (FileOutputStream outputInFile = new FileOutputStream(file)) {
                                outputInFile.write(fileContent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("File saved on the hard drive!");
                    }
                }
            }
            case "2" -> {
                System.out.print("Enter filename: ");
                String fileName = scanner.nextLine();
                System.out.print("Enter name of the file to be saved on server: ");
                String fileNameOnServer = scanner.nextLine();
                fileNameOnServer = fileNameOnServer.equals("") ? fileName : fileNameOnServer;
                File file = new File(workingDirectory + File.separator + fileName);
                try (FileInputStream inputFromFile = new FileInputStream(file)) {
                    byte[] fileContent = inputFromFile.readAllBytes();
                    output.writeUTF("PUT");
                    output.writeUTF(fileNameOnServer);
                    output.writeInt(fileContent.length);
                    output.write(fileContent);
                    System.out.println("The request was sent.");
                    String code = input.readUTF();
                    System.out.println(code);
                    switch (code) {
                        case "200" -> System.out.println("Response says that file is saved! ID = " + input.readInt());
                        case "403" -> System.out.println("The response says that creating the file was forbidden!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            case "3" -> {
                System.out.print("Do you want to delete the file by name or by id (1 - name, 2 - id): ");
                choice = scanner.nextLine();
                output.writeUTF("DELETE");
                output.writeUTF(choice);
                switch (choice) {
                    case "BY_NAME" -> {
                        System.out.print("Enter filename: ");
                        String fileName = scanner.nextLine();
                        output.writeUTF(fileName);
                    }
                    case "BY_ID" -> {
                        System.out.print("Enter id: ");
                        String id = scanner.nextLine();
                        output.writeUTF(id);
                    }
                }
                System.out.println("The request was sent.");
                String code = input.readUTF();
                switch (code) {
                    case "200" -> System.out.println("The response says that this file was deleted successfully!");
                    case "403" -> System.out.println("The response says that this file is not found!");
                }
            }
            case "exit" ->  {
                System.out.println("The request was sent.");
                output.writeUTF("EXIT");
            }
        }
    }
}
