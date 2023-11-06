package com.eyecon.back.entity;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="result")
public class Result {

	@Id
	@Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="beforeimg")
	private String beforeimg; // 본래 이미지
	
	@Column(name="email")
	private String email; 
	
	@Column(name="hitmap")
	private String hitmap; // 히트맵
	
	@Column(name="placementimg")
	private String placementimg; // 상품배치 이미지
	
	@Column(name = "resultname")
	private String resultname;
	
	@Column(name = "route" )
	private String route;
	
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="time")
	private Time time;
	
	@Column(name="storeName")
	private String storeName;
	
	
	
	
    @PrePersist
    public void prePersist() {
        this.date = new Date();
        this.time = new Time(System.currentTimeMillis());
    }

    @PreUpdate
    public void preUpdate() {
        this.date = new Date();
        this.time = new Time(System.currentTimeMillis());
    }
	
	
	
}
