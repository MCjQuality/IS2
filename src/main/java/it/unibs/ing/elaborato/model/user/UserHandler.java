package it.unibs.ing.elaborato.model.user;

import it.unibs.ing.elaborato.exception.FileWriterException;

import java.util.Optional;

public class UserHandler
{
    private final UserRepository repo;

    public UserHandler(UserRepository repo)
    {
        this.repo = repo;
    }

    public void add(User user)
    {
        repo.add(user);
    }

    public Optional<User> findUser(String username, String psw)
    {
        return repo.findUser(username, psw);
    }

    public void save() throws FileWriterException
    {
        repo.write();
    }
}
