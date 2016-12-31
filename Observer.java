//FILE:				Observer.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:			An interface for Observer in Observer Pattern
//RELATIONSHIP:		None

import java.util.*;
public interface Observer 
{
	public void update( List<Event> events, List<Property> properties, int currentYear, List<Plan> plans, PrimaryCompany pCompany ) throws InvalidOperationException;
}