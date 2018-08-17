package com.indream.noteservice.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReminderDto implements Serializable {
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy hh:mm");
	private String stringDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {

		try {
			return SIMPLE_DATE_FORMAT.parse(this.stringDate);
		} catch (ParseException e) {
			return null;
		}
	}

	public String getStringDate() {
		return stringDate;
	}

	public void setStringDate(String stringDate) {
		this.stringDate = stringDate;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	
	private Date date;

}
