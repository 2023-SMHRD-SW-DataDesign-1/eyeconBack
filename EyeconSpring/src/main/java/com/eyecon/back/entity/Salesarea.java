package com.eyecon.back.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="salesarea")
public class Salesarea {

	@Id
	@Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="place")	
	private String place;
	
	@Column(name="age")	
	private String age;
	
	@Column(name="sex")	
	private String sex;
	
	@Column(name="storecnt")	
	private String storecnt;
	
	@Column(name="income")	
	private String income;
	
	@Column(name="population")
	private String population;
	
	@Column(name="maxday")
	private String maxday;
	
	@Column(name="category")
	private String category;
	
	
}
