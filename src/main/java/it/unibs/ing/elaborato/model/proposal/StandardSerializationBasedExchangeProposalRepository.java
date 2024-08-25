package it.unibs.ing.elaborato.model.proposal;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;

import java.io.*;

public class StandardSerializationBasedExchangeProposalRepository implements ExchangeProposalRepository
{
    private final String filepath;

    public StandardSerializationBasedExchangeProposalRepository(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public ExchangeProposals read() throws FileReaderException
    {
        ExchangeProposals storage = new ExchangeProposals();

        File file = new File(filepath);
        if (file.exists() && file.length() > 0)
        {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                storage = (ExchangeProposals) ois.readObject();
            } catch (EOFException ignored) {

            } catch (IOException | ClassNotFoundException e) {
                throw new FileReaderException();
            }
        }
        return storage;
    }

    @Override
    public void write(ExchangeProposals proposals) throws FileWriterException
    {
        File file = new File(filepath);
        try {
            if (file.createNewFile())
            {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(proposals);
                oos.close();
            }
            else
            {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(proposals);
                oos.close();
            }
        } catch (IOException e) {
            throw new FileWriterException();
        }
    }

}
