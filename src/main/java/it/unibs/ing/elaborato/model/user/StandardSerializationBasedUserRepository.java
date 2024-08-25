package it.unibs.ing.elaborato.model.user;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.util.Constants;
import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * La classe mira a rappresentare la lista di utenti correttamente registrati.
 * Contiene inoltre metodi che svolgono controlli, modifiche, letture e salvataggi al file che contiene la lista delle utenze.
 */
public class StandardSerializationBasedUserRepository implements UserRepository
{
	private final List<User> users;
	private final String filepath;

	public StandardSerializationBasedUserRepository(String filepath) throws FileReaderException
	{
		this.filepath = filepath;
		this.users = read();
	}
	
	public void add(User user)
	{
		if(users.contains(user))
			throw new IllegalArgumentException("User already exists");
		users.add(user);
	}

	public Optional<User> findUser(String username, String psw) {
		return users.stream().filter(user -> user.getUsername().equals(username) && BCrypt.checkpw(psw, user.getPsw())).findFirst();
	}

	@Override
	public List<User> read() throws FileReaderException
	{
		List<User> users = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(filepath)))
		{
			while (scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				String[] parts = line.split(":");

				String username = parts[0].trim();
				String psw = parts[1].trim();
				boolean firstAccess = Boolean.parseBoolean(parts[2].trim());
				String district = parts[3].trim();
				String email = parts[4].trim();

				if (!district.equals("null"))
				{
					users.add(new Consumer(username, psw, district, email));
				}
				else
				{
					users.add(new Configurator(username, psw, firstAccess));
				}
			}
		} catch (FileNotFoundException e) {
			throw new FileReaderException();
		}
		return users;
	}

	@Override
	public void write() throws FileWriterException
	{
		File file = new File(filepath);
		try
		{
			FileWriter out = new FileWriter(file);
			for (User user : users)
			{
				if (user instanceof Configurator configurator)
				{
					out.write(user.getUsername() + ":" + user.getPsw() + ":" + configurator.getFirstAccess() + ":" + "null" + ":" + "null" + Constants.NEW_LINE);
				}
				else
				{
					Consumer consumer = (Consumer) user;
					out.write(consumer.getUsername() + ":" + consumer.getPsw() + ":" + "null" + ":" + consumer.getDistrict() + ":" + consumer.getEmail() + Constants.NEW_LINE);
				}
			}
			out.close();
		} catch (IOException e) {
			throw new FileWriterException();
		}
	}

}
