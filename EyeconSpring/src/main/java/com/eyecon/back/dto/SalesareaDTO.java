package com.eyecon.back.dto;





import javax.persistence.Column;

import com.eyecon.back.entity.Salesarea;
import com.eyecon.back.entity.Store;
import com.eyecon.back.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesareaDTO {

	private Long id;
	private String place;
	private String age;
	private String sex;
	private String storecnt;
	private String income;
	private String population;
	private String maxday;
	private String category;
	private String doro;
	private String dong;
	
	
    public static SalesareaDTO toSalesareaDTO(Salesarea salesarea, Store store) {
    	SalesareaDTO salesareaDTO = new SalesareaDTO();
    	
    	salesareaDTO.setId(salesarea.getId());
//    	salesareaDTO.setPlace(store.getPlace1()+store.getPlace2());
    	salesareaDTO.setPlace(salesarea.getPlace());
    	salesareaDTO.setAge(salesarea.getAge());
    	salesareaDTO.setSex(salesarea.getSex());
    	salesareaDTO.setStorecnt(salesarea.getStorecnt());
    	salesareaDTO.setIncome(salesarea.getIncome());
    	salesareaDTO.setPopulation(salesarea.getPopulation());
    	salesareaDTO.setMaxday(salesarea.getMaxday());
    	salesareaDTO.setCategory(salesarea.getCategory());
    	salesareaDTO.setDong(store.getDong());
    	salesareaDTO.setDoro(store.getPlace1());
    	

        return salesareaDTO;
    }
	
	
	
}
