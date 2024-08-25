package it.unibs.ing.elaborato.model.user;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void add(User user);
    Optional<User> findUser(String username, String psw);
    List<User> read() throws FileReaderException;
    void write() throws FileWriterException;
}
