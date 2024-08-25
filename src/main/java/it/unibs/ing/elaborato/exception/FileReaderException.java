package it.unibs.ing.elaborato.exception;

import it.unibs.ing.elaborato.util.Constants;

public class FileReaderException extends Exception
{
    @Override
    public String getMessage()
    {
        return Constants.FILE_READER_EXCEPTION;
    }
}
