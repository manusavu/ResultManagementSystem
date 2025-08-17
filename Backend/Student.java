import java.util.HashMap;
public class Student {
	private int id;
	private String rollNumber;
	private String name;
//	private String password = "123456";
	private HashMap<String,Double> marks;
	
	Student(int id,String rollNumber,String name){
		this.id = id;
		this.rollNumber = rollNumber;
		this.name = name;
		this.setMarks(new HashMap<>());
		getMarks().put("maths", 0.0);
		getMarks().put("physics",0.0);
		getMarks().put("chemistry",0.0);
	}
	public int getId() {
		return this.id;
	}
	public String getRollNumber() {
		return this.rollNumber;
	}
	public String getName() {
		return this.name;
	}
//	public String getPassword() {
//		return this.password;
//	}
	public HashMap<String,Double> getMarks() {
		return marks;
	}
	public void setMarks(HashMap<String,Double> marks) {
		this.marks = marks;
	}


}
