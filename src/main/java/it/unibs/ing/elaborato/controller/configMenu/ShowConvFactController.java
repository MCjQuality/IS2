package it.unibs.ing.elaborato.controller.configMenu;

import it.unibs.ing.elaborato.model.conversionElement.ConversionElementHandler;
import it.unibs.ing.elaborato.model.hierarchy.HierarchyHandler;
import it.unibs.ing.elaborato.controller.Executable;
import it.unibs.ing.elaborato.view.ConversionElementView;

public class ShowConvFactController implements Executable {
    private final HierarchyHandler hierarchies;
    private final ConversionElementHandler convElements;
    private final ConversionElementView view;

    public ShowConvFactController(HierarchyHandler hierarchies, ConversionElementHandler convElements, ConversionElementView view) {
        this.hierarchies = hierarchies;
        this.convElements = convElements;
        this.view = view;
    }

    @Override
    public void execute() {
        view.showConvFact(hierarchies, convElements);  // Delega la visualizzazione alla View
    }
}