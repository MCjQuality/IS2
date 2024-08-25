package it.unibs.ing.elaborato.model.district;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;

public interface DistrictRepository {
    Districts read() throws FileReaderException;
    void write(Districts districts) throws FileWriterException;
}
