package it.unibs.ing.elaborato.model.user;

import java.io.Serial;
import java.io.Serializable;

/**
 * Classe che simbolizza un possibile utente dell'applicazione.
 * Ricordiamo che l'utente puo' poi "specializzarsi" in configuratore oppure, nelle successive versioni, fruitore.
 */
public abstract class User implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	private String username;
	private String psw;

	public User(String username, String psw)
	{
		this.username = username;
		this.psw = psw;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getUsername()
	{
		return username;
	}

	public String getPsw()
	{
		return psw;
	}

    public abstract boolean isConsumer();

	public abstract boolean getFirstAccess();

}
