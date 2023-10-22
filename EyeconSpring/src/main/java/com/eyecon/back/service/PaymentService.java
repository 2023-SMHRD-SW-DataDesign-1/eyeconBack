package com.eyecon.back.service;

import org.springframework.stereotype.Service;

import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Service
public class PaymentService {

	public void verifyIamportService(IamportResponse<Payment> irsp, com.eyecon.back.entity.Payment payment) {
		//if(irsp.getResponse().getAmount().intValue() != Integer.parseInt(payment.getPrice())) throw new verifylamportException(); 
		
		
	}

}