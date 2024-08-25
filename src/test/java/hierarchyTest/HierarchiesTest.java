package hierarchyTest;

import it.unibs.ing.elaborato.model.hierarchy.Hierarchies;
import it.unibs.ing.elaborato.model.hierarchy.LeafCategory;
import it.unibs.ing.elaborato.model.hierarchy.NotLeafCategory;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HierarchiesTest {

    private Hierarchies hierarchies;
    private NotLeafCategory root;
    private LeafCategory leaf1;
    private LeafCategory leaf2;

    @Before
    public void setUp() {
        // Inizializza un'istanza di Hierarchies prima di ogni test
        hierarchies = new Hierarchies();

        // Crea delle categorie foglia e radice mock
        leaf1 = mock(LeafCategory.class);
        leaf2 = mock(LeafCategory.class);
        root = mock(NotLeafCategory.class);

        // Configura i mock
        when(leaf1.getName()).thenReturn("Leaf1");
        when(leaf2.getName()).thenReturn("Leaf2");
        when(root.getLeaves()).thenReturn(Arrays.asList(leaf1, leaf2));
        when(root.getName()).thenReturn("Root1");
    }

    @Test
    public void testAddHierarchy() {
        // Aggiungi una gerarchia e verifica che la lista non sia vuota
        hierarchies.addHierarchy(root);
        List<NotLeafCategory> hierarchiesList = hierarchies.getHierarchies();
        assertEquals(1, hierarchiesList.size());
        assertEquals(root, hierarchiesList.getFirst());
    }

    @Test
    public void testGetLeaves() {
        // Aggiungi una gerarchia e verifica che le foglie siano corrette
        hierarchies.addHierarchy(root);
        List<LeafCategory> leaves = hierarchies.getLeaves();
        assertEquals(2, leaves.size());
        assertTrue(leaves.contains(leaf1));
        assertTrue(leaves.contains(leaf2));
    }

    @Test
    public void testIsLeafPresent() {
        // Aggiungi una gerarchia e verifica la presenza di foglie
        hierarchies.addHierarchy(root);
        assertTrue(hierarchies.isLeafPresent("Leaf1"));
        assertFalse(hierarchies.isLeafPresent("NonExistentLeaf"));
    }

    @Test
    public void testFindLeaf() {
        // Aggiungi una gerarchia e verifica la ricerca di una foglia
        hierarchies.addHierarchy(root);
        assertEquals(leaf1, hierarchies.findLeaf("Leaf1"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindLeafThrowsException() {
        // Aggiungi una gerarchia e verifica la gestione di un'eccezione
        hierarchies.addHierarchy(root);
        hierarchies.findLeaf("NonExistentLeaf");
    }

    @Test
    public void testContainsRoot() {
        // Aggiungi una gerarchia e verifica la presenza di una radice
        hierarchies.addHierarchy(root);
        assertTrue(hierarchies.containsRoot("Root1"));
        assertFalse(hierarchies.containsRoot("NonExistentRoot"));
    }
}