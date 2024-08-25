package hierarchyTest;

import it.unibs.ing.elaborato.model.hierarchy.Couple;
import it.unibs.ing.elaborato.model.hierarchy.LeafCategory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CoupleTest {

    private LeafCategory leafCategory1;
    private LeafCategory leafCategory2;
    private LeafCategory leafCategory3;
    private Couple couple1;
    private Couple couple2;
    private Couple couple3;

    @Before
    public void setUp() {
        // Crea istanze di LeafCategory
        leafCategory1 = new LeafCategory("Leaf1", "Domain1", "Description1");
        leafCategory2 = new LeafCategory("Leaf2", "Domain2", "Description2");
        leafCategory3 = new LeafCategory("Leaf3", "Domain3", "Description3");

        // Crea istanze di Couple
        couple1 = new Couple(leafCategory1, leafCategory2);
        couple2 = new Couple(leafCategory1, leafCategory2); // Stesso contenuto di couple1
        couple3 = new Couple(leafCategory1, leafCategory3); // Diverso da couple1 e couple2
    }

    @Test
    public void testGetFirstLeaf() {
        // Verifica che il metodo getFirstLeaf restituisca il valore corretto
        assertEquals(leafCategory1, couple1.getFirstLeaf());
    }

    @Test
    public void testGetSecondLeaf() {
        // Verifica che il metodo getSecondLeaf restituisca il valore corretto
        assertEquals(leafCategory2, couple1.getSecondLeaf());
    }

    @Test
    public void testEquals() {
        // Verifica che due oggetti Couple con gli stessi LeafCategory siano considerati uguali
        assertEquals(couple1, couple2);

        // Verifica che due oggetti Couple con LeafCategory diversi non siano considerati uguali
        assertNotEquals(couple1, couple3);

        // Verifica che un oggetto Couple non sia uguale ad un oggetto di un altro tipo
        assertNotEquals(couple1, new Object());
    }

    @Test
    public void testHashCode() {
        // Verifica che due oggetti Couple con gli stessi LeafCategory abbiano lo stesso hashCode
        assertEquals(couple1.hashCode(), couple2.hashCode());

        // Verifica che due oggetti Couple con LeafCategory diversi abbiano hashCode diversi
        assertNotEquals(couple1.hashCode(), couple3.hashCode());
    }
}