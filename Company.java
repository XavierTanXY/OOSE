//FILE:				Company.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:			A container class that stores and manages Company
//RELATIONSHIP:		Inherited from Property Class and Association relationship with BankAccount

public class Company extends Property
{
	private BankAccount bankAcc;
	private double totalProfit;

	//Alternate Constructor
	public Company( String inName, double inMonetaryValue, Company inOwner )
	{
		super( inName, inMonetaryValue, inOwner );
		bankAcc = new BankAccount();
		totalProfit = 0.0;
	}
	
	//Accessor - get bank account
	public BankAccount getBankAccount()
	{
		return bankAcc;
	}

	//Accessor - get bank account balance
	public double getBalance()
	{
		return bankAcc.getBalance();
	}

	//PURPOSE:	Add balance to comapny's bank account
	//IMPORT:	Amount to be added
	//EXPORT:	None
	public void addBalance( double amount )
	{
		bankAcc.addBalance( amount );
	}

	//PURPOSE:	Subtract balance from comapny's bank account
	//IMPORT:	Amount to be subtracted
	//EXPORT:	None
	public void subtractBalance( double amount )
	{
		bankAcc.subtractBalance( amount );
	}

	//PURPOSE:	Returns details of a company
	//IMPORT:	None
	//EXPORT:	Company's details in String
	public String toString()
	{
		return new String( "Company" + super.toString() + bankAcc.toString() );
	}

	/*PURPOSE:	Calculate the total profit of a company from whatever it owns and 
				add balance or subtract from it's bank account 					*/
	//IMPORT:	None
	//EXPORT:	None
	public void calcProfit()
	{
		double newProfit;
		double profit = super.getProfit();


		if( profit <= 0.0 ) //If profit <= 0.0, set the profit to 0 for that paricular year
		{
			bankAcc.subtractBalance( profit );
			super.setProfit(0.0);
			
		} 
		else 
		{
			newProfit = profit * this.getFivePercentConst(); //Get new profit by multiplying 0.5
			totalProfit = totalProfit + newProfit; //Sum total profit for a year
			super.setProfit(totalProfit);
			bankAcc.addBalance( newProfit ); //Add new profit to bank
		}

		
	}

	//PURPOSE:	Calculate the interest of a company
	//IMPORT:	None
	//EXPORT:	None
	public void calcInterest()
	{
		bankAcc.calcInterest();
	}

	//PURPOSE:	Reset total profit to profit for that paricular year, and reset when end of year
	//IMPORT:	None
	//EXPORT:	None
	public void resetProfit()
	{
		super.setProfit(totalProfit);
		totalProfit = 0.0;
	}



	
}