package com.eyecon.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
	private String email;
	private String pw;
	
	public String email() {
		
		return this.email;
	}

	public String pw() {
		// TODO Auto-generated method stub
		return this.pw;
	}
}
