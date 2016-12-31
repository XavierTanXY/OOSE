//FILE:             IOBehaviour.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:          An Interface is responsible of reading different types of file - It uses Strategy Pattern
//RELATIONSHIP:		None

import java.io.*;
import java.util.*;
public interface IOBehaviour 
{
	public void performReading( String fileName ) throws IOException;
	public List<Property> getProcessedProperties() throws InvalidOperationException;
	public List<Event> getProcessedEvent() throws InvalidOperationException;
	public List<Plan> getProcessedPlan() throws InvalidOperationException;
}