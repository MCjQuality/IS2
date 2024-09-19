package it.unibs.ing.elaborato.controller.configMenu;

import it.unibs.ing.elaborato.model.conversionElement.ConversionElementHandler;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.controller.Executable;
import it.unibs.ing.elaborato.model.hierarchy.*;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Utility;
import it.unibs.ing.elaborato.view.HierarchyView;

public class InsertNewHierarchyController implements Executable {

    private final HierarchyHandler hierarchies;
    private final ConversionElementHandler conversionElements;
    private final HierarchyView view;

    public InsertNewHierarchyController(HierarchyHandler hierarchies, ConversionElementHandler conversionElements, HierarchyView view) {
        this.hierarchies = hierarchies;
        this.conversionElements = conversionElements;
        this.view = view;
    }

    private void insertNewHierarchy() throws FileWriterException {
        view.printHeader(Constants.INSERT_NEW_HIERARCHY_MESSAGE);

        NotLeafCategory root = createRoot();

        hierarchies.addHierarchy(root);
        populateTree(root);
        hierarchies.save();

        view.showMessage(Constants.HIERARCHY_VALID_MESSAGE);

        Utility.clearConsole(Constants.TRANSACTION_TIME);

        conversionElements.initialize(hierarchies.getLeaves());

        if (conversionElements.getConversionElements().size() > 1) {
            setConversionFactors();
            view.promptPressAnyKey();
        }

        conversionElements.save();
    }

    private NotLeafCategory createRoot() {
        String name = Utility.check2Condition(view.getInput(Constants.INSERT_ROOT_NAME_MESSAGE), Constants.INVALID_INPUT_MESSAGE,
                Constants.ROOT_ALREADY_EXIST_MESSAGE, String::isBlank, hierarchies::containsRoot, view.scanner);
        String field = view.getInput(Constants.SPECIFY_FIELD_NAME);

        return new NotLeafCategory(name, null, null, field);
    }

    private void populateTree(Category node) {
        NotLeafCategory notLeafNode = (NotLeafCategory) node;

        while (true) {
            Utility.clearConsole(Constants.TRANSACTION_TIME);
            view.printHeader(Constants.INSERT_NEW_HIERARCHY_MESSAGE);

            String categoryType = view.chooseCategoryType(node.getName());
            int index = Integer.parseInt(categoryType);

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
                    populateTree(childNode);
                }
                default -> {
                    if (notLeafNode.getLeaves().isEmpty()) {
                        view.showMessage(Constants.NO_LEAVES);
                    }
                }
            }
        }
    }

    private LeafCategory createLeaf(Category root) {
        String name = Utility.check2Condition(view.getInput(Constants.INSERT_CATEGORY_NAME_MASSAGE), Constants.INVALID_INPUT_MESSAGE,
                Constants.CATEGORY_ALREADY_INSERT, String::isBlank,
                input -> hierarchies.isLeafPresent(input) || root.contains(input), view.scanner);
        String nameDomain = view.getInput(Constants.SPECIFY_DOMAIN_NAME_MESSAGE);
        String yesOrNo = view.getInput(Constants.DESCRIPTION_ASSOCIATED_WITH_DOMAIN_MESSAGE);
        String description = null;

        if (yesOrNo.equalsIgnoreCase(Constants.YES_MESSAGE)) {
            description = view.getInput(Constants.INSERT_CATEGORY_DESCRIPTION_MESSAGE);
        }

        return new LeafCategory(name, nameDomain, description);
    }

    private NotLeafCategory createNotLeaf(Category root) {
        String name = Utility.check2Condition(view.getInput(Constants.INSERT_CATEGORY_NAME_MASSAGE), Constants.INVALID_INPUT_MESSAGE,
                Constants.CATEGORY_ALREADY_INSERT, String::isBlank, root::contains, view.scanner);
        String nameDomain = view.getInput(Constants.SPECIFY_DOMAIN_NAME_MESSAGE);
        String yesOrNo = view.getInput(Constants.DESCRIPTION_ASSOCIATED_WITH_DOMAIN_MESSAGE);
        String description = null;

        if (yesOrNo.equalsIgnoreCase(Constants.YES_MESSAGE)) {
            description = view.getInput(Constants.INSERT_CATEGORY_DESCRIPTION_MESSAGE);
        }
        String nameField = view.getInput(Constants.SPECIFY_FIELD_NAME);

        return new NotLeafCategory(name, nameDomain, description, nameField);
    }

    private void setConversionFactors() {
        do {
            view.showConversionFactorMessage();

            int coupleSelected = view.getConversionFactorSelection(conversionElements.getRemainingConversionElements());
            Couple couple = conversionElements.getRemainingConversionElements().get(coupleSelected - 1).getCouple();
            double[] range = conversionElements.getConversionFactorRange(couple);
            double value = view.getConversionFactor(range[0], range[1]);

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