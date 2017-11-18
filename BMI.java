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
  	
  	static Random rn = new Random();
  	
  	static ArrayList<Workout> wBank = new ArrayList<Workout>();
  	
  	public static class Workout {  														
//basic workout class
	    public String mExercise = "";
	    public int calBurned= 0;
	    
	    public int mSets = 0;	   	 //For Workouts
	    public int mReps = 0;        //For Workouts
	    public int breaks = 0;
	    
	    public double distance = 0;  //For Distance Runs (mi)
	    public int mph; 			 //For runs 
	    public int duration;		 //For runs currently
	    
	    
	  //Walk/Run Constructor - Run/Walk objects share exercise name, but differ in mph(intensity), duration, and calories 
	    public Workout(double miles, String pace, int rmph, int minutes, int burns) {
	    	mExercise = pace;
	    	distance = miles; //in miles
	        calBurned = burns;
	        mph = rmph; 
	        duration = minutes;
	        }
	        
	    	
	    public Workout(String name, int reps, int sets, int mins, int burns, int b){//Workout Constructor
	        //mReps= reps;  //Sprint 2 
	        //mSets = sets; //Sprint 2 
	        mExercise = name;
	        duration = mins;
	        calBurned = burns;
	        breaks = b;
	        
	        //add intensity or weight? probably not
	    }
	    	
	    public String toString() {
	    	String s = "";
	    	s+= (mExercise + " \n");
	    	for(int i =0; i< mExercise.length(); i++) { s+=("-");}
	    	s+= "\n";
    		s += "Duration: " + duration + " mins" + "\n";
	    	s += "Minimum Calorie Burn: " + calBurned + "\n";
    		
	    	if (this.distance == 0) {
	    		//toString for Strength Workouts
	    		//s += "Reps Per Set: " + mReps + "\n";		//Sprint 2
	    		//s += "Sets: " + mSets + "\n";				//Sprint 2
	    		s += "Work to failure with " + breaks + " minute breaks when you need rest.\n";
	    	}	else{
	    		//toString for Distance WO
	    		s += "Distance: " + distance+ " mi" + "\n";
	    		s += "Pace: " + mph+ " mph" + "\n";
	    	}
	    	return s;
	    }
	    
	   } 
	
	public static ArrayList<Workout> createRoutine(int calories){ 
// Creates Routine using workouts from wBank to meet user calorie burn
		//?Present long list of workouts, have user pick 5 workouts they like and any off days that are necessary, fill a daily or weekly routine to burn calories?
		//?Take input of hours per day dedicated to workout and days per week to take breaks, use that in scheduling ?
		calories  = Math.abs(calories); //Converted to only burn calories for the Demo
		ArrayList<Workout> bank = wBank;
		ArrayList<Workout> routine = new ArrayList<Workout>();
		if (calories <= 0) { System.out.println("****** ERROR: calories_to_burn Negative or missing *******"); return null;}
		System.out.println("Routine Burns: "+ calories + " Calories\n\n");
		while(calories > 0) {//goes one workout over
			Workout w = bank.get(rn.nextInt(bank.size()));
			//Test for fit - if accepted delete from temp bank
			if(w.calBurned >= calories) { 
				routine.add(w); //Maybe find the minimum workout in later Sprint?
				bank.remove(w);
				calories = 0;
			}
			else {//Eventually check for over saturated routines? - Right now just fill randomly from the wBank
				routine.add(w);
				bank.remove(w); //only saturation fail safe
				calories = calories - w.calBurned;
			}
		}
		
		return routine; //returns completed routine with collection of individual workout activities from wBank that in total will burn the amount of calories entered(includes 1 extra WO if calBurned not divisible
		
	}
  	static void fill_wBank() { //Creates workout objects to put into wBank - calBurned_PerSession currently minimum calories burned per exercise. [Varies By body type]
  		//Running - Distance Based 
  		for(int i = 1; i < 6; i++) {
  			Workout temp = new Workout(i , "Running", 5, i*12 , i*94); //one run (1 mi) at 5mph for 12min burns 94 cal
  			wBank.add(temp);
  		}
  		//Walking - Distance Based
  		for(int i = 1; i < 4; i++) {
  			Workout temp = new Workout(i , "Walking", 3, i*20 , i*65); //walking 1 mi at 3mph for 20min burns 65 cal
  			wBank.add(temp);
  			temp = new Workout(i , "Walking", 3, i*20 , i*65); //walking 1 mi at 3mph for 20min burns 65 cal
  			wBank.add(temp); 
  			}
  		for(int i = 1; i < 3; i++) {
  			Workout temp = new Workout(i , "Walking", 2, i*30 , i*74); //walking 1 mi at 2mph for 30min burns 74 cal
  			wBank.add(temp);
  			temp = new Workout(i , "Walking", 2, i*30 , i*74); //walking 1 mi at 2mph for 30min burns 74 cal
  			wBank.add(temp);
  			}
 
  		//Strength and HIIT - time based - implement sets of repititions Sprint 2
  		//int r, s;
  		for(int i = 1; i < 4; i++) {//514/hour		String name, int reps, int sets, int mins, int burns
  			Workout temp = new Workout("Push-ups", 0 , 0, i*10, (int)(i*10*8.56), (i*10)/5); //8.56 cal/minute
  			wBank.add(temp);
  			temp = new Workout("Push-ups", 0 , 0, i*10, (int)(i*10*8.56), (i*10)/5); //8.56 cal/minute
  			wBank.add(temp);
  		}
  		for(int i = 1; i < 4; i++) {//437/hour		String name, int reps, int sets, int mins, int burns
  			Workout temp = new Workout("Crunches", 0 , 0, i*10, (int)(i*10*4.09), (i*10)/6); 
  			wBank.add(temp);
  			temp = new Workout("Crunches", 0 , 0, i*10, (int)(i*10*4.09), (i*10)/6); 
  			wBank.add(temp);
  		}
  		for(int i = 1; i < 4; i++) {//560/hour		String name, int reps, int sets, int mins, int burns
  			Workout temp = new Workout("Lunges", 0 , 0, i*10, (int)(i*10*9.33), (i*10)/5); 
  			wBank.add(temp);
  			temp = new Workout("Lunges", 0 , 0, i*10, (int)(i*10*9.33), (i*10)/5); 
  			wBank.add(temp);
  		}
  		for(int i = 1; i < 4; i++) {//597/hour		String name, int reps, int sets, int mins, int burns, breaks
  			Workout temp = new Workout("Pull ups", 0 , 0, i*10, (int)(i*10*9.95), (i*10)/5); 
  			wBank.add(temp);
  			temp = new Workout("Pull ups", 0 , 0, i*10, (int)(i*10*4.09), (i*10)/5); 
  			wBank.add(temp);
  		}
  		
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
  			String p = "*** weightToLoseOrGain check (1/2) *** ";
 		   System.out.println(p+"\n");
       if(difference<0){
            roundedTGIR = (difference*-1)*BMItoPounds;
       }
       if(difference>0){
            roundedTGIR = difference*BMItoPounds;
       }
       roundedTGIR = (double)Math.round(roundedTGIR*10)/10;
       
       p = "*** weightToLoseOrGain check (2/2) *** ";
   	   System.out.println(p+"\n");
   	   System.out.println("difference: " + difference);
	   System.out.println("roundedTGIR: " + roundedTGIR);
	   System.out.println("BMItoPounds: " + BMItoPounds+ "\n");
       
	   return roundedTGIR;
  	   }
    public static double caloriesToBurnOrGain(){
	   //Takes pounds to lose or gain and returns the calories
    	
       poundsToCals = roundedTGIR * 3500;
       
       
       
       String p = "*** caloriesToBurnOrGain check (1) *** ";
	   System.out.println(p+"\n");
	   System.out.println("poundsToCals:" + difference);
   	   System.out.println("roundedTGIR: " + roundedTGIR);
   	   System.out.println("BMItoPounds: " + BMItoPounds + "\n");
       
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
       }//30+ over range
       return status;
       }
	public static void preloadUserData() { 
		weight = 280;
        feet = 6;
        inches = 4;
        height = (feet*12+inches);
        BMI = (weight*703)/(height*height);
        BMI = (double)Math.round(BMI*10)/10;
        BMItoPounds = (height*0.0254)*(height*0.0254)/0.453592;
        BMItoPounds = (double)Math.round(BMItoPounds*10)/10;
        
        String p = "\n*** Preload check (1) *** \n";
        p+= "User Weight:" + weight + "\n" 
        	+ "User Height:" + height + "\n"
            + "User BMI:" + BMI + "\n"
            + "BMItoPounds: " + BMItoPounds +"\n";
        System.out.println(p);
	   }
    public static void main(String[] args) {
	   //Instance of new User
       preloadUserData();
	   //String User_Status = BMIRange();
	   double User_Goal_Pounds = weightToLoseOrGain();
	   double User_Goal_Calories = caloriesToBurnOrGain();		
	   
	   //int Goal_Calories = (int)User_Goal_Calories;//Change After Demo
       fill_wBank();
	   ArrayList<Workout> User_Routine = createRoutine(2000); /*createRoutine(Goal_Calories);*/
	   
	   
	   String p = "Routine Workouts:" + User_Routine.size() + "\n"
			   + "Goal lbs: " + User_Goal_Pounds+ "\n"
			   + "Goal Calories: "+ User_Goal_Calories+"\n";
	   for( int i =0; i< User_Routine.size() ; i++ ) { //Routine toString
		   System.out.println(User_Routine.get(i));
	   }
	   System.out.println(p+"\n");
	   
	   
   }
}