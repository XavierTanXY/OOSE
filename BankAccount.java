//FILE:				BankAccount.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:			A container class that stores and manages BankAccount
//RELATIONSHIP:		None

public class BankAccount
{
	public static final double YEARLY_INTEREST = 0.05;
	private double balance;

	//Default Constructor - set balance to 0
	public BankAccount()
	{
		balance = 0.0;
	}

	//Copy Constructor 
	public BankAccount( BankAccount inBankAcc )
	{
		balance = inBankAcc.getBalance();
	}

	//Accessor - get balance
	public double getBalance()
	{
		return balance;
	}

	//PURPOSE:	Add balance to bank account
	//IMPORT:	Amount to be added
	//EXPORT:	None
	public void addBalance( double inAmount )
	{
		balance = balance + inAmount;
	}

	//PURPOSE:	Subtract balance from bank account
	//IMPORT:	Amount to be subtracted
	//EXPORT:	None
	public void subtractBalance( double inAmount )
	{
		if( inAmount < 0 )
		{
			addBalance( inAmount );
		}
		else
		{
			balance = balance - inAmount ;
		}
		
	}

	//PURPOSE:	Calculate interest and add or subtract to bank account
	//IMPORT:	None
	//EXPORT:	None
	public void calcInterest()
	{
		double amount;

		amount = calcInterestAmount();
		
		addBalance( amount );
	}

	//PURPOSE:	Calculate interest to be added or subtracted
	//IMPORT:	None
	//EXPORT:	Interest amount
	private double calcInterestAmount()
	{
		return balance * YEARLY_INTEREST;
	}

	//PURPOSE:	Returns bank account balance
	//IMPORT:	None
	//EXPORT:	Bank account balance in String
	public String toString()
	{
		return new String("		Current Bank Account Balance: $" + balance );
	}

}