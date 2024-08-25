package it.unibs.ing.elaborato.model.conversionElement;

import it.unibs.ing.elaborato.model.hierarchy.Couple;
import it.unibs.ing.elaborato.model.hierarchy.LeafCategory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Astrazione rappresentante la lista degli elementi di conversione.
 */
public class ConversionElements implements Serializable {

	private final List<ConversionElement> list;

	public ConversionElements()
	{
		list = new ArrayList<>();
	}

	public void addFactConv(ConversionElement elem)
	{
		list.add(elem);
	}

	public boolean isCouplePresent(Couple couple)
	{
		return list.stream().anyMatch(s -> s.getCouple().equals(couple));
	}

	public int size()
	{
		return list.size();
	}

	public boolean isFactConvPresent(Double factConv)
	{
		return list.stream().anyMatch(s -> s.getConversionFactor() == factConv);
	}

	public void replace(Couple coupleToFind, double value)
	{
		list.stream().filter(s -> s.getCouple().equals(coupleToFind)).forEach(s -> s.setConversionFactor(value));
	}

	public List<ConversionElement> getRemainingConversionElements()
	{
		return list.stream().filter(s -> s.getConversionFactor() == 0).toList();
	}

	public double[] getConversionFactorRange(Couple elem)
	{
		double [] x = new double [] {2.1, 0.4};
		ArrayList<ConversionElement> tmp = (ArrayList<ConversionElement>)getConversionElementsCompatible(elem);

		if(tmp.isEmpty())
		{
			x[0] = 0.5;
			x[1] = 2.0;
			return x;
		}
		else
		{
			for(ConversionElement conv : tmp)
			{
				if(conv.getConversionFactor() < x[0])
					x[0] = conv.getConversionFactor();
				if(conv.getConversionFactor() > x[1])
					x[1] = conv.getConversionFactor();
			}
			x[0] = Math.ceil((0.5 / x[0]) * 100) / 100.;
			x[1] = Math.floor((2 / x[1]) * 100) / 100.;
			if(x[0] < 0.5)
				x[0] = 0.5;
			if(x[1] > 2.0)
				x[1] = 2.0;

			return x;
		}
	}

	public List<ConversionElement> getConversionElementsCompatible(Couple elem)
	{
		ArrayList<ConversionElement> tmp = new ArrayList<>();
		for(ConversionElement conv : list) {
			if(conv.getConversionFactor() != 0.0 && (elem.getFirstLeaf().equals(conv.getCouple().getSecondLeaf()) 
					|| elem.getSecondLeaf().equals(conv.getCouple().getFirstLeaf())))
			{
					tmp.add(conv);
			}
		}
		return tmp;
	}
	
	public List<ConversionElement> getConversionElements()
	{
		return list;
	}

	public void automaticConvFactCalculate()
	{
		for(ConversionElement elem : list)
			if (elem.getConversionFactor() == 0)
			{
				ConversionElements compatibles = getCompatibleElements(elem);

				if(compatibles.size() > 1)
					for (ConversionElement elem2 : compatibles.getConversionElements())
						for(ConversionElement elem3 : compatibles.getConversionElements())
							if (hasLeafInCommon(elem2, elem3))
							{
								double newConvFact = (elem2.getConversionFactor() * elem3.getConversionFactor());
								Couple couple = elem.getCouple();
								replace(couple, newConvFact);
								replace(new Couple(elem.getCouple().getSecondLeaf(), elem.getCouple().getFirstLeaf()), (1 / newConvFact));
							}
			}
	}

	public ConversionElements getCompatibleElements(ConversionElement conversionElement)
	{
		ConversionElements compatibles = new ConversionElements();
		for(ConversionElement elem : list)
			if(elem.getConversionFactor() != 0)
				if((conversionElement.getCouple().getFirstLeaf().equals(elem.getCouple().getFirstLeaf()) && !conversionElement.getCouple().getFirstLeaf().equals(elem.getCouple().getSecondLeaf()))
						|| 
						(conversionElement.getCouple().getSecondLeaf().equals(elem.getCouple().getSecondLeaf()) && !conversionElement.getCouple().getFirstLeaf().equals(elem.getCouple().getFirstLeaf())))
				{
					compatibles.addFactConv(elem);
				}
		return compatibles;
	}

	public boolean hasLeafInCommon(ConversionElement elem1, ConversionElement elem2)
	{
		return (elem1.getCouple().getSecondLeaf().equals(elem2.getCouple().getFirstLeaf()) || elem1.getCouple().getFirstLeaf().equals(elem2.getCouple().getSecondLeaf()));
	}

	public void initialize(List<LeafCategory> leaves)
	{
		for (int i = 0; i < leaves.size(); i++)
            for (LeafCategory leaf : leaves)
                if (!leaves.get(i).getName().equals(leaf.getName())) {
                    Couple couple = new Couple(leaves.get(i), leaf);
                    if (!isCouplePresent(couple))
                        addFactConv(new ConversionElement(couple, 0.));
                }
	}

}
