package it.unibs.ing.elaborato.controller.consumerMenu;

import it.unibs.ing.elaborato.controller.Executable;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposal;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposalHandler;
import it.unibs.ing.elaborato.model.user.Consumer;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.view.ExchangeProposalView;

import java.util.List;
import java.util.function.Function;

public class ShowConsumerProposalsController implements Executable {

    private final Consumer consumer;
    private final ExchangeProposalHandler exchangeProposals;
    private final ExchangeProposalView view;

    public ShowConsumerProposalsController(final Consumer consumer, final ExchangeProposalHandler exchangeProposals, final ExchangeProposalView view) {
        this.consumer = consumer;
        this.exchangeProposals = exchangeProposals;
        this.view = view;
    }

    @Override
    public void execute() {
        showProposals();
    }

    private void showProposals() {
        view.showProposalsHeader();

        int index = view.getUserChoice();

        switch (index) {
            case Constants.NUMBER_1_MESSAGE -> showFilteredProposals(exchangeProposals::activeProposalBelongOneConsumer);
            case Constants.NUMBER_2_MESSAGE -> showFilteredProposals(exchangeProposals::closedProposalBelongOneConsumer);
            case Constants.NUMBER_3_MESSAGE -> showFilteredProposals(exchangeProposals::withdrawnProposalBelongOneConsumer);
            default -> System.out.println(Constants.INSERT_VALID_OPTION_MENU_MESSAGE);
        }
    }

    private void showFilteredProposals(Function<Consumer, List<ExchangeProposal>> filterFunction) {
        List<ExchangeProposal> proposals = filterFunction.apply(consumer);
        view.displayFilteredProposals(proposals);
    }
}