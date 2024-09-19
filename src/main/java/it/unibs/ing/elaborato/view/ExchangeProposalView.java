package it.unibs.ing.elaborato.view;

import it.unibs.ing.elaborato.model.hierarchy.HierarchyHandler;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposal;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposalHandler;
import it.unibs.ing.elaborato.model.user.Consumer;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Printer;
import it.unibs.ing.elaborato.util.Utility;

import java.util.List;
import java.util.Scanner;

public class ExchangeProposalView {

    private final Scanner scanner;

    public ExchangeProposalView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showHeader(String message) {
        System.out.println();
        System.out.println(Printer.align(message, Constants.MENU_LINE_SIZE));
        System.out.println();
    }

    public void showProposals(HierarchyHandler hierarchies, ExchangeProposalHandler proposals) {
        showHeader(Constants.SHOW_PROPOSAL);

        if (isValidForProposals(hierarchies, proposals)) {
            displayLeavesAndProposals(hierarchies, proposals);
        } else {
            System.out.println(Constants.NO_PROPOSALS);
        }

        waitForUser();
    }

    public void displayProposals(List<ExchangeProposal> proposals) {
        StringBuilder proposalsList = new StringBuilder();

        for (ExchangeProposal proposal : proposals) {
            proposalsList.append(Printer.printExchangeProposalWithSatus(proposal));
        }

        if (!proposalsList.isEmpty()) {
            System.out.println(proposalsList);
        } else {
            System.out.println(Constants.NO_PROPOSALS);
        }
    }

    public void showProposalDetails(ExchangeProposal exchangeProposal) {
        System.out.println();
        System.out.println(Printer.printExchangeProposal(exchangeProposal));
    }

    public void showActiveProposals(List<ExchangeProposal> activeProposals, Consumer consumer) {
        System.out.printf(Constants.ACTIVE_PROPOSALS_RELATED_TO_A_CONSUMER, consumer.getUsername());
        displayProposals(activeProposals);
    }

    public void showWithdrawnProposal(ExchangeProposal proposal) {
        System.out.println();
        System.out.println(Constants.WITHDRAWN_PROPOSAL);
        showProposalDetails(proposal);
    }

    // Input Methods
    public int getUserChoice(HierarchyHandler hierarchies) {
        return Integer.parseInt(Utility.checkCondition(
                Constants.SPECIFY_NAME_LEAF_CATEGORY,
                Constants.INVALID_INPUT_MESSAGE,
                input -> !Utility.isInt(input) || !isValidIndex(input, hierarchies.getLeaves().size()),
                scanner
        ));
    }

    public String getRequestInput(HierarchyHandler hierarchies) {
        return Utility.check2Condition(
                Constants.SERVICE_REQUESTED,
                Constants.INVALID_INPUT_MESSAGE,
                Constants.LEAF_CATEGORY_DOES_NOT_EXIST,
                String::isBlank,
                input -> !hierarchies.isLeafPresent(input),
                scanner
        );
    }

    public String getOfferInput(HierarchyHandler hierarchies, String request) {
        return Utility.check2Condition(
                Constants.SERVICE_OFFERED,
                Constants.INVALID_INPUT_MESSAGE,
                Constants.LEAF_CATEGORY_DOES_NOT_EXIST,
                input -> input.isBlank() || input.equals(request),
                input -> !hierarchies.isLeafPresent(input),
                scanner
        );
    }

    public int getHoursRequested() {
        return Integer.parseInt(Utility.checkCondition(
                Constants.HOURS_REQUESTED,
                Constants.INVALID_INPUT_MESSAGE,
                input -> input.isBlank() || !Utility.isInt(input) || Integer.parseInt(input) <= 0,
                scanner
        ));
    }

    public String askForSavingProposal() {
        return Utility.checkCondition(
                Constants.SAVING_MESSAGE,
                Constants.INVALID_INPUT_MESSAGE,
                input -> !input.equals(Constants.YES_MESSAGE) && !input.equals(Constants.NO_MESSAGE),
                scanner
        );
    }

    public int getUserSelectedIndex(int maxSize) {
        return Integer.parseInt(Utility.checkCondition(
                Constants.INSERT_PROPOSAL_NUMBER,
                Constants.INVALID_INPUT_MESSAGE,
                input -> !Utility.isInt(input) || !isValidIndex(input, maxSize),
                scanner
        ));
    }

    private boolean isValidIndex(String input, int maxSize) {
        int index = Integer.parseInt(input);
        return index >= 1 && index <= maxSize;
    }

    private boolean isValidForProposals(HierarchyHandler hierarchies, ExchangeProposalHandler proposals) {
        return !hierarchies.getLeaves().isEmpty() && !proposals.getExchangeProposals().isEmpty();
    }

    private void displayLeavesAndProposals(HierarchyHandler hierarchies, ExchangeProposalHandler proposals) {
        System.out.println(Printer.printLeaves(hierarchies.getLeaves()));

        int index = getUserChoice(hierarchies);
        String leafName = hierarchies.getLeaves().get(index - 1).getName();

        Utility.clearConsole(Constants.TRANSACTION_TIME);

        showHeader(Constants.SHOW_PROPOSAL);
        System.out.println(Constants.PROPOSALS_ASSOCIATES_LEAF_CATEGORY_MESSAGE + leafName.toUpperCase());

        List<ExchangeProposal> relevantProposals = filterProposals(proposals, leafName);
        displayProposals(relevantProposals);
    }

    private List<ExchangeProposal> filterProposals(ExchangeProposalHandler proposals, String leafName) {
        return proposals.getExchangeProposals().stream()
                .filter(proposal -> proposal.getCouple().getFirstLeaf().getName().equals(leafName) ||
                        proposal.getCouple().getSecondLeaf().getName().equals(leafName))
                .toList();
    }

    private void waitForUser() {
        System.out.print(Constants.PRESS_ANY_BUTTONS_TO_GO_BACK);
        scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}