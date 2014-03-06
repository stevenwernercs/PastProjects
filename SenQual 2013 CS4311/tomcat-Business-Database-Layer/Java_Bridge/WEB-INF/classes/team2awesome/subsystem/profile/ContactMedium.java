package team2awesome.subsystem.profile;

public abstract class ContactMedium {

	boolean prefered;
	
	protected abstract String validateSyntax(String entity);
	protected abstract boolean setContactMedium(String entity);
	protected abstract String getContactMedium();
	protected abstract boolean setPrimary();
	protected abstract boolean getPrimary();

}
