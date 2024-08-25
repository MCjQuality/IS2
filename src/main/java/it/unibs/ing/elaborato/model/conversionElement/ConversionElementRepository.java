package it.unibs.ing.elaborato.model.conversionElement;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;

public interface ConversionElementRepository {
    ConversionElements read() throws FileReaderException;
    void write(ConversionElements conversionElements) throws FileWriterException;
}
