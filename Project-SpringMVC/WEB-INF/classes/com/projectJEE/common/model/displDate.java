package com.projectJEE.common.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class displDate {
	
	private final String DATE_PATTERN ="[0-9]{4}-[0-1][0-9]-[0-3][0-9]";
    @NotNull
    @Pattern(regexp = DATE_PATTERN )
	private String dateFrom;
    @NotNull
    @Pattern(regexp =DATE_PATTERN)
	private String dateTo;
	
	public displDate(){ }

	public displDate(String dateFrom, String dateTo) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	@Override
	public String toString() {
		return "displDate [dateFrom=" + dateFrom + ", dateTo=" + dateTo + "]";
	}
}