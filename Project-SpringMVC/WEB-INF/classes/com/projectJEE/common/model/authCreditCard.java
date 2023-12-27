package com.projectJEE.common.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

public class authCreditCard {
	
	// RegEx
	private final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private final String CVV_PATTERN = "^[0-9]{3,4}$";
	private final String VISA_CARD_PATTERN = "^(?:4[0-9]{12})(?:[0-9]{3})$";
	
	@NotEmpty
	@Pattern(regexp=EMAIL_PATTERN)
	private String emailID;  
	@NotEmpty
	@DecimalMax("5000.00")
	@DecimalMin("50.00")
	private String amount;
	
	@NotEmpty
	@Pattern(regexp=VISA_CARD_PATTERN)
	private String cardNumber;
	
	@NotEmpty
	@Pattern(regexp=CVV_PATTERN)
	private String cvv2Code; 
		
	public authCreditCard ( ) {
		
	}

	public authCreditCard(String emailID, String amount, String cardNumber, String cvv2Code) {
		super();
		this.emailID = emailID;
		this.amount = amount;
		this.cardNumber = cardNumber;
		this.cvv2Code = cvv2Code;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvv2Code() {
		return cvv2Code;
	}

	public void setCvv2Code(String cvv2Code) {
		this.cvv2Code = cvv2Code;
	}

	@Override
	public String toString() {
		return "authCreditCard [emailID=" + emailID + ", amount=" + amount + ", cardNumber=" + cardNumber
				+ ", cvv2Code=" + cvv2Code + "]";
	}
}