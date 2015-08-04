import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 
 */

/**
 * @author bkrenz
 *
 */
public class TextToAccount {

	/*  */
	
	
	
	/*  */
	
	
	/* Main Method */
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Arguments Verification
		if (args.length != 2)
		{
			System.out.println("Usage: java TextToAccount {input-file} {output-file}");
			return;
		}
		
		// Do stuff!
		Scanner l_FileReader;
		ObjectOutputStream l_FileWriter;
		Map<String, Account> l_AccountsMap = new TreeMap<>();
		
		try {
			// Init the File IO objects
			l_FileReader = new Scanner(new File(args[0]));
			l_FileWriter = new ObjectOutputStream(new FileOutputStream(args[1]));
			
			// Parse File
			while(l_FileReader.hasNext())
			{
				String name = l_FileReader.next();
				int amount = l_FileReader.nextInt();
				Account l_Acc = new Account(name, amount);
				l_AccountsMap.put(name, l_Acc);
			}
			
			l_FileWriter.writeObject(l_AccountsMap);
		}
		catch (Exception e) {
			System.out.println("error!");
			e.printStackTrace();
		}
		
		
		// Test
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("output.dat"));
			Map<String, Account > testMap = (TreeMap) inputStream.readObject();
			for (String s : testMap.keySet())
				System.out.println(testMap.get(s));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		

	}
	
	/* End Main Method */

}
