package it.unibs.ing.elaborato.model.hierarchy;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Una categoria non foglia aggiunge un attributo campo ed una lista di sotto-categorie (non vuota).
 */
public class NotLeafCategory extends Category implements Serializable
{
	@Serial
	private static final long serialVersionUID = 1L;
	private final String field;
	private final List<Category> children;
	
	public NotLeafCategory(String name, String domain, String descr, String field) 
	{
		super(name, domain, descr);	
		this.field = field;
		this.children = new ArrayList<>();
	}

	public String getField() 
	{
		return field;
	}

	@Override
	public List<Category> getLeaves()
	{
		List<Category> leaves = new ArrayList<>();
		for(Category elem : children)
			leaves.addAll(elem.getLeaves());
	
		return leaves;
	}

	public List<Category> getChildren() 
	{
		return children;
	}

	@Override
	public boolean hasChildren() 
	{
		return true;
	}

	public void addChildren(Category category)
	{
		children.add(category);
	}

	@Override
	public boolean contains(String name)
	{
		if(this.getName().equals(name))
			return true;
		for(Category elem : children)
			if(elem.contains(name))
				return true;
		return false;
	}

	@Override
	public boolean contains(LeafCategory leaf)
	{
		for(Category elem : children)
			if(elem.contains(leaf))
				return true;
		return false;
	}
	
	@Override
    public boolean equals(Object o) 
	{
        if (this == o) return true;
        if (!(o instanceof NotLeafCategory notLeaf)) return false;
        return this.getName().equals(notLeaf.getName()) && this.getDomain().equals(notLeaf.getDomain()) && this.getField().equals(notLeaf.getField());
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(getName(), getDomain(), getDescription(), getField());
    }

}
