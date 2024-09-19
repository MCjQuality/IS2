package it.unibs.ing.elaborato.start;

import it.unibs.ing.elaborato.model.closedSet.ClosedSetHandler;
import it.unibs.ing.elaborato.model.closedSet.StandardSerializationBasedClosedSetRepository;
import it.unibs.ing.elaborato.model.conversionElement.ConversionElementHandler;
import it.unibs.ing.elaborato.model.conversionElement.StandardSerializationBasedConversionElementRepository;
import it.unibs.ing.elaborato.model.district.DistrictHandler;
import it.unibs.ing.elaborato.model.district.StandardSerializationBasedDistrictRepository;
import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.model.hierarchy.HierarchyHandler;
import it.unibs.ing.elaborato.model.hierarchy.StandardSerializationBasedHierarchyRepository;
import it.unibs.ing.elaborato.controller.MenuManager;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposalHandler;
import it.unibs.ing.elaborato.model.proposal.StandardSerializationBasedExchangeProposalRepository;
import it.unibs.ing.elaborato.model.user.StandardSerializationBasedUserRepository;
import it.unibs.ing.elaborato.model.user.User;
import it.unibs.ing.elaborato.model.user.UserHandler;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Utility;

import java.util.Scanner;

public class AppInitializer {

    private final UserHandler userHandler;
    private final DistrictHandler districtHandler;
    private final HierarchyHandler hierarchyHandler;
    private final ConversionElementHandler conversionElementHandler;
    private final ExchangeProposalHandler exchangeProposalHandler;
    private final ClosedSetHandler closedSetHandler;
    private final Scanner scanner;

    public AppInitializer() throws FileWriterException, FileReaderException {
        Utility.clearConsole(Constants.TRANSACTION_TIME);
        this.scanner = new Scanner(System.in);
        scanner.useDelimiter(System.lineSeparator());

        this.userHandler = new UserHandler(new StandardSerializationBasedUserRepository(Constants.USERS_FILEPATH));
        this.districtHandler = new DistrictHandler(new StandardSerializationBasedDistrictRepository(Constants.DISTRICTS_FILEPATH));
        this.hierarchyHandler = new HierarchyHandler(new StandardSerializationBasedHierarchyRepository(Constants.HIERARCHIES_FILEPATH));
        this.conversionElementHandler = new ConversionElementHandler(new StandardSerializationBasedConversionElementRepository(Constants.CONVERSION_ELEMENTS_FILEPATH));
        this.exchangeProposalHandler = new ExchangeProposalHandler(new StandardSerializationBasedExchangeProposalRepository(Constants.PROPOSALS_FILEPATH));
        this.closedSetHandler = new ClosedSetHandler(new StandardSerializationBasedClosedSetRepository(Constants.CLOSED_SETS_FILEPATH));
    }

    public UserAuthenticator createUserAuthenticator() {
        return new UserAuthenticator(userHandler, districtHandler, scanner);
    }

    public MenuManager createMenuController(User user) {
        return new MenuManager(user, districtHandler, hierarchyHandler, conversionElementHandler, exchangeProposalHandler, closedSetHandler, scanner);
    }

    public Scanner getScanner() {
        return scanner;
    }

}
