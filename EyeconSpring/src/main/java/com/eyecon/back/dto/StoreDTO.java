package com.eyecon.back.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

import com.eyecon.back.entity.Store;
import com.eyecon.back.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {

	private Long id;
    private String email;
	private String storename;
	private String category; //편의점인지 마트인지
	private String place1;
	private String place2;
	
	
    public static StoreDTO toStoreDTO(Store store) {
    	StoreDTO storeDTO = new StoreDTO();
    	
    	storeDTO.setId(store.getId());
    	storeDTO.setEmail(store.getEmail());
    	storeDTO.setStorename(store.getStorename());
    	storeDTO.setCategory(store.getCategory());
    	storeDTO.setPlace1(store.getPlace1());
    	storeDTO.setPlace2(store.getPlace2());
        return storeDTO;
    }

	
}
