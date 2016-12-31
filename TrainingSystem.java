//FILE:				TrainingSystem.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:			A class that runs the whole program and takes in five command line parameters:
//					Property File, Event File, Plan File, Start Year, End Year
//RELATIONSHIP:		None

public class TrainingSystem
{
	public static void main( String[] args ) 
	{
		TrainingSystemManager system = new TrainingSystemManager();
		system.run( args[0], args[1], args[2], args[3], args[4] );
	}


}