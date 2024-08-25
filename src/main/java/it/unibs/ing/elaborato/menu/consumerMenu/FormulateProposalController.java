package it.unibs.ing.elaborato.menu.consumerMenu;

import it.unibs.ing.elaborato.model.closedSet.ClosedSetHandler;
import it.unibs.ing.elaborato.model.conversionElement.ConversionElementHandler;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.model.hierarchy.Couple;
import it.unibs.ing.elaborato.model.hierarchy.HierarchyHandler;
import it.unibs.ing.elaborato.menu.Executable;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposal;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposalHandler;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposals;
import it.unibs.ing.elaborato.model.user.Consumer;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Printer;
import it.unibs.ing.elaborato.util.Utility;

import java.util.Scanner;

public class FormulateProposalController implements Executable {

    private final HierarchyHandler hierarchies;
    private final Consumer consumer;
    private final ConversionElementHandler conversionElements;
    private final ExchangeProposalHandler exchangeProposals;
    private final ClosedSetHandler closedSets;
    private final Scanner scanner;

    public FormulateProposalController(final HierarchyHandler hierarchies, final Consumer consumer,
                                       final ConversionElementHandler conversionElements,
                                       final ExchangeProposalHandler exchangeProposals,
                                       final ClosedSetHandler closedSets, final Scanner scanner) {
        this.hierarchies = hierarchies;
        this.consumer = consumer;
        this.conversionElements = conversionElements;
        this.exchangeProposals = exchangeProposals;
        this.closedSets = closedSets;
        this.scanner = scanner;
    }

    @Override
    public void execute() throws FileWriterException {
        formulateProposal();
    }

    private void formulateProposal() throws FileWriterException {
        System.out.println();
        System.out.println(Printer.align(Constants.ADD_EXCHANGE_PROPOSAL_MENU_MESSAGE, Constants.MENU_LINE_SIZE));
        System.out.println();

        if (!hierarchies.getLeaves().isEmpty()) {
            ExchangeProposal exchangeProposal = createExchangeProposal();

            if (exchangeProposals.contains(exchangeProposal)) {
                System.out.println(Constants.PROPOSAL_ALREADY_INSERT);
            } else {
                confirmAndSaveProposal(exchangeProposal);
            }
        } else {
            System.out.print(Constants.NO_LEAVES);
        }
    }

    private ExchangeProposal createExchangeProposal() {
        String request = Utility.check2Condition(
                Constants.SERVICE_REQUESTED,
                Constants.INVALID_INPUT_MESSAGE,
                Constants.LEAF_CATEGORY_DOES_NOT_EXIST,
                String::isBlank,
                input -> !hierarchies.isLeafPresent(input),
                scanner
        );

        String offer = Utility.check2Condition(
                Constants.SERVICE_OFFERED,
                Constants.INVALID_INPUT_MESSAGE,
                Constants.LEAF_CATEGORY_DOES_NOT_EXIST,
                input -> input.isBlank() || input.equals(request),
                input -> !hierarchies.isLeafPresent(input),
                scanner
        );

        Couple couple = new Couple(hierarchies.findLeaf(request), hierarchies.findLeaf(offer));

        int hours = Integer.parseInt(Utility.checkCondition(
                Constants.HOURS_REQUESTED,
                Constants.INVALID_INPUT_MESSAGE,
                input -> input.isBlank() || !Utility.isInt(input) || Integer.parseInt(input) <= 0,
                scanner
        ));

        return new ExchangeProposal(couple, hours, consumer, conversionElements.getConversionElements());
    }

    private void confirmAndSaveProposal(final ExchangeProposal exchangeProposal) throws FileWriterException {
        System.out.println();
        System.out.println(Printer.printExchangeProposal(exchangeProposal));

        String yesOrNo = Utility.checkCondition(
                Constants.SAVING_MESSAGE,
                Constants.INVALID_INPUT_MESSAGE,
                input -> !input.equals(Constants.YES_MESSAGE) && !input.equals(Constants.NO_MESSAGE),
                scanner
        );

        if (yesOrNo.equals(Constants.YES_MESSAGE)) {
            saveProposal(exchangeProposal);
        } else {
            System.out.println(Constants.PROPOSAL_NOT_SAVED_MESSAGE);
        }
    }

    private void saveProposal(final ExchangeProposal exchangeProposal) throws FileWriterException {
        exchangeProposals.add(exchangeProposal);
        exchangeProposals.save();
        System.out.println(Constants.PROPOSAL_SAVED_MESSAGE);

        ExchangeProposals closedSet = new ExchangeProposals();

        if (exchangeProposals.verifyClosedProposals(exchangeProposal, exchangeProposal, closedSet)) {
            System.out.println(Constants.EXCHANGE_PROPOSAL_CLOSED_MESSAGE);
            closedSets.add(closedSet);
            closedSets.write();
        }
    }
}