package it.unibs.ing.elaborato.menu.configMenu;

import it.unibs.ing.elaborato.model.hierarchy.HierarchyHandler;
import it.unibs.ing.elaborato.menu.Executable;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposal;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposalHandler;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Printer;
import it.unibs.ing.elaborato.util.Utility;

import java.util.Scanner;

public class ShowProposalsController implements Executable {
    private final HierarchyHandler hierarchies;
    private final ExchangeProposalHandler exchangeProposals;
    private final Scanner scanner;

    public ShowProposalsController(HierarchyHandler hierarchies, ExchangeProposalHandler exchangeProposals, Scanner scanner) {
        this.hierarchies = hierarchies;
        this.exchangeProposals = exchangeProposals;
        this.scanner = scanner;
    }

    private void showProposals(ExchangeProposalHandler proposals, Scanner scanner)
    {
        System.out.println();
        System.out.println(Printer.align(Constants.SHOW_PROPOSAL, Constants.MENU_LINE_SIZE));
        System.out.println();

        if(!hierarchies.getLeaves().isEmpty() && !proposals.getExchangeProposals().isEmpty())
        {
            System.out.println(Printer.printLeaves(hierarchies.getLeaves()));

            StringBuilder proposalsList = new StringBuilder();
            int index = Integer.parseInt(Utility.checkCondition(Constants.SPECIFY_NAME_LEAF_CATEGORY, Constants.INVALID_INPUT_MESSAGE, input -> !Utility.isInt(input) || !(Integer.parseInt(input) >= 1 && Integer.parseInt(input) <= hierarchies.getLeaves().size()), scanner));

            String name = hierarchies.getLeaves().get(index - 1).getName();

            Utility.clearConsole(Constants.TRANSACTION_TIME);

            System.out.println();
            System.out.println(Printer.align(Constants.SHOW_PROPOSAL, Constants.MENU_LINE_SIZE));
            System.out.println();
            System.out.println(Constants.PROPOSALS_ASSOCIATES_LEAF_CATEGORY_MESSAGE + name.toUpperCase());

            for(ExchangeProposal proposal : proposals.getExchangeProposals())
                if(proposal.getCouple().getFirstLeaf().getName().equals(name) || proposal.getCouple().getSecondLeaf().getName().equals(name))
                    proposalsList.append(Printer.printExchangeProposalWithSatus(proposal));

            if(!proposalsList.isEmpty())
            {
                System.out.println();
                System.out.println(proposalsList);
                System.out.println();
            }
            else
            {
                System.out.println(Constants.NO_PROPOSALS);
                System.out.println();
            }
        }
        else
        {
            System.out.println(Constants.NO_PROPOSALS);
            System.out.println();
        }

        System.out.print(Constants.PRESS_ANY_BUTTONS_TO_GO_BACK);
        scanner.nextLine();
    }


    @Override
    public void execute()
    {
        showProposals(exchangeProposals, scanner);
    }
}
