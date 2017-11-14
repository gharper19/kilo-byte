import java.util.Scanner;
import java.util.ArrayList; 
import java.util.Random;

//Make your workout objects(18) and add them to wBank
//Create formula to pick from those workouts until calories limit is reached
//Runs should be used to fill in unfillable space(Set a limit for # of runs in a single routine)
// - in the event a no workout fits a run with the appropriate distance will complete routine
public class BMI {
	static double weight, feet, inches, height, BMI, difference, BMItoPounds, roundedTGIR, poundsToCals;
  	static String status = "";
  	
  	static ArrayList<Workout> wBank = new ArrayList<Workout>();
  	
  	public static class Workout {  														
	  	//basic workout class
	    public String mExercise = "";
	    public int calBurned= 0;
	    
	    public int msets = 0;	   	 //For Workouts
	    public int mReps = 0;        //For Workouts
	    
	    public double distance = 0;  //For Distance Runs (mi)
	    public int mph; 			 //For runs 
	    public int duration;		 //For runs currently
	    
	  //Walk/Run Constructor - Run/Walk objects share exercise name, but differ in mph(intensity), duration, and calories 
	    public Workout(String pace, double miles, int rmph, int minutes, int burns) {
	    	mExercise = pace;
	    	distance = miles; //in miles
	        calBurned = burns;
	        mph = rmph; 
	        duration = minutes;
	        
	    	}
	    public Workout(String name, int reps, int sets, int burns){//Workout Constructor
	        mReps= reps;
	        msets = sets;
	        mExercise = name;
	        calBurned = burns;
	    	}
	    
	} 
	
	public static ArrayList<Workout> createRoutine(int calories){ 
		// Creates Routine using workouts from wBank to meet user calorie burn
		//Present long list of workouts, have user pick 5 workouts they like and any off days that are necessary, fill a daily or weekly routine to burn calories?
		//Take input of hours per day dedicated to workout and days per week to take breaks, use that in scheduling 
		calories  = Math.abs(calories); //Converted to only burn calories for the Demo
		ArrayList<Workout> bank = wBank;
		ArrayList<Workout> routine = new ArrayList<Workout>();
		if (calories <= 0) { System.out.println("****** ERROR: calories_to_burn Negative or missing *******");}
		
		while(calories > 0) {//goes one workout over
			Random rn = new Random();
			Workout w = bank.get(rn.nextInt(bank.size()));
			//Test for fit - if accepted delete from temp bank
			if(w.calBurned >= calories) { 
				routine.add(w); //Maybe find the minimum workout in later Sprint?
				calories = 0;
			}
			else {//Eventually check for over saturated routines? - Right now just fill randomly from the wBank
				routine.add(w);
				bank.remove(w); //only saturation fail safe
				calories = calories - w.calBurned;
			}
			return routine; //returns completed routine with collection of individual workout activities from wBank that in total will burn the amount of calories entered(includes 1 extra WO if calBurned not divisible
		}
		return routine;
	}
  	static void fill_wBank() { //Creates workout objects to put into wBank - calBurned_PerSession currently minimum calories burned per exercise. [Varies By body type]
  		//Running - Distance Based 
  		for(int i = 1; i < 6; i++) {
  			Workout temp = new Workout("Run", i , 5, i*12 , i*94); //one run (1 mi) at 5mph for 12min burns 94 cal
  			wBank.add(temp);
  		}
  		//Walking - Distance Based
  		for(int i = 1; i < 6; i++) {
  			Workout temp = new Workout("Walk", i , 3, i*20 , i*65); //walking 1 mi at 3mph for 20min burns 65 cal
  			wBank.add(temp); }
  		for(int i = 1; i < 6; i++) {
  			Workout temp = new Workout("Walk", i , 2, i*30 , i*74); //walking 1 mi at 2mph for 30min burns 74 cal
  			wBank.add(temp);}
  		
  		//Strength Workouts - Sets of Reps
  		/*
  		for(int i = 1; i < 6; i++) {
  			Workout temp = new Workout("Walk", i , 3, i*20 , i*65); 
  			wBank.add(temp);
  		*/ 
  	}
  	public static double getBMI(){
  		//User inputs metrics and returns BMI
        Scanner s = new Scanner(System.in);
        System.out.print("Enter your weight(lbs.): ");
        weight = s.nextDouble();
        System.out.println("\nEnter your height:");
        System.out.print("\tFeet: ");
        feet = s.nextDouble();
        System.out.print("\tInches: ");
        inches = s.nextDouble();
        height = (feet*12+inches);
        BMI = (weight*703)/(height*height);
        BMI = (double)Math.round(BMI*10)/10;
        BMItoPounds = (height*0.0254)*(height*0.0254)/0.453592;
        BMItoPounds = (double)Math.round(BMItoPounds*10)/10;
        s.close();
        return BMI;
  		}
  	
  		public static double weightToLoseOrGain(){
	   //returns pounds to lose(-negative) or to gain(+positive)
       if(difference<0){
            roundedTGIR = (difference*-1)*BMItoPounds;
       }
       if(difference>0){
            roundedTGIR = difference*BMItoPounds;
       }
       roundedTGIR = (double)Math.round(roundedTGIR*10)/10;
       return roundedTGIR;
  	   }
    public static double caloriesToBurnOrGain(){
	   //Takes pounds to lose or gain and returns the calories
       poundsToCals = roundedTGIR * 3500;
       return poundsToCals;
  	   }
    public static String BMIRange(){
    	//Returns 1 of 3 Strings indicating BMI category 
       if(BMI<18.5){
            status = "below range";
            difference = BMI - 18.5;
            difference = (double)Math.round(difference*10)/10;
       }
       else if(BMI>=18.5&&BMI<=24.9){
            status = "within range";
            difference = 0;
       }
       else{
            status = "over range";
            difference = BMI - 24.9;
            difference = (double)Math.round(difference*10)/10;
       }
       return status;
       }
   
    public static void main(String[] args) {
	   //Instance of new User
	   double User_BMI = getBMI();
	   String User_Status = BMIRange();
	   double User_Goal_Pounds = weightToLoseOrGain();
	   double User_Goal_Calories = caloriesToBurnOrGain();
	   int Goal_Calories = (int)User_Goal_Calories;
	   
	   
	   ArrayList<Workout> User_Routine = createRoutine(Goal_Calories);
	   
	   
   }
}