package server.request;

import server.Request;
import server.Response;

public class Controller {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public Response executeCommand() {
        return command.execute();
    }
}
