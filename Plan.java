//FILE:				Plan.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:			A container class that stores and manages Plan
//RELATIONSHIP:		None

public class Plan 
{
	//Constant for plan type
	public static final char SELL = 'S';
	public static final char BUY = 'B';

	private int year;
	private char transactionType;
	private String propertyName;
	
	//Alternate Constructor
	public Plan( int inYear, char inTransactionType, String inPropertyName )
	{
		setYear( inYear );
		setTransactionType( inTransactionType);
		setProperty( inPropertyName );
	}

	//Mutator - set year
	private void setYear( int inYear )
	{
		if( inYear < 0 )
		{
			throw new IllegalArgumentException( "Plan year invalid" );
		}
		else 
		{
			year = inYear;
		}
	}

	//Mutator - set transaction type
	private void setTransactionType( char inTransactionType )
	{
		if( inTransactionType != SELL && inTransactionType != BUY )
		{
			throw new IllegalArgumentException( "Plan type invalid" );
		}
		else
		{
			transactionType = inTransactionType;
		}
		
	}

	//Mutator - set affected property name
	private void setProperty( String inPropertyName )
	{
		if( inPropertyName.equals(" ") || inPropertyName == null )
		{
			throw new IllegalArgumentException( "Property name invalid in Plan" );
		}
		else
		{
			propertyName = inPropertyName;
		}
	
	}

	//Accessor - get year
	public int getYear()
	{
		return year;
	}

	//Accessor - get transaction type
	public char getTransactionType()
	{
		return transactionType;
	}

	//Accessor - get affected property name
	public String getPropertyName()
	{
		return propertyName;
	}

	//PURPOSE:	Returns details of a plan
	//IMPORT:	None
	//EXPORT:	Plan's details in String
	public String toString()
	{
		return new String( "Plan Year: " + year + " - Affected Name: " + propertyName + " - Transaction Type: " + transactionType ); 
	}

}