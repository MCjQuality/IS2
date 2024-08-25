package it.unibs.ing.elaborato.model.hierarchy;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;

public interface HierarchyRepository
{
    Hierarchies read() throws FileReaderException;
    void write(Hierarchies hierarchies) throws FileWriterException;
}
