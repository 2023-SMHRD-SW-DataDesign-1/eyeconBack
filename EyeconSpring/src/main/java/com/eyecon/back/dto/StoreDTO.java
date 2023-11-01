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
	private String storeName;
	private String category; //편의점인지 마트인지
	private String place1;
	private String place2;
	private String dong;
	
	
    public static StoreDTO toStoreDTO(Store store, User user) {
    	StoreDTO storeDTO = new StoreDTO();
    	
    	storeDTO.setId(store.getId());
    	storeDTO.setEmail(user.getEmail());
    	storeDTO.setStoreName(store.getStoreName());
    	storeDTO.setCategory(store.getCategory());
    	storeDTO.setPlace1(store.getPlace1());
    	storeDTO.setPlace2(store.getPlace2());
    	storeDTO.setDong(store.getDong());
        return storeDTO;
    }

	
}
