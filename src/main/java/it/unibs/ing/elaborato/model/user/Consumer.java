package it.unibs.ing.elaborato.model.user;

import java.io.Serial;

public class Consumer extends User {
	
	@Serial
	private static final long serialVersionUID = 1L;
	private final String email;
	private final String district;

	public Consumer(String username, String psw, String district, String email)
	{
		super(username, psw);
		this.district = district;
		this.email = email;
	}

	public String getEmail()
	{
		return email;
	}

	public String getDistrict()
	{
		return district;
	}

	@Override
	public boolean isConsumer() {return true;}

	@Override
	public boolean getFirstAccess() {
		return false;
	}

}
