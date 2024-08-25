package it.unibs.ing.elaborato.model.hierarchy;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Una categoria foglia che non presenta sotto-categorie.
 */
public class LeafCategory extends Category implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	public LeafCategory(String name, String domain, String description)
	{
		super(name, domain, description);
	}

	@Override
    public List<Category> getLeaves()
	{
		List<Category> leaf = new ArrayList<>();
		leaf.add(this);
		
		return leaf;
	}

	@Override
	public boolean hasChildren()
	{
		return false;
	}

	/**
	 * Fornisce la lista di figli, che pero' e' vuota, essendo una categoria foglia.
	 * @return una lista di categorie vuota.
	 */
	@Override
	public List<Category> getChildren()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(String leafName)
	{
		return this.getName().equals(leafName);
	}

	@Override
	public boolean contains(LeafCategory leaf)
	{
		return this == (leaf);
	}

	@Override
    public boolean equals(Object o)
	{
        if (this == o) return true;
        if (!(o instanceof LeafCategory leaf)) return false;
        return this.getName().equals(leaf.getName());
    }

	@Override
    public int hashCode()
    {
        return Objects.hash(getName());
    }

}
