import java.io.Serializable;


public class BusinessContact implements Serializable 
{
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String emailAddress;
	private String company;

	public BusinessContact()
	{
		firstName = lastName = phoneNumber = emailAddress = company = "";
	}
	public BusinessContact(String firstName, String lastName, String phoneNumber, String emailAddress, String company)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.company = company;
	}
	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public String getPhoneNumber() 
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() 
	{
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) 
	{
		this.emailAddress = emailAddress;
	}

	public String getCompany() 
	{
		return company;
	}

}
