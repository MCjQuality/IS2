package it.unibs.ing.elaborato.controller.consumerMenu;

import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.controller.Executable;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposal;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposalHandler;
import it.unibs.ing.elaborato.model.user.Consumer;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.view.ExchangeProposalView;

import java.util.List;

public class WithdrawnProposalController implements Executable {

    private final Consumer consumer;
    private final ExchangeProposalHandler exchangeProposals;
    private final ExchangeProposalView view;

    public WithdrawnProposalController(final Consumer consumer, final ExchangeProposalHandler exchangeProposals, final ExchangeProposalView view) {
        this.consumer = consumer;
        this.exchangeProposals = exchangeProposals;
        this.view = view;
    }

    @Override
    public void execute() throws FileWriterException {
        withdrawnProposal();
    }

    private void withdrawnProposal() throws FileWriterException {
        view.showHeader(Constants.WITHDRAW_PROPOSAL_MESSAGE);

        List<ExchangeProposal> activeProposals = exchangeProposals.activeProposalBelongOneConsumer(consumer);

        if (!activeProposals.isEmpty()) {
            view.showActiveProposals(activeProposals, consumer);
            int index = view.getUserSelectedIndex(activeProposals.size());

            if (index != Constants.NUMBER_0_MESSAGE) {
                ExchangeProposal proposalToWithdraw = activeProposals.get(index - Constants.NUMBER_1_MESSAGE);
                withdrawProposal(proposalToWithdraw);
            }
        } else {
            view.showMessage(Constants.NO_PROPOSALS);
        }
    }

    private void withdrawProposal(ExchangeProposal proposal) throws FileWriterException {
        view.showWithdrawnProposal(proposal);
        proposal.changeStatusToWithdrawn();
        exchangeProposals.save();
        view.showMessage(Constants.PROPOSAL_SUCCESSFULLY_WITHDRAWN);
    }
}