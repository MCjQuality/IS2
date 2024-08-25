package it.unibs.ing.elaborato.model.conversionElement;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.model.hierarchy.Couple;
import it.unibs.ing.elaborato.model.hierarchy.LeafCategory;

import java.util.List;

public class ConversionElementHandler {

    private final ConversionElements conversionElements;
    private final ConversionElementRepository repo;

    public ConversionElementHandler(ConversionElementRepository repo) throws FileReaderException
    {
        this.repo = repo;
        this.conversionElements = repo.read();
    }

    public int size()
    {
        return conversionElements.size();
    }

    public boolean isFactConvPresent(Double factConv)
    {
        return conversionElements.isFactConvPresent(factConv);
    }

    public void replace(Couple coupleToFind, double value)
    {
        conversionElements.replace(coupleToFind, value);
    }

    public List<ConversionElement> getRemainingConversionElements()
    {
        return conversionElements.getRemainingConversionElements();
    }

    public double[] getConversionFactorRange(Couple elem)
    {
        return conversionElements.getConversionFactorRange(elem);
    }

    public List<ConversionElement> getConversionElements()
    {
        return conversionElements.getConversionElements();
    }

    public void automaticConvFactCalculate()
    {
        conversionElements.automaticConvFactCalculate();
    }

    public void initialize(List<LeafCategory> leaves)
    {
        conversionElements.initialize(leaves);
    }

    public void save() throws FileWriterException
    {
        repo.write(conversionElements);
    }

}
