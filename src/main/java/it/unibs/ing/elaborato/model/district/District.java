package it.unibs.ing.elaborato.model.district;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Astrazione di un comprensorio geografico, il quale è composto da un nome e una lista dei comuni annessi.
 */
public class District implements Serializable{

	@Serial
	private static final long serialVersionUID = 1L;
	private String name;
	private List<String> municipalities;

	public District(String nome)
	{
		this.name = nome;
		this.municipalities = new ArrayList<>();
	}

	public String getName()
	{
		return name;
	}

	public List<String> getMunicipalities()
	{ 
		return municipalities; 
	}

	public void setMunicipalities(List<String> municipalities) {
		this.municipalities = municipalities;
	}

	public void setName(String nome)
	{
		this.name = nome;
	}

	public void add(String municipality)
	{ 
		municipalities.add(municipality);
	}

}
