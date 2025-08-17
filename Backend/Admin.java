//import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class Admin {

	private Scanner inp;
	Admin(Scanner scanner){
		this.inp = scanner;
	}
	public boolean addStudentDetails(Connection con) {
		System.out.println("Enter Student's id: ");
		boolean ans = false;
		int stu_id = inp.nextInt();
		System.out.println("Enter Student's Name: ");
		String name = inp.next();
		System.out.println("Enter Student's Roll Number: ");
		String Roll_Num = inp.next();
		String query = "INSERT INTO STUDENTS(ID,NAME,ROLLNUMBER) VALUES(?,?,?)";
		String q2  = "INSERT INTO MARKS(ID,MATHS,PHYSICS,CHEMISTRY) VALUES(?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,stu_id);
			ps.setString(2,name);
			ps.setString(3, Roll_Num);
			int res = ps.executeUpdate();
			if(res>0) {
				System.out.println("-----      Marks Details     --------");
				System.out.println("Enter Maths Marks: ");
				Double maths = inp.nextDouble();
				System.out.println("Enter Physics Marks: ");
				Double physics = inp.nextDouble();
				System.out.println("Enter Chemistry Marks: ");
				Double chemistry = inp.nextDouble();
				ps = con.prepareStatement(q2);
				ps.setDouble(2, maths);
				ps.setDouble(3, physics);
				ps.setDouble(4, chemistry);
				ps.setInt(1, stu_id);
				res = ps.executeUpdate();
				
				if(res>0) {
					ans = true;
				}
				else {
					ans = false;
				}
			}
			else {
				ans = false;
			}
			
			ps.close();
		}catch(SQLException e) {
//			e.printStackTrace();
			System.out.println("Error Connecting with Database");
		}
		return ans;
		
	}
	public boolean updateMarks(Connection con) {
		int ret=-1;
		System.out.println("Enter the students id: ");
		int stu_id = inp.nextInt();
		System.out.println("Enter the subject name: ");
		String subject = inp.next();
		System.out.println("Enter the updated marks: ");
		Double upd_marks = inp.nextDouble();
		String q1 = String.format("UPDATE MARKS SET %s = ? WHERE ID = ?",subject);
		try {
		PreparedStatement p1 = con.prepareStatement(q1);
		p1.setDouble(1, upd_marks);
		p1.setInt(2, stu_id);
		ret = p1.executeUpdate();
//		con.close();
		p1.close();
		
		}catch(SQLException e) {
			System.out.println("Error Connecting with Database");
//			e.printStackTrace();
		}
		
		return ret>0;
	}
	public boolean deleteRecord(Connection con) {
		int ret = -1;
		System.out.println("Enter the students id: ");
		int stu_id = inp.nextInt();
		String q1 = "DELETE FROM STUDENTS WHERE ID = ?";
		try {
		PreparedStatement p1 = con.prepareStatement(q1);
		p1.setInt(1, stu_id);
		ret = p1.executeUpdate();
//		con.close();
		p1.close();
		}catch(SQLException e) {
			System.out.println("Error connection to database!");
//			e.printStackTrace();
		}
		return ret>0;
		
	}
	public void showAllRecords(Connection con) {
		String query = "SELECT S.ID,S.NAME,S.ROLLNUMBER,M.MATHS,M.PHYSICS,M.CHEMISTRY FROM STUDENTS AS S JOIN MARKS AS M ON S.ID = M.ID;";
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			System.out.println("------      STUDENTS RESULTS     -------");
			ResultSetMetaData metaData = rs.getMetaData();
			int colLength = metaData.getColumnCount();
			int l = 0;
			int maxL = 0;
			ArrayList<String> headers = new ArrayList<>();
			for(int i=1;i<=colLength;i++) {
				headers.add(metaData.getColumnName(i));
			}
			for(String ele:headers) {
				l += ele.length()+1;
				if(ele.length()>maxL) {
					maxL = ele.length();
				}
			}
			for(String ele:headers) {
				System.out.print(ele);
				for(int k=0;k<=(maxL-ele.length());k++) {
					System.out.print(" ");
				}
			}
			System.out.println();
			for(int i=0;i<50;i++) {
				System.out.print("_");
			}
			System.out.println();
			while(rs.next()) {
				for(int j=1;j<=colLength;j++) {
					
				System.out.print(rs.getString(j));
				for(int k=0;k<=(maxL-rs.getString(j).length());k++) {
					System.out.print(" ");
				}
				}
				System.out.println();
			}
			System.out.println();System.out.println();
//			con.close();
			st.close();
			rs.close();
			
		}catch(SQLException e) {
			System.out.println("Error connection to database!");
		}
		
	}
	public void displayStudentRecord(Connection con) {
		System.out.println("Enter the students id: ");
		int stu_id = inp.nextInt();
		String query = "SELECT S.ID,S.NAME,S.ROLLNUMBER,M.MATHS,M.PHYSICS,M.CHEMISTRY FROM STUDENTS AS S JOIN MARKS AS M ON S.ID = M.ID WHERE S.ID = ?;";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, stu_id);
			ResultSet rs = ps.executeQuery();
			
			System.out.println("------      STUDENTS RESULT    -------");
			ResultSetMetaData metaData = rs.getMetaData();
			int colLength = metaData.getColumnCount();
			int l = 0;
			int maxL = 0;
			ArrayList<String> headers = new ArrayList<>();
			for(int i=1;i<=colLength;i++) {
				headers.add(metaData.getColumnName(i));
			}
			for(String ele:headers) {
				l += ele.length()+1;
				if(ele.length()>maxL) {
					maxL = ele.length();
				}
			}
			for(String ele:headers) {
				System.out.print(ele);
				for(int k=0;k<=(maxL-ele.length());k++) {
					System.out.print(" ");
				}
			}
			System.out.println();
			for(int i=0;i<50;i++) {
				System.out.print("_");
			}
			System.out.println();
			while(rs.next()) {
				for(int j=1;j<=colLength;j++) {
					
				System.out.print(rs.getString(j));
				for(int k=0;k<=(maxL-rs.getString(j).length());k++) {
					System.out.print(" ");
				}
				}
				System.out.println();
			}
			System.out.println();System.out.println();
			ps.close();
			rs.close();
			}
//			con.close();
			catch(SQLException e) {
			System.out.println("Error Connecting to the Database");
		}
		
		
	}

}
