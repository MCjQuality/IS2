package it.unibs.ing.elaborato.model.hierarchy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe che organizza l'insieme delle gerarchie.
 */
public class Hierarchies implements Serializable
{
	private final List<NotLeafCategory> hierarchies;

	public Hierarchies()
	{
		this.hierarchies = new ArrayList<>();
	}

	public void addHierarchy(NotLeafCategory root)
	{
		hierarchies.add(root);
	}

	public List<NotLeafCategory> getHierarchies()
	{
		return hierarchies;
	}

	public List<LeafCategory> getLeaves()
	{
		List<LeafCategory> leavesInAllHierarchies = new ArrayList<>();

		for(NotLeafCategory root : hierarchies)
			for(Category elem : root.getLeaves())
				leavesInAllHierarchies.add((LeafCategory) elem);

		return leavesInAllHierarchies;
	}

    public boolean isLeafPresent(String name)
    {
        return getLeaves().stream().anyMatch(leaf -> leaf.getName().equals(name));
    }

    public LeafCategory findLeaf(String name)
    {
        return getLeaves().stream().filter(leaf -> leaf.getName().contentEquals(name)).toList().getFirst();
    }

    public boolean containsRoot(String name)
    {
        return getHierarchies().stream().anyMatch(notLeaf -> notLeaf.getName().equals(name));
    }
}
