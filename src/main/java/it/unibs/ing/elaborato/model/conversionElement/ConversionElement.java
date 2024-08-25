package it.unibs.ing.elaborato.model.conversionElement;

import it.unibs.ing.elaborato.model.hierarchy.Couple;

import java.io.Serial;
import java.io.Serializable;

/**
 * Questa classe rappresenta il modello di un elemento di conversione, inteso come coppia di categorie foglia con associato il fattore di conversione.
 */
public class ConversionElement implements Serializable {

	public void setCouple(Couple couple)
	{
		this.couple = couple;
	}

	@Serial
	private static final long serialVersionUID = 1L;
	private Couple couple;
	private double conversionFactor;
	
	public ConversionElement(Couple couple, double conversionFactor)
	{
		this.couple = couple;
		this.conversionFactor = conversionFactor;
	}

	public Couple getCouple()
	{
		return couple;
	}

	public double getConversionFactor()
	{
		return conversionFactor;
	}

	public void setConversionFactor(double value)
	{
		this.conversionFactor = value;
	}
	
}
