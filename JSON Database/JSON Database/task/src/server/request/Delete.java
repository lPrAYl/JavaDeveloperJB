package server.request;

import server.Request;
import server.Response;

public class Delete implements Command {
    Request request;

    public Delete(Request request) {
        this.request = request;
    }
    @Override
    public Response execute() {
        return Request.delete(request.getKey());
    }
}
