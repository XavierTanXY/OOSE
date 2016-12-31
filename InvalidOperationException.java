//FILE:             InvalidOperationException.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:          A custom exception class for invalid operation 
//RELATIONSHIP:		Inherited from Exception

import java.util.*;
public class InvalidOperationException extends Exception
{
	public InvalidOperationException()
	{
	}

	public InvalidOperationException( String message )
	{
		super( message );
	}

	public InvalidOperationException( String message, Throwable cause )
	{
		super( message, cause );
	}

	public InvalidOperationException( Throwable cause )
	{
		super( cause );
	}
}