

public class User {
	//User Height in inches	
	protected String name, password;
	protected double curr_h, curr_w, curr_bmi;
	
	//User object will be created from seperate Registration Function
	
	public User(String username, String password, double height, double weight, double BMI ) {
		this.name = username;
		this.password = password;
		this.curr_h = height;
		this.curr_w = weight;
		this.curr_bmi = BMI;
		//Add more variables(Routes, HIIT workouts, Daily Calories)
	}	
	
	//Setters
	public void changeBMI(double c) { this.curr_bmi = c;}
	public void changeHeight(double c) { this.curr_h = c;}
	public void changeWeight(double c) { this.curr_w = c;}
	public void changePassword(String c) { this.password = c;}
	
	public static void main(String[] args) {
		User me = new User("Naming", "pword", 64, 250, (250.0/(64.0*64)));
	}
}
