package it.unibs.ing.elaborato.menu.configMenu;

import it.unibs.ing.elaborato.model.conversionElement.ConversionElementHandler;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.menu.Executable;
import it.unibs.ing.elaborato.model.hierarchy.*;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Printer;
import it.unibs.ing.elaborato.util.Utility;

import java.util.Scanner;

public class InsertNewHierarchyController implements Executable {

    private final HierarchyHandler hierarchies;
    private final ConversionElementHandler conversionElements;
    private final Scanner scanner;

    public InsertNewHierarchyController(HierarchyHandler hierarchies, ConversionElementHandler conversionElements, Scanner scanner) {
        this.hierarchies = hierarchies;
        this.conversionElements = conversionElements;
        this.scanner = scanner;
    }

    private void insertNewHierarchy() throws FileWriterException {
        System.out.println();
        System.out.println(Printer.align(Constants.INSERT_NEW_HIERARCHY_MESSAGE, Constants.MENU_LINE_SIZE));
        System.out.println();

        NotLeafCategory root = createRoot();

        hierarchies.addHierarchy(root);
        populateTree(root);
        hierarchies.save();

        System.out.println(Constants.HIERARCHY_VALID_MESSAGE);

        Utility.clearConsole(Constants.TRANSACTION_TIME);

        conversionElements.initialize(hierarchies.getLeaves());

        if (conversionElements.getConversionElements().size() > 1) {
            setConversionFactors();
            System.out.println(Printer.printConversionElements(conversionElements.getConversionElements()));
            System.out.print(Constants.PRESS_ANY_BUTTONS_TO_GO_BACK);
            scanner.nextLine();
        }

        conversionElements.save();
    }

    private NotLeafCategory createRoot() {
        String name = Utility.check2Condition(Constants.INSERT_ROOT_NAME_MESSAGE, Constants.INVALID_INPUT_MESSAGE,
                Constants.ROOT_ALREADY_EXIST_MESSAGE, String::isBlank, hierarchies::containsRoot ,scanner);
        String field = Utility.checkCondition(Constants.SPECIFY_FIELD_NAME, Constants.INVALID_INPUT_MESSAGE,
                String::isBlank, scanner);

        return new NotLeafCategory(name, null, null, field);
    }

    private void populateTree(Category node) {
//        try {
//
//        } catch (UnsupportedOperationException e) {
//            return;
//        }

//        if(!node.hasChildren()) {
//            return;
//        }

        NotLeafCategory notLeafNode = (NotLeafCategory) node;

        while (true) {
            Utility.clearConsole(Constants.TRANSACTION_TIME);

            System.out.println();
            System.out.println(Printer.align(Constants.INSERT_NEW_HIERARCHY_MESSAGE, Constants.MENU_LINE_SIZE));
            System.out.println();

            System.out.println(Utility.appendHorizontalLine(Constants.MENU_LINE_SIZE));
            System.out.println(Constants.CHOOSE_LEAF_CATEGORY_MESSAGE + Constants.NEW_LINE +
                    Constants.CHOOSE_NOT_LEAF_CATEGORY_MESSAGE);
            System.out.println(Constants.CHOOSE_NO_SUBCATEGORY);
            System.out.print(Utility.appendHorizontalLine(Constants.MENU_LINE_SIZE));

            int index = Integer.parseInt(Utility.checkCondition(
                    String.format(Constants.CHOOSE_LEAF_OPTIONS, node.getName()), Constants.INVALID_INPUT_MESSAGE,
                    input -> !Utility.isInt(input) || !(Integer.parseInt(input) >= 0 && Integer.parseInt(input) <= 2),
                    scanner));

            if (index == 0 && !notLeafNode.getLeaves().isEmpty()) {
                break;
            }

            switch (index) {
                case 1 -> {
                    LeafCategory leaf = createLeaf(node);
                    notLeafNode.addChildren(leaf);
                }
                case 2 -> {
                    NotLeafCategory childNode = createNotLeaf(node);
                    notLeafNode.addChildren(childNode);
//                    try
//                    {
//                        node.getChildren();
                        populateTree(childNode);
//                    } catch (UnsupportedOperationException e) {
//                        return;
//                    }

                }
                default -> {
                    if (notLeafNode.getLeaves().isEmpty()) {
                        System.out.println(Constants.NO_LEAVES);
                    }
                }
            }
        }
    }

    private LeafCategory createLeaf(Category root) {
        String name = Utility.check2Condition(Constants.INSERT_CATEGORY_NAME_MASSAGE, Constants.INVALID_INPUT_MESSAGE,
                Constants.CATEGORY_ALREADY_INSERT, String::isBlank,
                input -> hierarchies.isLeafPresent(input) || root.contains(input), scanner);
        String nameDomain = Utility.checkCondition(Constants.SPECIFY_DOMAIN_NAME_MESSAGE, Constants.INVALID_INPUT_MESSAGE,
                String::isBlank, scanner);
        String yesOrNo = Utility.checkCondition(Constants.DESCRIPTION_ASSOCIATED_WITH_DOMAIN_MESSAGE,
                Constants.INVALID_INPUT_MESSAGE,
                input -> !input.equalsIgnoreCase(Constants.YES_MESSAGE) &&
                        !input.equalsIgnoreCase(Constants.NO_MESSAGE), scanner);
        String description = null;

        if (yesOrNo.equalsIgnoreCase(Constants.YES_MESSAGE)) {
            description = Utility.checkCondition(Constants.INSERT_CATEGORY_DESCRIPTION_MESSAGE,
                    Constants.INVALID_INPUT_MESSAGE, String::isBlank, scanner);
        }

        return new LeafCategory(name, nameDomain, description);
    }

    private NotLeafCategory createNotLeaf(Category root) {
        String name = Utility.check2Condition(Constants.INSERT_CATEGORY_NAME_MASSAGE, Constants.INVALID_INPUT_MESSAGE,
                Constants.CATEGORY_ALREADY_INSERT, String::isBlank, root::contains, scanner);
        String nameDomain = Utility.checkCondition(Constants.SPECIFY_DOMAIN_NAME_MESSAGE, Constants.INVALID_INPUT_MESSAGE,
                String::isBlank, scanner);
        String yesOrNo = Utility.checkCondition(Constants.DESCRIPTION_ASSOCIATED_WITH_DOMAIN_MESSAGE,
                Constants.INVALID_INPUT_MESSAGE,
                input -> !input.equalsIgnoreCase(Constants.YES_MESSAGE) &&
                        !input.equalsIgnoreCase(Constants.NO_MESSAGE), scanner);
        String description = null;

        if (yesOrNo.equalsIgnoreCase(Constants.YES_MESSAGE)) {
            description = Utility.checkCondition(Constants.INSERT_CATEGORY_DESCRIPTION_MESSAGE,
                    Constants.INVALID_INPUT_MESSAGE, String::isBlank, scanner);
        }
        String nameField = Utility.checkCondition(Constants.SPECIFY_FIELD_NAME, Constants.INVALID_INPUT_MESSAGE,
                String::isBlank, scanner);

        return new NotLeafCategory(name, nameDomain, description, nameField);
    }

    private void setConversionFactors() {
        do {
            System.out.println();
            System.out.println(Printer.align(Constants.INSERT_CONV_FACT, Constants.MENU_LINE_SIZE));
            System.out.println();

            System.out.println(Printer.printRemainingConversionFactor(conversionElements.getRemainingConversionElements()));
            int coupleSelected = Integer.parseInt(Utility.checkCondition(
                    Constants.SPECIFY_COUPLE_NUMBER_MESSAGE, Constants.INVALID_INPUT_MESSAGE,
                    input -> !Utility.isInt(input) || input.isBlank() ||
                            !(Integer.parseInt(input) > 0 &&
                                    Integer.parseInt(input) <= conversionElements.getRemainingConversionElements().size()),
                    scanner));

            Couple couple = conversionElements.getRemainingConversionElements().get(coupleSelected - 1).getCouple();
            double[] range = conversionElements.getConversionFactorRange(couple);
            double value = Double.parseDouble(Utility.checkCondition(
                    String.format(Constants.SPECIFY_CONVERSION_FACTOR_NUMBER_MESSAGE, range[0], range[1]),
                    Constants.INVALID_INPUT_MESSAGE,
                    input -> input.isBlank() || !Utility.isDouble(input) ||
                            Double.parseDouble(input) < range[0] || Double.parseDouble(input) > range[1], scanner));

            conversionElements.replace(couple, value);
            conversionElements.replace(new Couple(couple.getSecondLeaf(), couple.getFirstLeaf()), 1 / value);
            conversionElements.automaticConvFactCalculate();

            Utility.clearConsole(Constants.TRANSACTION_TIME);

        } while (conversionElements.isFactConvPresent(0.0));
    }

    @Override
    public void execute() throws FileWriterException {
        insertNewHierarchy();
    }
}