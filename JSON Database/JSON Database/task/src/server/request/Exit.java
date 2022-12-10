package server.request;

import server.Request;
import server.Response;

public class Exit implements Command {

    Request request;

    public Exit(Request request) {
        this.request = request;
    }
    @Override
    public Response execute() {
        return Request.exit();
    }
}
