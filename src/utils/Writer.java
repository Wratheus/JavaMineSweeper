package utils;

import core.components.GamePlay;
import core.models.Record;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Base64;

public class Writer {
    GamePlay gamePlay;
    public Writer(GamePlay gamePlay){
        this.gamePlay = gamePlay;
    }
    private Record fromString(String str) throws IOException , ClassNotFoundException {
        byte [] data = Base64.getDecoder().decode(str);
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data));
        Record record = (Record) in.readObject();
        in.close();
        return record;
    }
    private String toString( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    public void writeRecord(String name) throws IOException {
        Record record = new Record(name, gamePlay.stopwatch.elapsedTime, gamePlay.field.fieldSize);
        Path path = Paths.get("db/records.bin");
        try(BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)){
            writer.write(toString(record) + "\n");
        }catch(IOException ex){
            ex.printStackTrace();
        }
        //System.out.println(record);
    }
    public void showRecord() throws IOException, ClassNotFoundException {
        Path path = Paths.get("db/records.bin");
        byte[] bytes = Files.readAllBytes(path);
        String[] records = toString(bytes).split("\n");
        for (String record : records) {
            System.out.println(record);
        }
    }

//    public Record[] showAllRecords() {
//        ArrayList<Record> records = new ArrayList<>();
//        Path path = Paths.get("db/records.bin");
//        while ()
//    }
}
