import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;


public class FileManager 
{
	private static ObjectOutputStream output;
	private static ObjectInputStream input;

	/****************************************************
	 * Method     : write
	 *
	 * Purpose    : serialize all business contacts and write to contacts.dat
	 *
	 * Parameters : directory           - current working directory
	 *              contacts - current contacts in arraylist.
	 *
	 * Returns    : This method does not return a value.
	 *
	 ****************************************************/
	public static void write(String directory, ArrayList<BusinessContact> contacts)
	{
		try
		{
			output = new ObjectOutputStream(
					Files.newOutputStream(Paths.get(directory)));
			for(BusinessContact b : contacts)
			{
				output.writeObject(b);	
			}
			if(output!=null) output.close();
		}catch(IOException e)
		{
			System.err.println("Error opening file. Terminating");
			System.exit(1);
		}catch(NoSuchElementException e)
		{
			System.err.println("Invalid Contact Object found. Cannot write to file. Terminating");
			System.exit(1);
		}
	}

	/****************************************************
	 * Method     : read
	 *
	 * Purpose    : reads from the contacts.dat file and populates arraylist
	 *
	 * Parameters : filename              - location of contacts.dat (working directory)
	 *
	 * Returns    : This method returns an arraylist of objects read in from file
	 *
	 ****************************************************/
	public static ArrayList<BusinessContact> read(String directory, String filename)
	{
		ArrayList<BusinessContact> existingContacts = new ArrayList<BusinessContact>();
		File tmp = new File(directory+"/"+filename);
		if(!tmp.exists())
		{
			System.out.printf("%nCould not find a current contacts file.");
			return existingContacts;
		}
		try
		{
			input = new ObjectInputStream(
					Files.newInputStream(Paths.get(filename)));
			try
			{
				while(true)
				{
					BusinessContact bc = (BusinessContact) input.readObject();
					existingContacts.add(bc);
				}
			}catch(EOFException e)
			{
				System.out.printf("%nSuccessful File Read%n");
			}
			if(input!=null) input.close();
		}catch(IOException e)
		{
			System.err.println("Error with file read.");
			System.exit(1);
		}catch(ClassNotFoundException e)
		{
			System.err.println("Corrupted .dat file. Terminating");
		}
		return existingContacts;
	}
}
