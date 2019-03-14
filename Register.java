import java.sql.*; 

public class Register
{
	/// Create a user using the parameters given as input: name, password
	/// Sets the authorized record and signout the other users
	/// Redirect to login unit
	public static void main(String[] args)
	{
		try 
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/authorizationdb","root","");  
			//Statement stmt=con.createStatement();  

			// update isAuthorized in false for the other records(signout)
			String IQuerySignOut = "UPDATE `authorizationdb`.`tbuser` SET `isAuthorized` = 0";
			boolean rsSo = con.createStatement().execute(IQuerySignOut);

			String IQuery = "INSERT INTO `authorizationdb`.`tbuser`(`name`,`password`,`isAuthorized`) " + 
							"VALUES('" + args[0] + "', '" + args[1] + "', 1)";
			
			boolean rs=con.createStatement().execute(IQuery);

			con.close(); 

			//redirect to login passing the same parameters recieved as input
			String params[] = {args[0], args[1]};
			Login.main(params);
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}
	}
}
