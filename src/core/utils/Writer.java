package core.utils;

import core.objects.GamePlay;
import core.models.Record;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;


public class Writer {

    private final GamePlay gamePlay;
    private final String fileName = "records.bin";
    private final String directoryName = "JavaSweeperDB/";
    private final File file;



    public Writer(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdir();
        }
        file = new File(directoryName + "/" + fileName);
    }


    public ArrayList<Record> showRecord() {
        try {
            ArrayList<Record> records = new ArrayList<>();
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                records.add(Objects.requireNonNull(fromString(line)));
            }
            reader.close();
            return records;
        } catch (IOException e ) {
            e.printStackTrace();
        }
        return null;
    }

    private Record fromString(String str) {
        try {
            byte[] data = Base64.getDecoder().decode(str);
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data));
            Record record = (Record) in.readObject();
            in.close();
            return record;
        }catch (IOException | ClassNotFoundException error) {
            error.printStackTrace();
            return null;
        }
    }


    public void writeRecord(String name) {
        Record record = new Record(name, gamePlay.getStopwatch().getElapsedTime(), gamePlay.getField().fieldSize());

        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(Objects.requireNonNull(toString(record)));
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String toString(Serializable o) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        }catch (IOException error) {
            error.printStackTrace();
            return null;
        }
    }
}
