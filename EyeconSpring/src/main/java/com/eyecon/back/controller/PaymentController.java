package com.eyecon.back.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eyecon.back.service.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;


@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class PaymentController {
	
	private final IamportClient iamportClient;
	
	private final PaymentService paymentService;
	
	public PaymentController() {
		this.iamportClient = new IamportClient("5741800168864011", "Geqo9oplVUcmtoCti2u8P0OsHTlPaV7NglaaZJyvESM6w7cXlKlJwbLfg2LNFUCuhM76MV7pH49SjhCE");
		this.paymentService = new PaymentService();
	}
	
	@PostMapping("/verify/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(@PathVariable("imp_uid") String imp_uid)
            throws IamportResponseException, IOException {
		System.out.println(imp_uid);
        return iamportClient.paymentByImpUid(imp_uid);
    }
	
	@PostMapping("/verify/completed")
	public IamportResponse<Payment> orderComplted(String impUid, @RequestBody com.eyecon.back.entity.Payment payment) throws IamportResponseException, IOException {
		System.out.println("카드번호" + payment.getNumber());
		System.out.println("imp_uid" + impUid);
		//아임포트 서버쪽에 결제된 정보 조회.
	    //paymentByImpUid 는 아임포트에 제공해주는 api인 결제내역 조회(/payments/{imp_uid})의 역할을 함. 
				IamportResponse<Payment> irsp = paymentByImpUid(impUid);
				paymentService.verifyIamportService(irsp, payment);
				
		return irsp;
	}

	
	
	
}
