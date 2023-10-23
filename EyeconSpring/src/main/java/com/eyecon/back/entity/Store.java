package com.eyecon.back.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.eyecon.back.dto.StoreDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Store {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column
//	@OneToOne
	private String email;

	@Column
	private String storename;
	
	@Column
	private String category; //편의점인지 마트인지

	@Column
	private String place1;
	
	@Column	
	private String place2;

	public static Store toStore(StoreDTO storeDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
