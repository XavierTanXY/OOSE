//FILE:				Business.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:			A container class that stores and manages Business
//RELATIONSHIP:		Inherited from Property Class

public class Business extends Property
{
	private double revenue;
	private double wages;

	//Alternate Constructor
	public Business( String inName, double inMonetaryValue, double inRevenue, double inWages, Company inOwner )
	{
		super( inName, inMonetaryValue, inOwner );
		setRevenue( inRevenue );
		setWages( inWages );
	}

	//Mutator - set revenue
	public void setRevenue( double inRevenue )
	{
		if( inRevenue < 0.0 )
		{
			throw new IllegalArgumentException( "Revenue is invalid" );
		}
		else
		{
			revenue = inRevenue;
		}
		
	}

	//Mutator - set wages
	public void setWages( double inWages )
	{
		if( inWages < 0.0 )
		{
			throw new IllegalArgumentException( "Wages is invalid" );
		}
		else
		{
			wages = inWages;
		}
	}

	//Accessor - get wages
	public double getWages()
	{
		return wages;
	}

	//Accessor - get revenue
	public double getRevenue()
	{
		return revenue;
	}

	//PURPOSE:	Get bank account from Property abstract method - throws InvalidOperationException 
	//IMPORT:	None
	//EXPORT:	Bank account
	public BankAccount getBankAccount() throws InvalidOperationException
	{
		throw new InvalidOperationException( "Business does not have a bank account" );
	}

	//PURPOSE:	Returns details of a business
	//IMPORT:	None
	//EXPORT:	Details of business in String
	public String toString()
	{
		return new String( "Business" + super.toString() + "	Revenue: " + getRevenue() + "	Wages: " + getWages() );
	}

	//PURPOSE:	Calculate the profit of a business using the formula: REVENUE - WAGES, and set as profit
	//IMPORT:	None
	//EXPORT:	None
	public void calcProfit()
	{
		super.setProfit( revenue - wages );
	}




}