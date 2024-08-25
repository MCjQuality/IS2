package it.unibs.ing.elaborato.model.closedSet;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposals;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StandardSerializationBasedClosedSetRepository implements ClosedSetRepository
{
    private final List<ExchangeProposals> closedSets;
    private final String filepath;

    public StandardSerializationBasedClosedSetRepository(String filepath) throws FileReaderException
    {
        this.filepath = filepath;
        this.closedSets = read();
    }

    public void add(ExchangeProposals closedSet)
    {
        closedSets.add(closedSet);
    }

    public List<ExchangeProposals> getClosedSets()
    {
        return closedSets;
    }

    public List<ExchangeProposals> read() throws FileReaderException
    {
        List<ExchangeProposals> closedSets = new ArrayList<>();

        File file = new File(filepath);
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
            {
                closedSets = (List<ExchangeProposals>) ois.readObject();
            } catch (EOFException ignored) {

            } catch (IOException | ClassNotFoundException e) {
                throw new FileReaderException();
            }
        }
        return closedSets;
    }

    @Override
    public void write() throws FileWriterException
    {
        File file = new File(filepath);
        try {
            if (file.createNewFile())
            {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(closedSets);
                oos.close();
            }
            else
            {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(closedSets);
                oos.close();
            }
        } catch (IOException e) {
            throw new FileWriterException();
        }
    }
}
