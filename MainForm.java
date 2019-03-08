import java.sql.*; 
import java.util.Scanner;

public class MainForm
{
	// Verify if there is a user loged in, in that case show the menu
	// Otherwise redirect to the login
	public static void main(String[] args)
	{
		try 
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/authorizationdb","root","");  
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from tbuser where isAuthorized = 1");  
			if (rs.next())
			{
				System.out.println("Welcome, " + rs.getString(2));
				System.out.println("1. Option x");
				System.out.println("2. Option y");
				System.out.println("3. Sign out");
				System.out.println("--------------------------------------");

				Scanner scanner = new Scanner(System.in);
				String option = scanner.nextLine();
				//System.out.println("You selected option " + option);

				if(option == "3")
				{
					// 	redirect to login
					Login.main(new String[0]);
				} else 
				{
					System.out.println("You selected option " + option);
				}
				
			} 
			else 
			{
				System.out.println("Something is wrong with the login provided, try again");
				// 	redirect to login
			}
			
			con.close();  
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}
	}
}