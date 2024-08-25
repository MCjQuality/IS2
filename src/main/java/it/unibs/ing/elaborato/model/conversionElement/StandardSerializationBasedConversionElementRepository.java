package it.unibs.ing.elaborato.model.conversionElement;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;

import java.io.*;

public class StandardSerializationBasedConversionElementRepository implements ConversionElementRepository
{
    private final String filepath;

    public StandardSerializationBasedConversionElementRepository(String filepath)
    {
        this.filepath = filepath;
    }

    @Override
    public void write(ConversionElements conversionElements) throws FileWriterException
    {
        File file = new File(filepath);
        try {
            if (file.createNewFile())
            {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(conversionElements);
                oos.close();
            }
            else
            {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(conversionElements);
                oos.close();
            }
        } catch (IOException e) {
            throw new FileWriterException();
        }
    }

    @Override
    public ConversionElements read() throws FileReaderException
    {
        ConversionElements storage = new ConversionElements();

        File file = new File(filepath);
        if (file.exists() && file.length() > 0)
        {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                storage = (ConversionElements) ois.readObject();
            } catch (EOFException ignored) {

            } catch (IOException | ClassNotFoundException e) {
                throw new FileReaderException();
            }
        }
        return storage;
    }
}
