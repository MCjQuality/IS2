package hierarchyTest;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.model.hierarchy.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HierarchyHandlerTest {

    @Mock
    private HierarchyRepository repo;

    @Mock
    private Hierarchies hierarchies;

    private HierarchyHandler handler;

    @Before
    public void setUp() throws FileReaderException {
        MockitoAnnotations.openMocks(this);
        when(repo.read()).thenReturn(hierarchies);
        handler = new HierarchyHandler(repo);
    }

    @Test
    public void testAddHierarchy() {
        NotLeafCategory notLeafCategory = new NotLeafCategory("Root", "Domain", "Description", "Field");
        handler.addHierarchy(notLeafCategory);
        verify(hierarchies).addHierarchy(notLeafCategory);
    }

    @Test
    public void testGetHierarchies() {
        List<NotLeafCategory> expectedHierarchies = new ArrayList<>();
        when(hierarchies.getHierarchies()).thenReturn(expectedHierarchies);

        List<NotLeafCategory> actualHierarchies = handler.getHierarchies();
        assertEquals(expectedHierarchies, actualHierarchies);
    }

    @Test
    public void testGetLeaves() {
        List<LeafCategory> expectedLeaves = new ArrayList<>();
        when(hierarchies.getLeaves()).thenReturn(expectedLeaves);

        List<LeafCategory> actualLeaves = handler.getLeaves();
        assertEquals(expectedLeaves, actualLeaves);
    }

    @Test
    public void testIsLeafPresent() {
        String leafName = "LeafName";
        when(hierarchies.isLeafPresent(leafName)).thenReturn(true);

        boolean result = handler.isLeafPresent(leafName);
        assertTrue(result);
    }

    @Test
    public void testContainsRoot() {
        String rootName = "RootName";
        when(hierarchies.containsRoot(rootName)).thenReturn(true);

        boolean result = handler.containsRoot(rootName);
        assertTrue(result);
    }

    @Test
    public void testFindLeaf() {
        String leafName = "LeafName";
        LeafCategory expectedLeaf = new LeafCategory(leafName, "Domain", "Description");
        when(hierarchies.findLeaf(leafName)).thenReturn(expectedLeaf);

        LeafCategory actualLeaf = handler.findLeaf(leafName);
        assertEquals(expectedLeaf, actualLeaf);
    }

    @Test
    public void testSave() throws FileWriterException {
        handler.save();
        verify(repo).write(hierarchies);
    }
}