package hierarchyTest;

import it.unibs.ing.elaborato.model.hierarchy.Category;
import it.unibs.ing.elaborato.model.hierarchy.LeafCategory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LeafCategoryTest {

    private LeafCategory leafCategory;

    @Before
    public void setUp() {
        // Crea un'istanza di LeafCategory prima di ogni test
        leafCategory = new LeafCategory("Leaf1", "Domain1", "Description1");
    }

    @Test
    public void testGetLeaves() {
        // Verifica che getLeaves() restituisca una lista contenente solo l'istanza corrente
        List<Category> leaves = leafCategory.getLeaves();
        assertEquals(1, leaves.size());
        assertTrue(leaves.contains(leafCategory));
    }

    @Test
    public void testHasChildren() {
        // Verifica che hasChildren() restituisca false
        assertFalse(leafCategory.hasChildren());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetChildren() {
        // Verifica che getChildren() lanci un'eccezione UnsupportedOperationException
        leafCategory.getChildren();
    }

    @Test
    public void testContainsByName() {
        // Verifica che contains() restituisca true se il nome corrisponde
        assertTrue(leafCategory.contains("Leaf1"));
        // Verifica che contains() restituisca false se il nome non corrisponde
        assertFalse(leafCategory.contains("NonExistentLeaf"));
    }

    @Test
    public void testContainsByLeaf() {
        // Verifica che contains() restituisca true se il leaf è uguale all'istanza corrente
        assertTrue(leafCategory.contains(leafCategory));
        // Crea un'altra istanza di LeafCategory e verifica che non sia uguale all'istanza corrente
        LeafCategory anotherLeaf = new LeafCategory("Leaf2", "Domain2", "Description2");
        assertFalse(leafCategory.contains(anotherLeaf));
    }

    @Test
    public void testEqualsAndHashCode() {
        // Crea una nuova istanza di LeafCategory con lo stesso nome
        LeafCategory sameLeaf = new LeafCategory("Leaf1", "Domain1", "Description1");
        // Verifica che due istanze con lo stesso nome siano uguali
        assertEquals(leafCategory, sameLeaf);
        assertEquals(leafCategory.hashCode(), sameLeaf.hashCode());

        // Crea un'altra istanza con un nome diverso
        LeafCategory differentLeaf = new LeafCategory("Leaf2", "Domain2", "Description2");
        // Verifica che istanze con nomi diversi non siano uguali
        assertNotEquals(leafCategory, differentLeaf);
        assertNotEquals(leafCategory.hashCode(), differentLeaf.hashCode());
    }
}