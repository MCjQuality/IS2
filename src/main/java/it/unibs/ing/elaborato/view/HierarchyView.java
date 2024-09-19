package it.unibs.ing.elaborato.view;

import it.unibs.ing.elaborato.model.conversionElement.ConversionElement;
import it.unibs.ing.elaborato.model.hierarchy.HierarchyHandler;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Printer;
import it.unibs.ing.elaborato.util.Utility;

import java.util.List;
import java.util.Scanner;

public class HierarchyView {
    public final Scanner scanner;

    public HierarchyView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getInput(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String chooseCategoryType(String nodeName) {
        System.out.println();
        System.out.println(Constants.CHOOSE_LEAF_CATEGORY_MESSAGE + Constants.NEW_LINE + Constants.CHOOSE_NOT_LEAF_CATEGORY_MESSAGE);
        System.out.println(Constants.CHOOSE_NO_SUBCATEGORY);
        return Utility.checkCondition(
                String.format(Constants.CHOOSE_LEAF_OPTIONS, nodeName),
                Constants.INVALID_INPUT_MESSAGE,
                input -> !Utility.isInt(input) || !(Integer.parseInt(input) >= 0 && Integer.parseInt(input) <= 2),
                scanner);
    }

    public int getConversionFactorSelection(List<ConversionElement> remainingCouples) {
        System.out.println(Printer.printRemainingConversionFactor(remainingCouples));
        return Integer.parseInt(Utility.checkCondition(
                Constants.SPECIFY_COUPLE_NUMBER_MESSAGE,
                Constants.INVALID_INPUT_MESSAGE,
                input -> !Utility.isInt(input) || input.isBlank() ||
                        !(Integer.parseInt(input) > 0 && Integer.parseInt(input) <= remainingCouples.size()),
                scanner));
    }

    public double getConversionFactor(double min, double max) {
        return Double.parseDouble(Utility.checkCondition(
                String.format(Constants.SPECIFY_CONVERSION_FACTOR_NUMBER_MESSAGE, min, max),
                Constants.INVALID_INPUT_MESSAGE,
                input -> input.isBlank() || !Utility.isDouble(input) ||
                        Double.parseDouble(input) < min || Double.parseDouble(input) > max,
                scanner));
    }

    public void showConversionFactorMessage() {
        System.out.println(Printer.align(Constants.INSERT_CONV_FACT, Constants.MENU_LINE_SIZE));
        System.out.println();
    }

    public void promptPressAnyKey() {
        System.out.print(Constants.PRESS_ANY_BUTTONS_TO_GO_BACK);
        scanner.nextLine();
    }

    public void showHierarchies(HierarchyHandler hierarchies) {
        printHeader(Constants.VIEW_ALLA_HIERARCHIES_MESSAGE);

        if (!hierarchies.getLeaves().isEmpty()) {
            showAvailableHierarchies(hierarchies);
        } else {
            System.out.println(Constants.NO_HIERARCHIES);
            System.out.println();
        }

        promptPressAnyKey();
    }

    private void showAvailableHierarchies(HierarchyHandler hierarchies) {
        System.out.println(Printer.printHierarchyRoots(hierarchies.getHierarchies()));

        int index = getUserSelectedIndex(hierarchies);

        Utility.clearConsole(Constants.TRANSACTION_TIME);

        printHeader(Constants.TREE_PRINT_OF_HIERARCHY_MESSAGE);
        System.out.println(Printer.printHierarchy(hierarchies.getHierarchies().get(index), 0));

        printHeader(Constants.COMPLETE_PRINT_OF_HIERARCHY_MESSAGE);
        System.out.println(Printer.printExtendHierarchy(hierarchies.getHierarchies().get(index)));
    }

    private int getUserSelectedIndex(HierarchyHandler hierarchies) {
        return Integer.parseInt(Utility.checkCondition(
                Constants.INSERT_NUMBER_SELECT_HIERARCHY_MESSAGE,
                Constants.INVALID_INPUT_MESSAGE,
                input -> !Utility.isInt(input) || !isValidHierarchyIndex(input, hierarchies),
                scanner
        )) - 1;  // Convert to 0-based index
    }

    private boolean isValidHierarchyIndex(String input, HierarchyHandler hierarchies) {
        int parsedIndex = Integer.parseInt(input);
        return parsedIndex >= 1 && parsedIndex <= hierarchies.getHierarchies().size();
    }

    public void printHeader(String message) {
        System.out.println();
        System.out.println(Printer.align(message, Constants.MENU_LINE_SIZE));
        System.out.println();
    }
}