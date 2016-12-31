//FILE:				Subject.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:			An interface for Subject in Observer Pattern
//RELATIONSHIP:		None

import java.util.*;
public interface Subject 
{
	public void registerObserver( Observer o );
	public void notifyObservers() throws InvalidOperationException ;
}