package utils;

import core.objects.GamePlay;
import core.models.Record;

import java.io.*;
import java.util.Base64;


public class Writer {

    private final GamePlay gamePlay;


    public Writer(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }


    public void showRecord() throws IOException, ClassNotFoundException {
        try {
            FileReader reader = new FileReader("db/records.bin");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(fromString(line).getName());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Record fromString(String str) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(str);
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data));
        Record record = (Record) in.readObject();
        in.close();
        return record;
    }


    public void writeRecord(String name) throws IOException {
        Record record = new Record(name, gamePlay.getStopwatch().getElapsedTime(), gamePlay.getField().fieldSize());
        String path = "db/records.bin";

        try {
            FileWriter writer = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(toString(record));
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String toString(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}
