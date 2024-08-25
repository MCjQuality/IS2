package proposalTest;

import it.unibs.ing.elaborato.model.conversionElement.ConversionElement;
import it.unibs.ing.elaborato.model.conversionElement.ConversionElements;
import it.unibs.ing.elaborato.model.hierarchy.Couple;
import it.unibs.ing.elaborato.model.hierarchy.LeafCategory;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposal;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposals;
import it.unibs.ing.elaborato.model.user.Consumer;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class ExchangeProposalsTest {

    private ExchangeProposals proposals;
    private LeafCategory leaf1 = new LeafCategory("leaf1", "domain1", "");
    private LeafCategory leaf2 = new LeafCategory("leaf2", "domain2", "");
    private Couple couple1 = new Couple(leaf1, leaf2);
    private Couple couple2 = new Couple(leaf2, leaf1);
    private ConversionElement conversionElement1 = new ConversionElement(couple1, 1.2);
    private ConversionElement conversionElement2 = new ConversionElement(couple2, 0.83);
    private Consumer consumer1 = new Consumer("Consumer1", "psw1", "District1", "Email1");
    private Consumer consumer2 = new Consumer("Consumer2", "psw2", "District1", "Email2");
    private ConversionElements conversionElements = new ConversionElements();
    private ExchangeProposal proposal1;
    private ExchangeProposal proposal2;

    @Before
    public void setUp()
    {
        conversionElements.addFactConv(conversionElement1);
        conversionElements.addFactConv(conversionElement2);
        proposal1 = new ExchangeProposal(couple1, 10, consumer1, conversionElements.getConversionElements());
        proposal2 = new ExchangeProposal(couple2, 12, consumer2, conversionElements.getConversionElements());
        proposals = new ExchangeProposals();
    }

    @Test
    public void testAdd()
    {
        proposals.add(proposal1);
        List<ExchangeProposal> proposals = this.proposals.getExchangeProposals();
        assertTrue(proposals.contains(proposal1));
    }

    @Test
    public void testActiveProposalBelongOneConsumer() {
        ExchangeProposals proposals = new ExchangeProposals();
        proposals.add(proposal1);

        List<ExchangeProposal> result = proposals.activeProposalBelongOneConsumer(consumer1);
        assertTrue(result.contains(proposal1));
    }

    @Test
    public void testClosedProposalBelongOneConsumer() {
        ExchangeProposals closedSet = new ExchangeProposals();
        conversionElements.addFactConv(conversionElement1);
        conversionElements.addFactConv(conversionElement2);

        proposals.add(proposal1);
        proposals.add(proposal2);
        proposals.verifyClosedProposals(proposal1, proposal2, closedSet);

        List<ExchangeProposal> result = proposals.closedProposalBelongOneConsumer(consumer1);
        assertTrue(result.contains(proposal1));
    }

    @Test
    public void testWithdrawnProposalBelongOneConsumer() {
        ExchangeProposals proposals = new ExchangeProposals();
        proposals.add(proposal1);
        proposal1.changeStatusToWithdrawn();

        List<ExchangeProposal> result = proposals.withdrawnProposalBelongOneConsumer(consumer1);
        assertTrue(result.contains(proposal1));
    }

    @Test
    public void testContains() {
        proposals.add(proposal1);
        assertTrue(proposals.contains(proposal1));
    }

    @Test
    public void testVerifyClosedProposals() {
        ExchangeProposals closedSet = new ExchangeProposals();
        conversionElements.addFactConv(conversionElement1);
        conversionElements.addFactConv(conversionElement2);

        ExchangeProposal proposal1 = new ExchangeProposal(couple1, 10, consumer1, conversionElements.getConversionElements());
        ExchangeProposal proposal2 = new ExchangeProposal(couple2, 12, consumer2, conversionElements.getConversionElements());
        proposals.add(proposal1);
        proposals.add(proposal2);

        boolean result = proposals.verifyClosedProposals(proposal1, proposal2, closedSet);
        assertTrue(result);
        assertTrue(closedSet.getExchangeProposals().contains(proposal1));
        assertTrue(closedSet.getExchangeProposals().contains(proposal2));
    }

    @Test
    public void testClear() {
        proposals.add(proposal1);
        proposals.clear();
        assertTrue(proposals.getExchangeProposals().isEmpty());
    }

    @Test
    public void testChangeStatusToClosed() {
        proposals.add(proposal1);
        proposals.add(proposal2);

        proposals.changeStatusToClosed();
    }
}