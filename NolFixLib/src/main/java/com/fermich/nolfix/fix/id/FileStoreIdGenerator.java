package com.fermich.nolfix.fix.id;

import java.io.*;

public class FileStoreIdGenerator implements IdGenerator {

    private String counterFile;

    public FileStoreIdGenerator(String counterFile) {
        this.counterFile = counterFile;
    }

    public synchronized int nextId() {
        try {
            int id = readLastId();
            id++;
            writeLastId(id);
            return id;
        } catch (IOException ex) {
            throw new IdGeneratorException("Error while getting next ID for message!", ex);
        }
    }

    private int readLastId() throws IOException {
        File f = new File(counterFile);
        if (!f.exists()) {
            return 0;
        } else {
            DataInputStream dis = new DataInputStream(new FileInputStream(f));
            int lastId = dis.readInt();
            dis.close();
            return lastId;
        }
    }

    private void writeLastId(int lastId) throws IOException {
        File f = new File(counterFile);
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
        dos.writeInt(lastId);
        dos.close();
    }

}
