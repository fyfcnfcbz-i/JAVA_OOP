package ru.gb.family_tree.writer;

import java.io.*;

public class FileHandler implements Writer {
    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean save(Serializable serializable){
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))){
            objectOutputStream.writeObject(serializable);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Object read(){
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))){
            return objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }








}
