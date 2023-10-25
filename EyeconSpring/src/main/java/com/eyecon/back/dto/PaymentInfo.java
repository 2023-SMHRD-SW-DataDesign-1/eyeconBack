package com.eyecon.back.dto;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfo {
	
	private String impUid;
	private int price;
	private String coinName;
	private String number;
	private String finance;
	private Date date;
	private Time time;

	 
}
