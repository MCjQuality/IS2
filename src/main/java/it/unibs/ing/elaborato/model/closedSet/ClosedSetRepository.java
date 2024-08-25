package it.unibs.ing.elaborato.model.closedSet;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposals;

import java.io.IOException;
import java.util.List;

public interface ClosedSetRepository {
    void add(ExchangeProposals closedSet);
    List<ExchangeProposals> getClosedSets();
    List<ExchangeProposals> read() throws IOException, ClassNotFoundException, FileReaderException;
    void write() throws FileWriterException;
}
