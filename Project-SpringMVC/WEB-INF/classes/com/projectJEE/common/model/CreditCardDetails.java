package com.projectJEE.common.model;

public class CreditCardDetails {
	
	private String CreditCardNumber;
	private String CC_CVV;
	private String CC_Email;
	private String CC_validity;
	
	public CreditCardDetails() {
		
	}
	
	public CreditCardDetails(String creditCardNumber, String cC_CVV, String cC_Email, String cC_validity) {
		CreditCardNumber = creditCardNumber;
		CC_CVV = cC_CVV;
		CC_Email = cC_Email;
		CC_validity = cC_validity;
	}
	public String getCreditCardNumber() {
		return CreditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		CreditCardNumber = creditCardNumber;
	}
	public String getCC_CVV() {
		return CC_CVV;
	}
	public void setCC_CVV(String cC_CVV) {
		CC_CVV = cC_CVV;
	}
	public String getCC_Email() {
		return CC_Email;
	}
	public void setCC_Email(String cC_Email) {
		CC_Email = cC_Email;
	}
	public String getCC_validity() {
		return CC_validity;
	}
	public void setCC_validity(String cC_validity) {
		CC_validity = cC_validity;
	}
	@Override
	public String toString() {
		return "CreditCardDetails [CreditCardNumber=" + CreditCardNumber + ", CC_CVV=" + CC_CVV + ", CC_Email="
				+ CC_Email + ", CC_validity=" + CC_validity + "]";
	}
}