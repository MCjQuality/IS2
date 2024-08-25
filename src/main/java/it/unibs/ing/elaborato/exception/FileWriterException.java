package it.unibs.ing.elaborato.exception;

import it.unibs.ing.elaborato.util.Constants;

public class FileWriterException extends Exception
{
    @Override
    public String getMessage()
    {
        return Constants.FILE_WRITER_EXCEPTION;
    }
}
