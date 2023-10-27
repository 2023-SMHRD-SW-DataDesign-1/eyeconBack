package com.eyecon.back.dto;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.eyecon.back.entity.Result;
import com.eyecon.back.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {

	private Long id;
	
	private String beforeimg; // 본래 이미지
	
	private String email; 

	private String hitmap; // 히트맵
	
	private String placementimg; // 상품배치 이미지
	
	private String resultname;
	
	private String route;
	
	private Date date;
	
	private Time time;
	
	
	
	   public static ResultDTO toResultDTO(Result result) {
		   ResultDTO resultDTO = new ResultDTO();
	    	
		   resultDTO.setId(result.getId());
		   resultDTO.setBeforeimg(result.getBeforeimg());
		   resultDTO.setHitmap(result.getHitmap());
		   resultDTO.setPlacementimg(result.getPlacementimg());
		   resultDTO.setResultname(result.getResultname());
		   resultDTO.setRoute(result.getRoute());

	        return resultDTO;
	    }
	
	
	
}
