package server;

import com.google.gson.JsonElement;
import server.exceptions.NoSuchKeyException;

public class Request {
    private String type;
    private JsonElement key;
    private JsonElement value;

    public Request(String type, JsonElement key, JsonElement value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public JsonElement getKey() {
        return key;
    }

    public JsonElement getValue() {
        return value;
    }

    public static Response get(JsonElement key) {
        Response response;
        try {
            DataBase.getDataBase().get(key);
            response = Response.builder()
                    .response("OK")
                    .value(DataBase.getDataBase().get(key))
                    .build();
        } catch (NoSuchKeyException e) {
            response = Response.builder()
                    .response("ERROR")
                    .reason("No such key")
                    .build();
        }
        return response;
    }

    public static Response set(JsonElement key, JsonElement value) {
        DataBase.getDataBase().set(key, value);
        return Response.builder()
                .response("OK")
                .build();
    }

    public static Response delete(JsonElement key) {
        Response response;
        try {
            DataBase.getDataBase().delete(key);
            response = Response.builder()
                    .response("OK")
                    .build();
        } catch (NoSuchKeyException e) {
            response = Response.builder()
                    .response("ERROR")
                    .reason("No such key")
                    .build();
        }
        return response;
    }

    public static Response exit() {
        DataBase.getDataBase().save();
        return Response.builder()
                .response("OK")
                .build();
    }
}
