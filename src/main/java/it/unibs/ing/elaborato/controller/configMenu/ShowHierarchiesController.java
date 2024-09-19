package it.unibs.ing.elaborato.controller.configMenu;

import it.unibs.ing.elaborato.model.hierarchy.HierarchyHandler;
import it.unibs.ing.elaborato.controller.Executable;
import it.unibs.ing.elaborato.view.HierarchyView;

public class ShowHierarchiesController implements Executable {

    private final HierarchyHandler hierarchies;
    private final HierarchyView view;

    public ShowHierarchiesController(HierarchyHandler hierarchies, HierarchyView view) {
        this.hierarchies = hierarchies;
        this.view = view;
    }

    @Override
    public void execute() {
        view.showHierarchies(hierarchies);  // Delega la visualizzazione alla View
    }
}