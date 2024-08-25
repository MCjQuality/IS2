package it.unibs.ing.elaborato.model.district;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;

import java.io.*;

public class StandardSerializationBasedDistrictRepository implements DistrictRepository
{
    private final String filepath;

    public StandardSerializationBasedDistrictRepository(String filepath)
    {
        this.filepath = filepath;
    }

    @Override
    public Districts read() throws FileReaderException
    {
        Districts storage = new Districts();
        File file = new File(filepath);
        if (file.exists() && file.length() > 0)
        {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
            {
                storage = (Districts) ois.readObject();
            }
            catch (EOFException ignored) {}
            catch (IOException | ClassNotFoundException e)
            {
                throw new FileReaderException();
            }
        }
        return storage;
    }

    @Override
    public void write(Districts districts) throws FileWriterException
    {
        File file = new File(filepath);
        try {
            if (file.createNewFile())
            {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(districts);
                oos.close();
            }
            else
            {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(districts);
                oos.close();
            }
        } catch (IOException e) {
            throw new FileWriterException();
        }
    }
}
