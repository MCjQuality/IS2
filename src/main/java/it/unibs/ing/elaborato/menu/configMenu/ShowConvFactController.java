package it.unibs.ing.elaborato.menu.configMenu;

import it.unibs.ing.elaborato.model.conversionElement.ConversionElement;
import it.unibs.ing.elaborato.model.conversionElement.ConversionElementHandler;
import it.unibs.ing.elaborato.model.hierarchy.HierarchyHandler;
import it.unibs.ing.elaborato.menu.Executable;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Printer;
import it.unibs.ing.elaborato.util.Utility;

import java.util.Scanner;

public class ShowConvFactController implements Executable {
    private final HierarchyHandler hierarchies;
    private final ConversionElementHandler convElements;
    private final Scanner scanner;

    public ShowConvFactController(HierarchyHandler hierarchies, ConversionElementHandler convElements, Scanner scanner) {
        this.hierarchies = hierarchies;
        this.convElements = convElements;
        this.scanner = scanner;
    }

    private void showConvFact() {
        System.out.println();
        System.out.println(Printer.align(Constants.VIEW_CONVERSION_FACTOR_MESSAGE, Constants.MENU_LINE_SIZE));
        System.out.println();

        if (convElements.getConversionElements().size() <= 1) {
            System.out.println(Constants.NO_CONV_FACT);
            System.out.println();
            waitForUser();
            return;
        }

        displayLeaves();
        int index = getUserChoice();

        String name = hierarchies.getLeaves().get(index - 1).getName();
        Utility.clearConsole(Constants.TRANSACTION_TIME);

        System.out.println();
        System.out.println(Printer.align(Constants.VIEW_CONVERSION_FACTOR_MESSAGE, Constants.MENU_LINE_SIZE));
        System.out.println();
        System.out.println(Constants.CONVERSION_FACTOR_ASSOCIATES_LEAF_CATEGORY_MESSAGE + name.toUpperCase());

        displayConversionFactors(name);

        waitForUser();
    }

    private void displayLeaves() {
        System.out.println(Printer.printLeaves(hierarchies.getLeaves()));
    }

    private int getUserChoice() {
        return Integer.parseInt(Utility.checkCondition(
                Constants.SPECIFY_NAME_LEAF_CATEGORY,
                Constants.INVALID_INPUT_MESSAGE,
                input -> !Utility.isInt(input) ||
                        !(Integer.parseInt(input) >= 1 &&
                                Integer.parseInt(input) <= hierarchies.getLeaves().size()),
                scanner
        ));
    }

    private void displayConversionFactors(String leafName) {
        StringBuilder listaFattori = new StringBuilder();
        for (ConversionElement elem : convElements.getConversionElements()) {
            if (elem.getCouple().getFirstLeaf().getName().equals(leafName)) {
                listaFattori.append(Constants.SEPARATOR)
                        .append(Printer.printConversionElement(elem));
            }
        }
        System.out.println(listaFattori);
        System.out.println();
    }

    private void waitForUser() {
        System.out.print(Constants.PRESS_ANY_BUTTONS_TO_GO_BACK);
        scanner.nextLine();
    }

    @Override
    public void execute()
    {
        showConvFact();
    }
}