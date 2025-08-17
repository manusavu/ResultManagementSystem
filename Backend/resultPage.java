import java.util.Scanner;
public class resultPage {

	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		System.out.println("-------          Welcome To the Result Management System     ------------");
		System.out.println();
		System.out.println("Student : Enter 1              Admin : Enter 2"
				+ "\nEnter Your choice: ");System.out.println();
		int role = inp.nextInt();
		ResultManagement obj = new ResultManagement(inp);
		switch(role) {
		case 1->{
					obj.studentPage();
					break;
				}
		case 2->{
					obj.adminPage();
					break;
				}
		default->{
			System.out.println("You have entered Invalid choice!");
			inp.close();
			System.exit(0);
		}
		}
		

	}

}
