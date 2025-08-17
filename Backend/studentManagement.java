import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Scanner;
import java.sql.SQLException;
public class studentManagement {
	
	public static void main(String[] args) {
		String pwd = "admin";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		}catch(ClassNotFoundException e) {
			System.out.println("No Driver found!");
		}
		String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
		
		String username = "root";
		String password = "123456789";
		Connection con;
		Scanner inp = new Scanner(System.in);
		Admin admin = new Admin(inp);
		System.out.println("Welcome to the Result Management System Admin");
		System.out.print("Enter the password: ");
		String enteredPassword = inp.next();
		if(pwd.equals(enteredPassword)) {
			try {
				con = DriverManager.getConnection(url,username,password);
				
				while(true) {
				System.out.println("---------Enter the choice--------");
				System.out.print(""
						+"\n1.Add Student's Data"
						+ "\n2.Update marks"
						+ "\n3.Delete Records"
						+ "\n4.Show all records"
						+ "\n5.Display Student records"
						+ "\n6.Exit\n"
						+ "Select the option: ");
				int choice = inp.nextInt();
				System.out.println();
				switch(choice) {
							case 1 ->{
								boolean res = admin.addStudentDetails(con);
								 if(res)
								    {
								    	System.out.println("---Added Successfully!---");
								    }
								    else {
								    	System.out.println("---Data Entry Falied---");
								    }
								    break;
							}
							case 2 ->{	
										boolean res = admin.updateMarks(con);
									    if(res)
									    {
									    	System.out.println("---Updated Successfully!---");
									    }
									    else {
									    	System.out.println("---Update Falied---");
									    }
									    break;
									}
							case 3 ->{
									 boolean res = admin.deleteRecord(con);
									 if(res)
									 	{
									    	System.out.println("---Deleted Successfully!---");
									    }
									    else {
									    	System.out.println("---Students data Not found!---");
									    }
									    break;
									}
							case 4->{
									admin.showAllRecords(con);
									break;
							}
							case 5->{
								admin.displayStudentRecord(con);
								break;
							}
							case 6->{
								System.out.println("Sucessfully Logged out!");
								con.close();
								System.exit(0);
							}
						}
				}
				
			
		}catch(SQLException e)
			{
				System.out.println("Error Connecting to the database!");
			}
		}
		else
		{
			System.out.println("Invalid password for admin!");
		}
		inp.close();
		
	}
}
