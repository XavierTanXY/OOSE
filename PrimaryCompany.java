 //FILE:             PrimaryCompany.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:          A class is responsible for Primary Company and its behaviour
//RELATIONSHIP:		Association Primary Property

import java.io.*;
import java.util.*;

public class PrimaryCompany
{
	public static final char SELL = 'S';
	public static final char BUY = 'B';

	private BankAccount bankAcc;
	private double profit;
	private String name;
	private double monetaryValue;
	private Property primaryProperty;

	//Alternate Constructor
	public PrimaryCompany( Property inProperty ) throws InvalidOperationException
	{
		bankAcc = inProperty .getBankAccount();
		profit = inProperty.getProfit();
		name = inProperty.getName();
		monetaryValue = inProperty.getMonetaryValue();
		primaryProperty = inProperty;
	}

	//PURPOSE:	Decides either buying or selling operation to take place
	//IMPORT:	Plan that happens, List of properties
	//EXPORT:	None
	public void buySellTransaction( Plan inPlan, List<Property> inProperties ) throws InvalidOperationException
	{
		if( inPlan.getTransactionType() == SELL )
		{
			sellProperty( inPlan, inProperties );
		}
		else if( inPlan.getTransactionType() == BUY )
		{
			buyProperty( inPlan, inProperties );
		}
	}

	//PURPOSE:	Buying operation
	//IMPORT:	Plan that happens, List of properties
	//EXPORT:	None
	private void buyProperty( Plan inPlan, List<Property> inProperties ) throws InvalidOperationException
	{
		double cost;
		int check = 0;

		if( checkAlreadyOwned( inPlan, inProperties ) ) //Check if the primary company has already owned the property being bought
		{
			throw new IllegalArgumentException("The primary company can't buy a property that it has already owned");
		}
		else
		{

			if( ( inPlan.getPropertyName() ).equals( name ) ) //Check if the primary company is buying itself
			{
				throw new InvalidOperationException( "Primary Company can't buy itself" );
			}
			else
			{
				for( Property p : inProperties )
				{
					if( ( inPlan.getPropertyName() ).equals( p.getName() ) )
					{
						
						cost = p.getMonetaryValue();
						check++;
						
						bankAcc.subtractBalance( cost ); //Subtract primary company balance
				
						if( p.getOwner() instanceof Company ) //If the previous of the property is Company, add amount to his bank account
						{
							( p.getOwner().getBankAccount() ).addBalance( cost );
						}

						p.setOwner( getPrimaryProperty() );
							
					}
				}
			}

			checkValidTransaction( check, BUY ); //Transaction is incomplete when check is not incremented
		}

	}

	//PURPOSE:	Selling operation
	//IMPORT:	Plan that happens, List of properties
	//EXPORT:	None
	private void sellProperty( Plan inPlan, List<Property> inProperties ) throws InvalidOperationException
	{
		int check = 0;
		
		if( ( inPlan.getPropertyName() ).equals( name ) ) //Check if the primary company is selling itself
		{
			throw new InvalidOperationException( "Primary Company can't sell itself" );
		}
		else if( checkHasProperties( inPlan, inProperties ) ) //Check if the primary company has the property that is being sold
		{
			throw new IllegalArgumentException("The primary company can't sell a property that it does not have");
		}
		else
		{

			for( Property p : inProperties )
			{
				if( ( inPlan.getPropertyName() ).equals( p.getName() ) )
				{
					p.setOwner( new Company("Unamed Owner", 0.0, null) ); //Sells to unamed owner
					bankAcc.addBalance( p.getMonetaryValue() );
					check++;
				}
			}
		}

		checkValidTransaction( check, SELL ); //Transaction is incomplete when check is not incremented
	}

	//PURPOSE:	Check whether the primary company has already owned a property that it intented to buy
	//IMPORT:	Plan that happens, List of properties
	//EXPORT:	Boolean alreadyOwned
	private boolean checkAlreadyOwned( Plan inPlan, List<Property> inProperties )
	{
		boolean alreadyOwned = false;
		String name;

		for( Property p : inProperties )
		{
			if ( ( inPlan.getPropertyName() ).equals( p.getName() ) ) 
			{
				if( ( p.getOwner().getName() ).equals( getName() ) )
				{
					alreadyOwned = true;
				}
			}
		}	
		
		return alreadyOwned;
	}

	//PURPOSE:	Check whether the primary company has a property that it wants to sell
	//IMPORT:	Plan that happens, List of properties
	//EXPORT:	Boolean hasProperties
	private boolean checkHasProperties( Plan inPlan, List<Property> inProperties )
	{
		boolean hasProperties = false;
		String name;

		for( Property p : inProperties )
		{
		
			if ( ( inPlan.getPropertyName() ).equals( p.getName() ) ) 
			{
				if( !( ( p.getOwner().getName() ).equals( getName() ) ) )
				{
					hasProperties = true;
				}
			}
		}	
		
		return hasProperties;
	}

	//PURPOSE:	Check whether a valid transaction using check
	//IMPORT:	Check that supposed to be incremented, Type of transaction happens
	//EXPORT:	Boolean alreadyOwned
	private void checkValidTransaction( int check, char type ) throws InvalidOperationException
	{
		if( check == 0 )
		{
			if( type == BUY )
			{
				throw new InvalidOperationException( "Transaction not completed due to Property being bought is not exist" );
			}
			else if( type == SELL )
			{
				throw new InvalidOperationException( "Transaction not completed due to Property being sold is not exist" );
			}
		}
	}

	//Accessor - get name
	public String getName()
	{
		return primaryProperty.getName();
	}

	//Accessor -  get primary property
	public Property getPrimaryProperty()
	{
		return primaryProperty;
	}

	//Accessor - get bank account
	public BankAccount getBankAcc() throws InvalidOperationException
	{
		return primaryProperty.getBankAccount();
	}

	//Accessor - get bank balance
	public double getBalance() 
	{
		return bankAcc.getBalance();
	}

	//Accessor - get profit
	public double getProfit()
	{
		return primaryProperty.getProfit();
	}
	
	//PURPOSE:	Returns details of primary company
	//IMPORT:	None
	//EXPORT:	Primary Company's details in String
	public String toString() 
	{
		return new String("PRIMARY COMPANY - " + name + " | Profit: " + getProfit() + " | Balance: " + getBalance() );
	}
}