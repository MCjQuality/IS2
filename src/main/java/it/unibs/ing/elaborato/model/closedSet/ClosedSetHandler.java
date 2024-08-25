package it.unibs.ing.elaborato.model.closedSet;

import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposals;

import java.util.List;

public class ClosedSetHandler {

    private final ClosedSetRepository repo;

    public ClosedSetHandler(ClosedSetRepository repo) {
        this.repo = repo;
    }

    public void add(ExchangeProposals closedSet) {
        repo.add(closedSet);
    }

    public List<ExchangeProposals> getClosedSets() {
        return repo.getClosedSets();
    }

    public void write() throws FileWriterException {
        repo.write();
    }

}
