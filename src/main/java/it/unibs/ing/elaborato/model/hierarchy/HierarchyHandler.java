package it.unibs.ing.elaborato.model.hierarchy;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;

import java.util.List;

public class HierarchyHandler
{
    private final HierarchyRepository repo;
    private final Hierarchies hierarchies;

    public HierarchyHandler(HierarchyRepository repo) throws FileReaderException {
        this.repo = repo;
        this.hierarchies = repo.read();
    }

    public void addHierarchy(NotLeafCategory root)
    {
        hierarchies.addHierarchy(root);
    }

    public List<NotLeafCategory> getHierarchies()
    {
        return hierarchies.getHierarchies();
    }

    public List<LeafCategory> getLeaves()
    {
        return hierarchies.getLeaves();
    }

    public boolean isLeafPresent(String name)
    {
        return hierarchies.isLeafPresent(name);
    }

    public boolean containsRoot(String name)
    {
        return hierarchies.containsRoot(name);
    }

    public LeafCategory findLeaf(String name)
    {
        return hierarchies.findLeaf(name);
    }

    public void save() throws FileWriterException
    {
        repo.write(hierarchies);
    }

}
