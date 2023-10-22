package com.eyecon.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthVO {
	
	private String accessToken;
	private String refreshToken;
	
	public String accessToken() {
		return this.accessToken;
	}
	public String refreshToken() {
		return this.refreshToken;
	}
	
}
