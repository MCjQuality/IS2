package it.unibs.ing.elaborato.model.hierarchy;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;

import java.io.*;

public class StandardSerializationBasedHierarchyRepository implements HierarchyRepository
{
    private final String filepath;

    public StandardSerializationBasedHierarchyRepository(String filepath)
    {
        this.filepath = filepath;
    }

    @Override
    public Hierarchies read() throws FileReaderException {
        Hierarchies storage = new Hierarchies();

        File file = new File(filepath);
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                storage = (Hierarchies) ois.readObject();
            } catch (EOFException ignored) {

            } catch (IOException | ClassNotFoundException e) {
                throw new FileReaderException();
            }
        }
        return storage;
    }

    @Override
    public void write(Hierarchies hierarchies) throws FileWriterException
    {
        File file = new File(filepath);
        try {
            if (file.createNewFile())
            {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(hierarchies);
                oos.close();
            }
            else
            {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(hierarchies);
                oos.close();
            }
        } catch (IOException e) {
            throw new FileWriterException();
        }
    }
}
