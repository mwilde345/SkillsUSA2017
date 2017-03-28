import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ContactsMain 
{

	private final static MenuOption[] choices = MenuOption.values();
	private ArrayList<BusinessContact> contacts = new ArrayList<BusinessContact>();
	private String workingDir;
	private String filename;
	
	public ContactsMain(){
		setWorkingDir(Paths.get(".").toAbsolutePath().normalize()
				.toString());
		setFilename("contacts.dat");
		contacts = FileManager.read(getWorkingDir(),getFilename());
	}

	/****************************************************
	 * Method : main
	 *
	 * Purpose : Displays menu of selections. writes to contacts.dat file upon closing.
	 *
	 * Parameters : String[] args (not used)
	 *
	 * Returns : This method does not return a value.
	 *
	 ****************************************************/
	/*public static void main(String[] args) 
	{
		ContactsMain c = new ContactsMain();
		
		//prompt the user to choose an option from the menu. close if 5.
		MenuOption operation = getRequest();
		while (operation != MenuOption.EXIT) 
		{
			switch (operation) 
			{
			case ADD:
				c.addContact();
				break;
			case DELETE:
				if (c.checkContacts())
					c.delContact();
				break;
			case SPECIFIC:
				if (c.checkContacts())
					c.viewContact();
				break;
			case LIST:
				if (c.checkContacts())
					c.displayAll();
				break;
			default:
				break;
			}
			operation = getRequest();
		}
		System.out.printf("%nClosing Program. Thank You%n");
		//write to file upon closing
		FileManager.write(c.workingDir + "/"+c.getFilename(), c.contacts);
	}*/
	
	public void setWorkingDir(String input){
		this.workingDir = input;
	}
	public String getWorkingDir(){
		return this.workingDir;
	}

	/****************************************************
	 * Method : getContacts
	 *
	 * Purpose : Return the currently updated arraylist of business contacts
	 *
	 * Parameters : none
	 *
	 * Returns : arraylist of business contacts
	 *
	 ****************************************************/
	public ArrayList<BusinessContact> getContacts() 
	{
		return this.contacts;
	}
	
	public void setContacts(ArrayList<BusinessContact> contacts)
	{
		this.contacts = contacts;
	}

	/****************************************************
	 * Method : checkContacts
	 *
	 * Purpose : determines if there are contacts stored in the .dat file. Displays a message if not.
	 *
	 * Parameters : none
	 *
	 * Returns : This method returns false if there have been no contacts found.
	 *
	 ****************************************************/
	public boolean checkContacts() 
	{
		if (this.contacts.size() < 1) 
		{
			System.out.printf("%nNo Contacts Stored%n");
			return false;
		} else 
		{
			return true;
		}
	}

	/****************************************************
	 * Method : addContact
	 *
	 * Purpose : Gather info from System.in and store into a BusinessContact object.
	 *
	 * Parameters : none
	 *
	 * Returns : This method does not return a value.
	 *
	 ****************************************************/
	public void addContact() 
	{
		System.out.printf("%n--[ Add Contact ]--%n");
		Scanner s = new Scanner(System.in);
		System.out.printf("%nFirst Name: ");
		String firstName = s.next();
		System.out.printf("%nLast Name: ");
		String lastName = s.next();
		System.out.printf("%nPhone Number: ");
		String phoneNumber = s.next();
		System.out.printf("%nEmail Address: ");
		String emailAddress = s.next();
		System.out.printf("%nCompany: ");
		String company = s.next();
		BusinessContact b = new BusinessContact(firstName, lastName,
				phoneNumber, emailAddress, company);
		contacts.add(b);
	}

	/****************************************************
	 * Method : addContact (Overloader)
	 *
	 * Purpose : Used for implementation with a gui to store values from text field.
	 *
	 * Parameters : member variables of BusinessContact class.
	 *
	 * Returns : This method does not return a value.
	 *
	 ****************************************************/
	public void addContact(String firstName, String lastName,
			String phoneNumber, String emailAddress, String company) 
	{
		BusinessContact b = new BusinessContact(firstName, lastName,
				phoneNumber, emailAddress, company);
		contacts.add(b);
	}

	/****************************************************
	 * Method : delContact
	 *
	 * Purpose : remove contact from arraylist based on index provided by user
	 *
	 * Parameters : none
	 *
	 * Returns : This method does not return a value.
	 *
	 ****************************************************/
	public void delContact() 
	{
		System.out.printf("%n--[ Delete Contact ]--%n");
		Scanner s = new Scanner(System.in);
		showContactIndex();
		System.out.printf("%nClient Number: ");
		int index = s.nextInt();
		this.contacts.remove(index - 1);
		showContactIndex();
	}

	/****************************************************
	 * Method : viewContact
	 *
	 * Purpose : displays menu of contacts to choose from to view their details.
	 *
	 * Parameters : none
	 *
	 * Returns : none
	 *
	 ****************************************************/
	public void viewContact() 
	{
		System.out.printf("%n--[ View Contact ]--%n");
		Scanner s = new Scanner(System.in);
		showContactIndex();
		System.out.printf("%n%nClient Number: ");
		int index = s.nextInt();
		displayAll(index - 1);
	}

	/****************************************************
	 * Method : displayAll (Overloader)
	 *
	 * Purpose : Show details for specific  contact. Index of contact given by user
	 * 			from viewContact()
	 *
	 * Parameters : index - index for the BusinessContact arrayList
	 *
	 * Returns : none
	 *
	 ****************************************************/
	public void displayAll(int index) 
	{
		BusinessContact b = this.contacts.get(index);
		System.out.printf("%n%-20s%-15s%-20s%-15s%n", "Name", "Phone", "Email",
				"Company");
		System.out.printf("%n%-20s%-15s%-20s%-15s%n", "----", "-----", "-----",
				"-------");
		System.out.printf("%n%-20s%-15s%-20s%-15s%n", b.getLastName() + ", "
				+ b.getFirstName(), b.getPhoneNumber(), b.getEmailAddress(),
				b.getCompany());
	}

	/****************************************************
	 * Method : displayAll
	 *
	 * Purpose : shows the details for all contacts in the arraylist
	 *
	 * Parameters : none
	 *
	 * Returns : none
	 *
	 ****************************************************/
	public void displayAll() 
	{
		System.out.printf("%n--[ My Contact List ]--%n");
		System.out.printf("%n%-20s%-15s%-20s%-15s%n", "Name", "Phone", "Email",
				"Company");
		System.out.printf("%n%-20s%-15s%-20s%-15s%n", "----", "-----", "-----",
				"-------");
		for (BusinessContact b : this.contacts) 
		{
			System.out.printf("%n%-20s%-15s%-20s%-15s%n", b.getLastName()
					+ ", " + b.getFirstName(), b.getPhoneNumber(),
					b.getEmailAddress(), b.getCompany());
		}
	}

	/****************************************************
	 * Method : showContactIndex
	 *
	 * Purpose : displays the contacts' first and last names in order according 
	 * to their index in the BusinessContact arraylist
	 *
	 * Parameters : none
	 *
	 * Returns : none
	 *
	 ****************************************************/
	public void showContactIndex() 
	{
		for (BusinessContact b : this.contacts) 
		{
			System.out.printf("%n%d. %s, %s", contacts.indexOf(b) + 1,
					b.getLastName(), b.getFirstName());
		}
		System.out.println();
	}

	/****************************************************
	 * Method : getRequest
	 *
	 * Purpose : Display in the console the options in the menu and prompts 
	 * the user to choose one.
	 *
	 * Parameters : none
	 *
	 * Returns : MenuOption based on the number entered
	 *
	 ****************************************************/
	private static MenuOption getRequest() 
	{
		int request = 5;
		System.out.printf(
				"%n--[ Manage My Contacts ]--%n%n%s%n%s%n%s%n%s%n%s%n",
				"1. Add new contact", "2. Delete a contact",
				"3. View a contact", "4. Display all contacts",
				"5. Exit program");
		try 
		{
			Scanner input = new Scanner(System.in);
			do 
			{
				System.out.printf("%nSelection: ");
				request = input.nextInt();
			} while ((request < 1) || (request > 5));
		} catch (NoSuchElementException e) 
		{
			System.err.println("Invalid input. Closing");
		}
		return choices[request - 1];
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
