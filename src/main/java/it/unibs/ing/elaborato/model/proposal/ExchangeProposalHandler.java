package it.unibs.ing.elaborato.model.proposal;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.model.user.Consumer;

import java.util.List;

public class ExchangeProposalHandler {

    private final ExchangeProposalRepository repo;
    private final ExchangeProposals proposals;

    public ExchangeProposalHandler(ExchangeProposalRepository repo) throws FileReaderException {
        this.repo = repo;
        this.proposals = repo.read();
    }

    public List<ExchangeProposal> getExchangeProposals()
    {
        return proposals.getExchangeProposals();
    }

    public void add(ExchangeProposal exchangeProposal)
    {
        proposals.add(exchangeProposal);
    }

    public List<ExchangeProposal> activeProposalBelongOneConsumer(Consumer consumer)
    {
        return proposals.activeProposalBelongOneConsumer(consumer);
    }

    public List<ExchangeProposal> closedProposalBelongOneConsumer(Consumer consumer)
    {
        return proposals.closedProposalBelongOneConsumer(consumer);
    }

    public List<ExchangeProposal> withdrawnProposalBelongOneConsumer(Consumer consumer)
    {
        return proposals.withdrawnProposalBelongOneConsumer(consumer);
    }

    public boolean contains(ExchangeProposal exchangeProposal)
    {
        return proposals.contains(exchangeProposal);
    }

    public boolean verifyClosedProposals(ExchangeProposal exchangeProposal, ExchangeProposal fixed, ExchangeProposals closedSet)
    {
        return proposals.verifyClosedProposals(exchangeProposal, fixed, closedSet);
    }

    public void save() throws FileWriterException {
        repo.write(proposals);
    }

}
