package it.unibs.ing.elaborato.controller.consumerMenu;

import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.exception.LogoutException;
import it.unibs.ing.elaborato.model.conversionElement.ConversionElementHandler;
import it.unibs.ing.elaborato.model.hierarchy.HierarchyHandler;
import it.unibs.ing.elaborato.controller.Executable;
import it.unibs.ing.elaborato.controller.LogoutController;
import it.unibs.ing.elaborato.controller.TerminationController;
import it.unibs.ing.elaborato.model.closedSet.ClosedSetHandler;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposalHandler;
import it.unibs.ing.elaborato.model.user.Consumer;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Utility;
import it.unibs.ing.elaborato.view.ExchangeProposalView;
import it.unibs.ing.elaborato.view.HierarchyView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuConsumerContext {

	private final Map<Integer, Executable> actions;

	public MenuConsumerContext(HierarchyHandler hierarchies, Consumer consumer, ConversionElementHandler conversionElements,
	                           ExchangeProposalHandler exchangeProposals, ClosedSetHandler closedSets, Scanner scanner)
	{
		HierarchyView hierarchyView = new HierarchyView(scanner);
		ExchangeProposalView exchangeProposalView = new ExchangeProposalView(scanner);

		actions = new HashMap<>();
		actions.put(Constants.NUMBER_1_MESSAGE, new NavigateController(hierarchies, scanner));
		actions.put(Constants.NUMBER_2_MESSAGE, new FormulateProposalController(hierarchies, consumer, conversionElements,
				exchangeProposals, closedSets, exchangeProposalView));
		actions.put(Constants.NUMBER_3_MESSAGE, new WithdrawnProposalController(consumer, exchangeProposals, exchangeProposalView));
		actions.put(Constants.NUMBER_4_MESSAGE, new ShowConsumerProposalsController(consumer, exchangeProposals, exchangeProposalView));
		actions.put(Constants.NUMBER_5_MESSAGE, new LogoutController());
		actions.put(Constants.NUMBER_0_MESSAGE, new TerminationController());
	}

	public void chooseOption(Scanner scanner) throws LogoutException, FileWriterException
	{
		int index;

		do
		{
			System.out.print(Constants.TITLE_MENU);
			System.out.println(Constants.CONSUMER_OPTION);

			index = Integer.parseInt(Utility.checkCondition(Constants.SELECT_FROM_THE_OPTIONS_MESSAGE,
					Constants.INVALID_INPUT_MESSAGE, input -> !Utility.isInt(input)
							|| !(Integer.parseInt(input) >= Constants.NUMBER_0_MESSAGE
							&& Integer.parseInt(input) <= Constants.NUMBER_5_MESSAGE), scanner));
			Executable action = actions.get(index);

			Utility.clearConsole(Constants.TRANSACTION_TIME);
			action.execute();
			Utility.clearConsole(Constants.READING_TIME);
		} while(index != Constants.NUMBER_0_MESSAGE && index != Constants.NUMBER_5_MESSAGE);
	}
}
