import java.sql.*; 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Login
{
	/// Validate the user to Log in using the parameters given as input: name, password
	/// If is already authorized redirect to main form
	/// Otherwise signout all the users and authorize the given user to redirect to main form
	public static void main(String[] args)
	{
		try 
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/authorizationdb","root","");  
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from tbuser where name = '" + args[0] + "' and password = '" + args[1] + "'");  
			if (rs.next())
			{
				if (rs.getBoolean("isAuthorized"))
				{
					System.out.println("U are already logged in. Redirecting to main form");
				} 
				else
				{
					String IQuerySignOut = "UPDATE `authorizationdb`.`tbuser` SET `isAuthorized` = 0";
					boolean rsSo = con.createStatement().execute(IQuerySignOut);

					String IQueryAuth = "UPDATE `authorizationdb`.`tbuser` SET `isAuthorized` = 1 WHERE name = '" + args[0] + "'";
					boolean rsAu = con.createStatement().execute(IQueryAuth);

					con.close();  

					System.out.println("Hooray, U are logged in successfuly");
				}

				// 	redirect
				MainForm.main(new String[0]);
				
			} else 
			{
				System.out.println("Something is wrong with the login provided, try again");
			}
			
			con.close();  
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}
	}

}