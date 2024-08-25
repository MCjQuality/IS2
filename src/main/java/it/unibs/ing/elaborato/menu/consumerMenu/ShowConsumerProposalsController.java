package it.unibs.ing.elaborato.menu.consumerMenu;

import it.unibs.ing.elaborato.menu.Executable;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposal;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposalHandler;
import it.unibs.ing.elaborato.model.user.Consumer;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Printer;
import it.unibs.ing.elaborato.util.Utility;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class ShowConsumerProposalsController implements Executable {

    private final Consumer consumer;
    private final ExchangeProposalHandler exchangeProposals;
    private final Scanner scanner;

    public ShowConsumerProposalsController(final Consumer consumer, final ExchangeProposalHandler exchangeProposals, final Scanner scanner) {
        this.consumer = consumer;
        this.exchangeProposals = exchangeProposals;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        showProposals();
    }

    private void showProposals() {
        System.out.println();
        System.out.println(Printer.align(Constants.SHOW_PROPOSAL, Constants.MENU_LINE_SIZE));
        System.out.println();
        System.out.println(Constants.SHOW_OPEN_PROPOSAL);
        System.out.println(Constants.SHOW_CLOSED_PROPOSAL);
        System.out.println(Constants.SHOW_WITHDRAWN_PROPOSAL);
        System.out.println();

        int index = Integer.parseInt(Utility.checkCondition(
                Constants.SELECT_FROM_THE_OPTIONS_MESSAGE,
                Constants.INVALID_INPUT_MESSAGE,
                input -> !Utility.isInt(input) || !(Integer.parseInt(input) >= Constants.NUMBER_1_MESSAGE && Integer.parseInt(input) <= Constants.NUMBER_3_MESSAGE),
                scanner
        ));

        switch (index) {
            case Constants.NUMBER_1_MESSAGE -> showFilteredProposals(exchangeProposals::activeProposalBelongOneConsumer);
            case Constants.NUMBER_2_MESSAGE -> showFilteredProposals(exchangeProposals::closedProposalBelongOneConsumer);
            case Constants.NUMBER_3_MESSAGE -> showFilteredProposals(exchangeProposals::withdrawnProposalBelongOneConsumer);
            default -> System.out.println(Constants.INSERT_VALID_OPTION_MENU_MESSAGE);
        }
    }

    private void showFilteredProposals(Function<Consumer, List<ExchangeProposal>> filterFunction) {
        Utility.clearConsole(Constants.TRANSACTION_TIME);

        System.out.println();
        System.out.println(Printer.align(Constants.SHOW_PROPOSAL, Constants.MENU_LINE_SIZE));
        System.out.println();

        List<ExchangeProposal> proposals = filterFunction.apply(consumer);
        if (!proposals.isEmpty()) {
            System.out.println(Printer.printExchangeProposals(proposals));
        } else {
            System.out.println(Constants.NO_PROPOSALS);
            System.out.println();
        }

        System.out.print(Constants.PRESS_ANY_BUTTONS_TO_GO_BACK);
        scanner.nextLine();
    }
}