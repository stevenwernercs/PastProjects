package team2awesome.subsystem.profile;

public class PhoneNumber extends ContactMedium
{

	final int MAX_DIGITS = 10;
	final int MIN_DIGITS = 10;
	
	int 	phoneInt;
	String 	phoneString;
	
	/**
	 * 
	 * @param phoneNumber
	 */
	public PhoneNumber(String phoneNumber)
	{
		validateSyntax(phoneNumber);
	}

	/** 
	 * must be 10 numerical digits after strip of all '-' and '(' ')'
	 * @param phoneNumber
	 * @return boolean
	 */
	@Override
	protected String validateSyntax(String phoneNumber)
	{
		if(phoneNumber==null)
			return null;
		
		phoneNumber = phoneString.trim();
		phoneNumber = phoneString.replace("-", "");
		phoneNumber = phoneString.replace("+", "");
		phoneNumber = phoneString.replace("(", "");
		phoneNumber = phoneString.replace(")", "");
		phoneNumber = phoneString.replace(" ", "");
		phoneNumber = phoneString.replace("\t", "");
		
		if(phoneNumber.length()>MAX_DIGITS) 	
			return null; //not long enough
		
		if(phoneNumber.length()<MIN_DIGITS) 	
			return null; //not long enough
				

		try
		{
			Integer.parseInt(phoneNumber);
			this.phoneString = phoneNumber;
		}
		catch (Exception e)   
		{
			return null;
		}
		
		return this.phoneString;
			
	}
	
	@Override
	protected boolean setContactMedium(String phoneNumber) 
	{
		if((phoneNumber = validateSyntax(phoneNumber)) != null)
		{
			//TODO Database Contract
			this.phoneString = phoneNumber;
		}
		return false;
	}

	@Override
	protected String getContactMedium() {
		// TODO Database Contract
		return this.phoneString;
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
