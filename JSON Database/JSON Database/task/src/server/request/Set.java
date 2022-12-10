package server.request;

import server.Request;
import server.Response;

public class Set implements Command {

    Request request;

    public Set(Request request) {
        this.request = request;
    }
    @Override
    public Response execute() {
        return Request.set(request.getKey(), request.getValue());
    }
}
