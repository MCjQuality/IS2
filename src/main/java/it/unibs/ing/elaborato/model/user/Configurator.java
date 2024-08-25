package it.unibs.ing.elaborato.model.user;

import org.mindrot.jbcrypt.BCrypt;

import java.io.Serial;
import java.io.Serializable;

/**
 * Classe che estende la classe Utente e punta a rappresentare la figura del configuratore.
 */
public class Configurator extends User implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	private boolean firstAccess;
	
	public Configurator(String username, String psw, boolean firstAccess)
	{
		super(username, psw);
		this.firstAccess = firstAccess;
	}
	
	public boolean getFirstAccess() {
		return firstAccess;
	}

	public void updateCredentials(String newUsername, String newPsw)
	{
		if(getUsername().equals(newUsername))
			throw new IllegalArgumentException();
		else if (BCrypt.checkpw(newPsw, getPsw()))
			throw new IllegalArgumentException();

		setUsername(newUsername);
		setPsw(BCrypt.hashpw(newPsw, BCrypt.gensalt()));
		firstAccess = false;
	}

	@Override
	public boolean isConsumer()
	{
		return false;
	}

}
