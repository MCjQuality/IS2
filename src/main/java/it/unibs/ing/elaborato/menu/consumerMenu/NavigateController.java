package it.unibs.ing.elaborato.menu.consumerMenu;

import it.unibs.ing.elaborato.model.hierarchy.HierarchyHandler;
import it.unibs.ing.elaborato.menu.Executable;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Printer;
import it.unibs.ing.elaborato.util.Utility;
import it.unibs.ing.elaborato.model.hierarchy.Category;
import it.unibs.ing.elaborato.model.hierarchy.NotLeafCategory;

import java.util.Optional;
import java.util.Scanner;

public class NavigateController implements Executable {

    private final HierarchyHandler hierarchies;
    private final Scanner scanner;

    public NavigateController(final HierarchyHandler hierarchies, final Scanner scanner) {
        this.hierarchies = hierarchies;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        navigate();
    }

    private void navigate() {
        System.out.println();
        System.out.println(Printer.align(Constants.NAVIGATE_THROUGH_HIERARCHIES_MESSAGE, Constants.MENU_LINE_SIZE));
        System.out.println();

        if (!hierarchies.getHierarchies().isEmpty()) {
            NotLeafCategory root = navigateRoots();
            navigateHierarchy(root);
        } else {
            System.out.println(Constants.NO_HIERARCHY);
        }
    }

    private NotLeafCategory navigateRoots() {
        StringBuilder result = new StringBuilder();

        int i = Constants.NUMBER_1_MESSAGE;
        for (NotLeafCategory node : hierarchies.getHierarchies()) {
            result.append(i++).append(Constants.SEPARATOR).append(node.getName()).append(Constants.NEW_LINE);
        }

        System.out.println(result);

        int index = Integer.parseInt(Utility.checkCondition(
                Constants.INSERT_THE_NUMBER_RELATED_TO_THE_HIERARCHY,
                Constants.INVALID_INPUT_MESSAGE,
                input -> !Utility.isInt(input) || !(Integer.parseInt(input) >= Constants.NUMBER_1_MESSAGE && Integer.parseInt(input) <= hierarchies.getHierarchies().size()),
                scanner
        ));

        String nodeName = hierarchies.getHierarchies().get(index - Constants.NUMBER_1_MESSAGE).getName();

        return hierarchies.getHierarchies()
                .stream()
                .filter(x -> x.getName().equals(nodeName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Node not found"));
    }

    private void navigateHierarchy(final Category father) {
        if (father.hasChildren()) {
            displayHierarchy(father);

            int index = Integer.parseInt(Utility.checkCondition(
                    Constants.INSERT_THE_NUMBER_RELATED_TO_THE_HIERARCHY,
                    Constants.INVALID_INPUT_MESSAGE,
                    input -> !Utility.isInt(input) || !(Integer.parseInt(input) >= Constants.NUMBER_1_MESSAGE && Integer.parseInt(input) <= father.getChildren().size()),
                    scanner
            ));

            String nodeName = father.getChildren().get(index - Constants.NUMBER_1_MESSAGE).getName();

            Optional<Category> node = father.getChildren()
                    .stream()
                    .filter(x -> x.getName().equals(nodeName))
                    .findFirst();

            node.ifPresent(this::navigateHierarchy);

        } else {
            System.out.println();
            System.out.printf(Constants.ASSOCIATED_NODE_TO_DOMAIN, Constants.LIGHT_BLUE_FORMAT + father.getName() + Constants.RESET_FORMAT);
        }
    }

    private void displayHierarchy(final Category father) {
        System.out.println();
        System.out.printf((Constants.ASSOCIATED_NODE_TO_DOMAIN) + Constants.NEW_LINE, father.getName());

        Utility.clearConsole(Constants.READING_TIME);

        System.out.println();
        System.out.println(Printer.align(Constants.NAVIGATE_THROUGH_HIERARCHIES_MESSAGE, Constants.MENU_LINE_SIZE));
        System.out.println();

        StringBuilder result = new StringBuilder();
        int i = Constants.NUMBER_1_MESSAGE;

        System.out.println(((NotLeafCategory) father).getField() + Constants.COLONS);
        for (Category node : father.getChildren()) {
            result.append(i++).append(Constants.SEPARATOR).append(node.getDomain()).append(Constants.NEW_LINE);
        }

        System.out.println(result);
    }
}