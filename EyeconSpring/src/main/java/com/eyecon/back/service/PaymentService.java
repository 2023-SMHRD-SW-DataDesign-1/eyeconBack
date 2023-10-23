package com.eyecon.back.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.eyecon.back.dto.UserDTO;
import com.eyecon.back.repository.UserRepository;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Service
public class PaymentService {


	
	public void verifyIamportService(IamportResponse<Payment> irsp, com.eyecon.back.entity.Payment payment) {
		//if(irsp.getResponse().getAmount().intValue() != Integer.parseInt(payment.getPrice())) throw new verifylamportException(); 
		
		
	}
	

	

}