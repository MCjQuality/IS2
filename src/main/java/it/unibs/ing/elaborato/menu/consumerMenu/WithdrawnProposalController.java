package it.unibs.ing.elaborato.menu.consumerMenu;

import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.menu.Executable;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposal;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposalHandler;
import it.unibs.ing.elaborato.model.user.Consumer;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Printer;
import it.unibs.ing.elaborato.util.Utility;

import java.util.List;
import java.util.Scanner;

public class WithdrawnProposalController implements Executable {

    private final Consumer consumer;
    private final ExchangeProposalHandler exchangeProposals;
    private final Scanner scanner;

    public WithdrawnProposalController(final Consumer consumer, final ExchangeProposalHandler exchangeProposals, final Scanner scanner) {
        this.consumer = consumer;
        this.exchangeProposals = exchangeProposals;
        this.scanner = scanner;
    }

    @Override
    public void execute() throws FileWriterException {
        withdrawnProposal();
    }

    private void withdrawnProposal() throws FileWriterException {
        System.out.println();
        System.out.println(Printer.align(Constants.WITHDRAW_PROPOSAL_MESSAGE, Constants.MENU_LINE_SIZE));
        System.out.println();

        List<ExchangeProposal> activeProposals = exchangeProposals.activeProposalBelongOneConsumer(consumer);

        if (!activeProposals.isEmpty()) {
            displayActiveProposals(activeProposals);
            int index = getUserSelectedIndex(activeProposals.size());

            if (index != Constants.NUMBER_0_MESSAGE) {
                ExchangeProposal proposalToWithdraw = activeProposals.get(index - Constants.NUMBER_1_MESSAGE);
                withdrawProposal(proposalToWithdraw);
            }
        } else {
            System.out.println(Constants.NO_PROPOSALS);
        }
    }

    private void displayActiveProposals(List<ExchangeProposal> activeProposals) {
        System.out.printf(Constants.ACTIVE_PROPOSALS_RELATED_TO_A_CONSUMER, consumer.getUsername());
        System.out.println(Printer.printNumberedExchangeProposals(activeProposals));
    }

    private int getUserSelectedIndex(int maxSize) {
        return Integer.parseInt(Utility.checkCondition(
                Constants.INSERT_PROPOSAL_NUMBER,
                Constants.INVALID_INPUT_MESSAGE,
                input -> !Utility.isInt(input) || !(Integer.parseInt(input) >= Constants.NUMBER_0_MESSAGE && Integer.parseInt(input) <= maxSize),
                scanner
        ));
    }

    private void withdrawProposal(ExchangeProposal proposal) throws FileWriterException {
        System.out.println();
        System.out.println(Constants.WITHDRAWN_PROPOSAL);
        System.out.println(Printer.printExchangeProposal(proposal));
        proposal.changeStatusToWithdrawn();
        exchangeProposals.save();
        System.out.println(Constants.PROPOSAL_SUCCESSFULLY_WITHDRAWN);
    }
}