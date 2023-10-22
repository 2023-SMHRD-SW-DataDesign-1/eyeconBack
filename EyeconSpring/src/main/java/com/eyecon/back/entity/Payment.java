package com.eyecon.back.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Payment {
	@Id
	private int id;
	private String email;
	private String price;
	private int coinId;
	private Date date;
	private Time time;
	private String category;
	private String number;
	private String finance;
}