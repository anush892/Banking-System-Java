package com.projectJEE.common.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

public class Transaction {
	
	private final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@NotEmpty
	@Pattern(regexp=EMAIL_PATTERN)
	private String accountNumber;  // email_ID
	@NotEmpty
	@DecimalMax("5000.00")
	@DecimalMin("50.00")
	private String amount;
	
	public Transaction() {
		
	}
	
	public Transaction(String accountNumber, String amount) {
		this.accountNumber = accountNumber;
		this.amount = amount;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [accountNumber=" + accountNumber + ", amount=" + amount + "]";
	}		
}