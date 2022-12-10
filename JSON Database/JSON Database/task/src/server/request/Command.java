package server.request;

import server.Request;
import server.Response;

public interface Command {
    Response execute();
}
