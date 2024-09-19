package it.unibs.ing.elaborato.controller;

import it.unibs.ing.elaborato.model.closedSet.ClosedSetHandler;
import it.unibs.ing.elaborato.model.conversionElement.ConversionElementHandler;
import it.unibs.ing.elaborato.model.district.DistrictHandler;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.exception.LogoutException;
import it.unibs.ing.elaborato.model.hierarchy.HierarchyHandler;
import it.unibs.ing.elaborato.controller.configMenu.MenuConfigContext;
import it.unibs.ing.elaborato.controller.consumerMenu.MenuConsumerContext;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposalHandler;
import it.unibs.ing.elaborato.model.user.Consumer;
import it.unibs.ing.elaborato.model.user.User;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Utility;

import java.util.Scanner;

public class MenuManager {

    private final User user;
    private final DistrictHandler districtHandler;
    private final HierarchyHandler hierarchyHandler;
    private final ConversionElementHandler conversionElementHandler;
    private final ExchangeProposalHandler exchangeProposalHandler;
    private final ClosedSetHandler closedSetHandler;
    private final Scanner scanner;

    public MenuManager(User user, DistrictHandler districtHandler, HierarchyHandler hierarchyHandler,
                       ConversionElementHandler conversionElementHandler, ExchangeProposalHandler exchangeProposalHandler,
                       ClosedSetHandler closedSetHandler, Scanner scanner) {
        this.user = user;
        this.districtHandler = districtHandler;
        this.hierarchyHandler = hierarchyHandler;
        this.conversionElementHandler = conversionElementHandler;
        this.exchangeProposalHandler = exchangeProposalHandler;
        this.closedSetHandler = closedSetHandler;
        this.scanner = scanner;
    }

    public void start() throws FileWriterException, LogoutException {
        Utility.clearConsole(Constants.READING_TIME);
        if (user.isConsumer()) {
            handleConsumerMenu();
        } else {
            handleConfiguratorMenu();
        }
    }

    private void handleConsumerMenu() throws FileWriterException, LogoutException {
        MenuConsumerContext menuConsumerContext = new MenuConsumerContext(hierarchyHandler, (Consumer) user, conversionElementHandler, exchangeProposalHandler, closedSetHandler, scanner);
        menuConsumerContext.chooseOption(scanner);
    }

    private void handleConfiguratorMenu() throws FileWriterException, LogoutException {
        MenuConfigContext menuConfigContext = new MenuConfigContext(districtHandler, hierarchyHandler, conversionElementHandler, exchangeProposalHandler, closedSetHandler, scanner);
        menuConfigContext.chooseOption(scanner);
    }
}
