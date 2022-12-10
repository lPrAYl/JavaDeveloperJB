package server.request;

import server.Request;
import server.Response;

public class Get implements Command {

    Request request;

    public Get(Request request) {
        this.request = request;
    }
    @Override
    public Response execute() {
        return Request.get(request.getKey());
    }
}
