package com.eyecon.back.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name="store")
public class Store {

	@Id
	@Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name="email", referencedColumnName = "email")
	private User user;

//	@Column(name="email")
//	private String email;

	@Column(name="storeName")
	private String storeName;
	
	@Column(name="category")
	private String category; //편의점인지 마트인지

	@Column(name=" place1")
	private String place1;	
	
	@Column(name=" place2")
	private String place2;

	
	@Column(name = "dong")
	private String dong;
	
	public static Store toStore(StoreDTO storeDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
