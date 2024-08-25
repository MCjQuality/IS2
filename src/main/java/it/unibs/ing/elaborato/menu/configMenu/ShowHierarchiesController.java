package it.unibs.ing.elaborato.menu.configMenu;

import it.unibs.ing.elaborato.model.hierarchy.HierarchyHandler;
import it.unibs.ing.elaborato.menu.Executable;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Printer;
import it.unibs.ing.elaborato.util.Utility;

import java.util.Scanner;

public class ShowHierarchiesController implements Executable {

    private final HierarchyHandler hierarchies;
    private final Scanner scanner;

    public ShowHierarchiesController(HierarchyHandler hierarchies, Scanner scanner) {
        this.hierarchies = hierarchies;
        this.scanner = scanner;
    }

    @Override
    public void execute()
    {
        showHierarchies();
    }

    private void showHierarchies() {
        printHeader(Constants.VIEW_ALLA_HIERARCHIES_MESSAGE);

        if (!hierarchies.getLeaves().isEmpty()) {
            showAvailableHierarchies();
        } else {
            System.out.println(Constants.NO_HIERARCHIES);
            System.out.println();
        }

        promptToGoBack();
    }

    private void showAvailableHierarchies() {
        System.out.println(Printer.printHierarchyRoots(hierarchies.getHierarchies()));

        int index = getUserSelectedIndex();

        Utility.clearConsole(Constants.TRANSACTION_TIME);

        printHeader(Constants.TREE_PRINT_OF_HIERARCHY_MESSAGE);
        System.out.println(Printer.printHierarchy(hierarchies.getHierarchies().get(index), 0));

        printHeader(Constants.COMPLETE_PRINT_OF_HIERARCHY_MESSAGE);
        System.out.println(Printer.printExtendHierarchy(hierarchies.getHierarchies().get(index)));
    }

    private int getUserSelectedIndex() {
        return Integer.parseInt(Utility.checkCondition(
                Constants.INSERT_NUMBER_SELECT_HIERARCHY_MESSAGE,
                Constants.INVALID_INPUT_MESSAGE,
                input -> !Utility.isInt(input) || !isValidHierarchyIndex(input),
                scanner
        )) - 1;  // Convert to 0-based index
    }

    private boolean isValidHierarchyIndex(String input) {
        int parsedIndex = Integer.parseInt(input);
        return parsedIndex >= 1 && parsedIndex <= hierarchies.getHierarchies().size();
    }

    private void printHeader(String message) {
        System.out.println();
        System.out.println(Printer.align(message, Constants.MENU_LINE_SIZE));
        System.out.println();
    }

    private void promptToGoBack() {
        System.out.print(Constants.PRESS_ANY_BUTTONS_TO_GO_BACK);
        scanner.nextLine();
    }
}