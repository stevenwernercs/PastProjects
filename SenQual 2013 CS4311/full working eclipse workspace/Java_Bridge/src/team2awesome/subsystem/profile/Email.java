package team2awesome.subsystem.profile;

public class Email extends ContactMedium
{
	
	String emailString;
	
	public Email(String email)
	{
		
	}

	@Override
	protected String validateSyntax(String entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setContactMedium(String entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected String getContactMedium() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setPrimary() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean getPrimary() {
		// TODO Auto-generated method stub
		return false;
	}

}
