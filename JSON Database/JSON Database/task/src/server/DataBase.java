package server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import server.exceptions.NoSuchKeyException;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataBase {
    private final Lock readLock;
    private final Lock writeLock;
    private Path pathToDirectory;
    private Path pathToFile;
    private static DataBase dataBase;
    private static JsonObject storage;

    public static DataBase getDataBase() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

    private DataBase() {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();
        try {
//            pathToDirectory = Files.createDirectories(Paths.get(System.getProperty("user.dir"),
//                    "JSON Database", "task", "src", "server", "data"));
            pathToDirectory = Files.createDirectories(Paths.get(System.getProperty("user.dir"),
                    "src", "server", "data"));
            pathToFile = pathToDirectory.resolve("db.json");
            if (!Files.exists(pathToFile)) {
                Files.createFile(pathToFile);
            }
            if (storage == null) {
                storage = new JsonObject();
                save();
            } else {
                String content = new String(Files.readAllBytes(pathToFile));
                Gson gson = new Gson();
                storage = gson.fromJson(content, JsonObject.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set(JsonElement key, JsonElement value) {
        try {
            writeLock.lock();
            if (key.isJsonPrimitive()) {
                storage.add(key.getAsString(), value);
            } else if (key.isJsonArray()) {
                JsonArray keys = key.getAsJsonArray();
                String toAdd = keys.remove(keys.size() - 1).getAsString();
                findElement(keys, true).getAsJsonObject().add(toAdd, value);
            } else {
                throw new NoSuchKeyException();
            }
            save();
        } finally {
            writeLock.unlock();
        }
    }

    public JsonElement get(JsonElement key) {
        try {
            readLock.lock();
            if (key.isJsonPrimitive() && storage.has(key.getAsString())) {
                return storage.get(key.getAsString());
            } else if (key.isJsonArray()) {
                return findElement(key.getAsJsonArray(), false);
            }
            throw new NoSuchKeyException();
        } finally {
            readLock.unlock();
        }
    }

    public void delete(JsonElement key) {
        try {
            writeLock.lock();
            if (key.isJsonPrimitive() && storage.has(key.getAsString())) {
                storage.remove(key.getAsString());
            } else if (key.isJsonArray()) {
                JsonArray keys = key.getAsJsonArray();
                String toRemove = keys.remove(keys.size() - 1).getAsString();
                findElement(keys, false).getAsJsonObject().remove(toRemove);
                save();
            } else {
                throw new NoSuchKeyException();
            }
        } finally {
            writeLock.unlock();
        }
    }

    private JsonElement findElement(JsonArray keys, boolean createIfAbsent) {
        JsonElement tmp = storage;
        if (createIfAbsent) {
            for (JsonElement key: keys) {
                if (!tmp.getAsJsonObject().has(key.getAsString())) {
                    tmp.getAsJsonObject().add(key.getAsString(), new JsonObject());
                }
                tmp = tmp.getAsJsonObject().get(key.getAsString());
            }
        } else {
            for (JsonElement key: keys) {
                if (!key.isJsonPrimitive() || !tmp.getAsJsonObject().has(key.getAsString())) {
                    throw new NoSuchKeyException();
                }
                tmp = tmp.getAsJsonObject().get(key.getAsString());
            }
        }
        return tmp;
    }

    public void save() {
        try (Writer writer = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            gson.toJson(storage, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
