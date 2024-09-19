package it.unibs.ing.elaborato.controller.consumerMenu;

import it.unibs.ing.elaborato.model.closedSet.ClosedSetHandler;
import it.unibs.ing.elaborato.model.conversionElement.ConversionElementHandler;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.model.hierarchy.Couple;
import it.unibs.ing.elaborato.model.hierarchy.HierarchyHandler;
import it.unibs.ing.elaborato.controller.Executable;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposal;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposalHandler;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposals;
import it.unibs.ing.elaborato.model.user.Consumer;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.view.ExchangeProposalView;

public class FormulateProposalController implements Executable {

    private final HierarchyHandler hierarchies;
    private final Consumer consumer;
    private final ConversionElementHandler conversionElements;
    private final ExchangeProposalHandler exchangeProposals;
    private final ClosedSetHandler closedSets;
    private final ExchangeProposalView view;

    public FormulateProposalController(final HierarchyHandler hierarchies, final Consumer consumer,
                                       final ConversionElementHandler conversionElements,
                                       final ExchangeProposalHandler exchangeProposals,
                                       final ClosedSetHandler closedSets, final ExchangeProposalView view) {
        this.hierarchies = hierarchies;
        this.consumer = consumer;
        this.conversionElements = conversionElements;
        this.exchangeProposals = exchangeProposals;
        this.closedSets = closedSets;
        this.view = view;
    }

    @Override
    public void execute() throws FileWriterException {
        view.showHeader(Constants.ADD_EXCHANGE_PROPOSAL_MENU_MESSAGE);

        if (!hierarchies.getLeaves().isEmpty()) {
            ExchangeProposal exchangeProposal = createExchangeProposal();

            if (exchangeProposals.contains(exchangeProposal)) {
                view.showMessage(Constants.PROPOSAL_ALREADY_INSERT);
            } else {
                confirmAndSaveProposal(exchangeProposal);
            }
        } else {
            view.showMessage(Constants.NO_LEAVES);
        }
    }

    private ExchangeProposal createExchangeProposal() {
        String request = view.getRequestInput(hierarchies);
        String offer = view.getOfferInput(hierarchies, request);
        Couple couple = new Couple(hierarchies.findLeaf(request), hierarchies.findLeaf(offer));
        int hours = view.getHoursRequested();

        return new ExchangeProposal(couple, hours, consumer, conversionElements.getConversionElements());
    }

    private void confirmAndSaveProposal(final ExchangeProposal exchangeProposal) throws FileWriterException {
        view.showProposalDetails(exchangeProposal);
        String yesOrNo = view.askForSavingProposal();

        if (yesOrNo.equals(Constants.YES_MESSAGE)) {
            saveProposal(exchangeProposal);
        } else {
            view.showMessage(Constants.PROPOSAL_NOT_SAVED_MESSAGE);
        }
    }

    private void saveProposal(final ExchangeProposal exchangeProposal) throws FileWriterException {
        exchangeProposals.add(exchangeProposal);
        exchangeProposals.save();
        view.showMessage(Constants.PROPOSAL_SAVED_MESSAGE);

        ExchangeProposals closedSet = new ExchangeProposals();

        if (exchangeProposals.verifyClosedProposals(exchangeProposal, exchangeProposal, closedSet)) {
            view.showMessage(Constants.EXCHANGE_PROPOSAL_CLOSED_MESSAGE);
            closedSets.add(closedSet);
            closedSets.write();
        }
    }
}