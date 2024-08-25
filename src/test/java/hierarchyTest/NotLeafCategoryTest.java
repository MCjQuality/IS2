package hierarchyTest;

import it.unibs.ing.elaborato.model.hierarchy.Category;
import it.unibs.ing.elaborato.model.hierarchy.LeafCategory;
import it.unibs.ing.elaborato.model.hierarchy.NotLeafCategory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NotLeafCategoryTest {

    private NotLeafCategory notLeafCategory;
    private LeafCategory leafCategory1;
    private LeafCategory leafCategory2;
    private NotLeafCategory childCategory;

    @Before
    public void setUp() {
        // Crea istanze di NotLeafCategory e LeafCategory prima di ogni test
        notLeafCategory = new NotLeafCategory("Category1", "Domain1", "Description1", "Field1");
        leafCategory1 = new LeafCategory("Leaf1", "Domain1", "Description1");
        leafCategory2 = new LeafCategory("Leaf2", "Domain1", "Description2");
        childCategory = new NotLeafCategory("ChildCategory", "Domain2", "Description2", "Field2");

        // Aggiungi foglie e categorie figlie
        notLeafCategory.addChildren(leafCategory1);
        notLeafCategory.addChildren(childCategory);
        childCategory.addChildren(leafCategory2);
    }

    @Test
    public void testGetField() {
        // Verifica che getField() restituisca il valore corretto
        assertEquals("Field1", notLeafCategory.getField());
    }

    @Test
    public void testGetLeaves() {
        // Verifica che getLeaves() restituisca tutte le foglie nella gerarchia
        List<Category> leaves = notLeafCategory.getLeaves();
        assertEquals(2, leaves.size());
        assertTrue(leaves.contains(leafCategory1));
        assertTrue(leaves.contains(leafCategory2));
    }

    @Test
    public void testHasChildren() {
        // Verifica che hasChildren() restituisca true
        assertTrue(notLeafCategory.hasChildren());
    }

    @Test
    public void testAddChildren() {
        // Verifica che l'aggiunta di una categoria figlia funzioni correttamente
        NotLeafCategory newChild = new NotLeafCategory("NewChild", "Domain3", "Description3", "Field3");
        notLeafCategory.addChildren(newChild);
        List<Category> children = notLeafCategory.getChildren();
        assertTrue(children.contains(newChild));
    }

    @Test
    public void testContainsByName() {
        // Verifica che contains(String name) restituisca true per nomi esistenti e false per nomi non esistenti
        assertTrue(notLeafCategory.contains("Leaf1"));
        assertTrue(notLeafCategory.contains("Leaf2"));
        assertFalse(notLeafCategory.contains("NonExistentLeaf"));
    }

    @Test
    public void testContainsByLeaf() {
        // Verifica che contains(LeafCategory leaf) restituisca true per foglie esistenti e false per foglie non esistenti
        assertTrue(notLeafCategory.contains(leafCategory1));
        assertTrue(notLeafCategory.contains(leafCategory2));
        LeafCategory nonExistentLeaf = new LeafCategory("NonExistentLeaf", "Domain4", "Description4");
        assertFalse(notLeafCategory.contains(nonExistentLeaf));
    }

    @Test
    public void testEqualsAndHashCode() {
        // Crea una nuova istanza di NotLeafCategory con lo stesso nome, dominio e campo
        NotLeafCategory sameCategory = new NotLeafCategory("Category1", "Domain1", "Description1", "Field1");
        // Verifica che due istanze con gli stessi attributi siano uguali
        assertEquals(notLeafCategory, sameCategory);
        assertEquals(notLeafCategory.hashCode(), sameCategory.hashCode());

        // Crea un'altra istanza con attributi diversi
        NotLeafCategory differentCategory = new NotLeafCategory("DifferentCategory", "Domain1", "Description1", "Field1");
        // Verifica che istanze con attributi diversi non siano uguali
        assertNotEquals(notLeafCategory, differentCategory);
        assertNotEquals(notLeafCategory.hashCode(), differentCategory.hashCode());
    }
}