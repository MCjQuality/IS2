package it.unibs.ing.elaborato.controller.configMenu;

import it.unibs.ing.elaborato.model.hierarchy.HierarchyHandler;
import it.unibs.ing.elaborato.controller.Executable;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposalHandler;
import it.unibs.ing.elaborato.view.ExchangeProposalView;

public class ShowProposalsController implements Executable {

    private final HierarchyHandler hierarchies;
    private final ExchangeProposalHandler exchangeProposals;
    private final ExchangeProposalView view;

    public ShowProposalsController(HierarchyHandler hierarchies, ExchangeProposalHandler exchangeProposals, ExchangeProposalView view) {
        this.hierarchies = hierarchies;
        this.exchangeProposals = exchangeProposals;
        this.view = view;
    }

    @Override
    public void execute() {
        view.showProposals(hierarchies, exchangeProposals);  // Delega la visualizzazione alla View
    }
}