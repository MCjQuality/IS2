package it.unibs.ing.elaborato.model.hierarchy;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Questa classe astrae l'idea di nodo di una gerachia.
 * Infatti qualsiasi sia la sua natura (foglia o meno) un nodo contiene a priori un nome, un valore del dominio e una sua possibile descrizione.
 * Inoltre, definisci metodi astratti che saranno implementati e concretizzati dalle sottoclassi, atti a fornire informazioni sull'effettiva natura delle sottoclassi e proprieta' delle stesse.
 */
public abstract class Category implements Serializable
{
	@Serial
	private static final long serialVersionUID = 1L;
	private String name;
	private final String domain;
	private final String description;

	public Category(String name, String domain, String description)
	{
		this.name = name;
		this.domain = domain;
		this.description = description;
	}

	public String getName()
	{
		return name;
	}

	public String getDomain()
	{
		return domain;
	}

	public String getDescription()
	{
		return description;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public abstract List<Category> getLeaves();
	public abstract boolean hasChildren();
	public abstract List<Category> getChildren();
	public abstract boolean contains(String leafName);
	public abstract boolean contains(LeafCategory leaf);
}