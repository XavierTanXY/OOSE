//FILE:				Property.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:			A container class that stores and manages Property - abstract class
//RELATIONSHIP:		None

public abstract class Property
{
	public static final double FIVE_PERCENT = 0.5;
	public static final String UNAMED_OWNER = "Unamed Owner";

	private String name;
	private double monetaryValue;
	private Property owner;
	private double profit;

	//Alternate Constructor
	public Property( String inName, double inMonetaryValue, Property inOwner )
	{
		setName( inName );
		setMonetaryValue( inMonetaryValue );
		setOwner( inOwner );
		profit = 0.0;
	}

	//Mutator - set name
	public void setName( String inName )
	{
		if( inName == null || inName.equals("") )
		{
			throw new IllegalArgumentException( "Name can't be null or empty string" );
		}
		else 
		{
			name = inName;
		}

	}

	//Mutator - set monetary value
	public void setMonetaryValue( double inMonetaryValue )
	{
		if( inMonetaryValue < 0.0 )
		{
			throw new IllegalArgumentException( "Monetary value is negative" );
		}
		else
		{
			monetaryValue = inMonetaryValue;
		}
	
	}


	//Mutator - set owner to null is it is Unamed Owner
	public void setOwner( Property inOwner )
	{

		if( checkUnamedOwner() )
		{
			owner = null;
		}
		else 
		{
			owner = inOwner ;
		}
				
	}

	//Mutator - set profit
	public void setProfit( double inProfit )
	{
		profit = inProfit;
	}

	//Accessor - constant, used by children class
	public double getFivePercentConst()
	{
		return FIVE_PERCENT;
	}

	//Accessor -  get name
	public String getName()
	{
		return name;
	}

	//Accessor - get monetary value
	public double getMonetaryValue() 
	{
		return monetaryValue;
	}

	//Accessor - get owner
	public Property getOwner()
	{
		return owner;
	}

	//Accessor - get profit
	public double getProfit()
	{
		return profit;
	}

	//PURPOSE:	Check owner is Unamed Owner
	//IMPORT:	None
	//EXPORT:	Boolean isUanmed
	public boolean checkUnamedOwner()
	{
		boolean isUnamed = false;

		if( name.equals( UNAMED_OWNER ) )
		{
			isUnamed = true;
		}

		return isUnamed;
	}

	//PURPOSE:	Returns details of a property
	//IMPORT:	None
	//EXPORT:	Property's details in String
	public String toString()
	{
	 	return new String( " Name: " + getName() + "		Profit Earned over previous year: $" + getProfit() );
	}

	//PURPOSE:	Abstract method calculate profit
	//IMPORT:	None
	//EXPORT:	None
	public abstract void calcProfit();

	//PURPOSE:	Abstract method get bank account - throws InvalidOperationException
	//IMPORT:	None
	//EXPORT:	Bank account
	public abstract BankAccount getBankAccount() throws InvalidOperationException;






}