package it.unibs.ing.elaborato.model.proposal;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;

public interface ExchangeProposalRepository {
    ExchangeProposals read() throws FileReaderException;
    void write(ExchangeProposals proposals) throws FileWriterException;
}
