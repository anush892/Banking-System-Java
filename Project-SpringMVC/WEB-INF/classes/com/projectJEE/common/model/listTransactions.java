package com.projectJEE.common.model;

public class listTransactions {
	
	String numb;
	String strDate;
	String strTrsrc;
	String strTsrcAmount;
	String strAvAmount; 
	
	public listTransactions (){
		
	}
	public listTransactions(String numb, String strDate, String strTrsrc, String strTsrcAmount, String strAvAmount) {
		super();
		this.numb = numb;
		this.strDate = strDate;
		this.strTrsrc = strTrsrc;
		this.strTsrcAmount = strTsrcAmount;
		this.strAvAmount = strAvAmount;
	}

	public String getNumb() {
		return numb;
	}

	public void setNumb(String numb) {
		this.numb = numb;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public String getStrTrsrc() {
		return strTrsrc;
	}

	public void setStrTrsrc(String strTrsrc) {
		this.strTrsrc = strTrsrc;
	}

	public String getStrTsrcAmount() {
		return strTsrcAmount;
	}

	public void setStrTsrcAmount(String strTsrcAmount) {
		this.strTsrcAmount = strTsrcAmount;
	}

	public String getStrAvAmount() {
		return strAvAmount;
	}

	public void setStrAvAmount(String strAvAmount) {
		this.strAvAmount = strAvAmount;
	}

	@Override
	public String toString() {
		return "listTransactions [numb=" + numb + ", strDate=" + strDate + ", strTrsrc=" + strTrsrc + ", strTsrcAmount="
				+ strTsrcAmount + ", strAvAmount=" + strAvAmount + "]";
	}
}