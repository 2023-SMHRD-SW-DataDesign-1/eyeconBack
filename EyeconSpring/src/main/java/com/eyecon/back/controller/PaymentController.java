package com.eyecon.back.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyecon.back.dto.PaymentInfo;
import com.eyecon.back.dto.UserDTO;
import com.eyecon.back.method.TokenToEmail;
import com.eyecon.back.service.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","http://3.36.133.196:3000","https://3.36.133.196:3000","https://eyecon.site"})
@RequestMapping("/verify")
public class PaymentController {
	// portone key
	@Value("impKey")
	private String impKey;
	@Value("impSecretKey")
	private String impSecret;

	private final IamportClient iamportClient;
	
	private final PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		this.iamportClient = new IamportClient("5741800168864011", "Geqo9oplVUcmtoCti2u8P0OsHTlPaV7NglaaZJyvESM6w7cXlKlJwbLfg2LNFUCuhM76MV7pH49SjhCE");
		this.paymentService = paymentService;
		
	}

	@PostMapping("/{imp_uid}")
	public IamportResponse<Payment> paymentByImpUid(@PathVariable("imp_uid") String imp_uid)
			throws IamportResponseException, IOException {
		System.out.println(imp_uid);
		return iamportClient.paymentByImpUid(imp_uid);
	}

	@PostMapping("/completed")
	public IamportResponse<Payment> orderComplted(@RequestBody PaymentInfo paymentInfo, @CookieValue String accessToken)
			throws IamportResponseException, IOException {
		System.out.println("카드번호" + paymentInfo.getNumber());
		System.out.println("imp_uid : " + paymentInfo.getImpUid());
		System.out.println("access : "+ accessToken);
		// 아임포트 서버쪽에 결제된 정보 조회.
		// paymentByImpUid 는 아임포트에 제공해주는 api인 결제내역 조회(/payments/{imp_uid})의 역할을 함.
		IamportResponse<Payment> irsp = paymentByImpUid(paymentInfo.getImpUid());
		/*
		 * Cookie[] cookies = request.getCookies();
		 * 
		 * String token = Arrays.stream(cookies).filter(c ->
		 * c.getName().equals("accessToken")).findFirst()
		 * .map(Cookie::getValue).orElse(null);
		 */

		paymentService.verifyIamportService(irsp, paymentInfo, accessToken);

		return irsp;
	}
	
	// 결제 내역 페이지
	@GetMapping("/")
	public List<com.eyecon.back.entity.Payment> selectAllPayment(@CookieValue String accessToken) {
		
		TokenToEmail tokenToEmail = new TokenToEmail();
		String email = tokenToEmail.getEmailFromToken(accessToken);
		
		return paymentService.selectAllPayment(email);
		
		
	}

}
