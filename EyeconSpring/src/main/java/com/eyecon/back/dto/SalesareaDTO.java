package com.eyecon.back.dto;





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
	
	
    public static SalesareaDTO toSalesareaDTO(Salesarea salesarea, Store store) {
    	SalesareaDTO salesareaDTO = new SalesareaDTO();
    	
    	salesareaDTO.setId(salesarea.getId());
//    	salesareaDTO.setPlace(store.getPlace1()+store.getPlace2());
    	salesareaDTO.setPlace(salesarea.getPlace());
    	salesareaDTO.setAge(salesarea.getAge());
    	salesareaDTO.setSex(salesarea.getSex());

        return salesareaDTO;
    }
	
	
	
}
