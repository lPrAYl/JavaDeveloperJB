package server;

import utils.Setup;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;

public class DataBase implements Serializable {
    private static final long serialVersionId = 1L;

    public static void saveDB (Object obj) {
        try {
            FileOutputStream fos = new FileOutputStream(Objects.requireNonNull(Setup.createWorkingDirectory("/server/data/data.db")));
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object loadDB() {
        Object obj;

        try {
            FileInputStream fis = new FileInputStream(ID_MAP_PATH);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No id map found! Creating new one...");
            obj = new HashMap<>();
            saveDB(obj);
        }

        return obj instanceof HashMap ? obj : new HashMap<>();
    }
}







//    private TreeMap<Integer, String> dataBase;
//
//    public DataBase() {
//        dataBase = new TreeMap<>();
//    }
//
//    public String getFileById(int id) {
//        return dataBase.get(id);
//    }
//
//    public int put(String fileName) {
//        int id = (dataBase.size() > 0) ? dataBase.lastKey() + 1 : 1;
//        dataBase.put(id, fileName);
//
//        return id;
//    }

//


