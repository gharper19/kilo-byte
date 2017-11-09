import java.util.Scanner;
public class BMI_Calculator{
	static Scanner input = new Scanner(System.in);
	/*User can either
	 * - Input measurements to calculate BMI
	 * - Choose appropriate representation from models with precalculated BMIs
	 * - Or Manually Enter BMI, 
	 * Also include functions to allow user to make changes to daily calorie intake, BMI, and measurements at any time
	 *    - User should be reminded to update progress
	 */
	
	public static void main(String[] args) {
		
			
	}
	public void registerUser(){ //Registration Process - Gets data to put into User Constructor
				
		
		
	}
	public void calcBMI(User u){
		//Presents 3 BMI options (Needs to be represented with graphical buttons)
				System.out.println("Let's get started by Calculating your Body Mass Index(BMI)! ");
				double bmi;
				
				boolean redo = false;
				do { //User input Error Loop
					System.out.println("/nIf you already know your BMI, you can set it yourself by entering 0.");
					System.out.println("Otherwise you can either enter 1 to submit your height and weight/n" 
					+ "or enter 2 to selected a model that closest fits your body type.");
					
					//Recieves and analyzes choice
					int c = input.nextInt();
					switch(c) {
					
					//Manually Set BMI for this user
					case 0: 
						System.out.print("Enter your calculated BMI: ");
						//User.changeBMI(input.nextDouble())
						redo = false;
						break;
					
					//Height, Weight, and BMI need to be saved to user object attributes
					case 1: 
						double inches, pounds;
						
						//Height in inches (Detect and Read in feet and inches as well) - Convert to meters 
						System.out.print("Enter your height in inches: "); 
						inches = input.nextDouble();
						//Save User Height here,
					
						//Weight in pounds - Convert to KG
						System.out.print("Enter your weight in inches: "); 
						pounds = input.nextDouble();
						//Save User weight here
						bmi = (pounds/(inches*inches))* 703; //Returns BMI - 703 is the conversion constant 
						//Change user object data here: Save h, w, and BMI
						redo = false;
						break;
					//Show three different visual models with their heights and weights and let user select one as their representation
					case 2:
						//Once chosen, save the h, w, and BMI of the model as the User's metrics
						redo = false;
						break;
					
					default: //redo
						redo = true;
					} 
				}while(redo == true);
				//Now Determine Category: underweight, healthy, and Overweight - Based off of BMI result 
	
	}

}
