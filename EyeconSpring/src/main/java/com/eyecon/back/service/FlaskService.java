package com.eyecon.back.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eyecon.back.dto.ResultDTO;
import com.eyecon.back.dto.SalesareaDTO;
import com.eyecon.back.dto.StoreDTO;
import com.eyecon.back.entity.Result;
import com.eyecon.back.entity.Salesarea;
import com.eyecon.back.entity.Store;
import com.eyecon.back.entity.User;
import com.eyecon.back.repository.ResultRepository;
import com.eyecon.back.repository.SalesareaRepository;
import com.eyecon.back.repository.StoreRepository;
import com.eyecon.back.repository.UserRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlaskService {

	private final SalesareaRepository salesareaRepository;
	private final StoreRepository storeRepository;
	private final ResultRepository resultRepository;
	
	@Transactional
	public String callData(StoreDTO storeDTO) {
		System.out.println("=== Flask Service ===");
		
		//1-1 이메일로 가게 장소 조회
		Optional<Store> optionStore  =storeRepository.findByEmail(storeDTO.getEmail());
		System.out.println("1-1 통과");
		
		//1-2 조회한 가게 장소 엔티티에 저장
		Store store= optionStore.get();
//		System.out.println("store 엔티티 : "+store);
		System.out.println("1-2 통과");
		
		//2-1 엔티티에 저장된 가게 장소 상권dto에 저장 
		SalesareaDTO salesareaDTO= new SalesareaDTO();
		salesareaDTO.setDoro(store.getPlace1());  // 여기서 store의 place냐 dong 이냐가 중요
		salesareaDTO.setDong(store.getDong());
		salesareaDTO.setCategory(store.getCategory());
		System.out.println("salesareaDTO 세일즈DTO"+salesareaDTO);
		System.out.println("2 통과");
		
		// 2-2 위치 정보 () 들어가면 거기부터 지워버리기
		String doro = salesareaDTO.getDoro();
		int index = doro.indexOf("(");
		if (index > 0) {
		    // 공백을 제외하고 '(' 앞의 문자열을 가져옵니다.
		    doro =doro.substring(0, index).trim();
		}
		System.out.println("도로명주소 : "+doro);
		
		String dong =salesareaDTO.getDong();
		index = dong.indexOf("동 ");
		if(index>0) {
			dong=dong.substring(0,index).trim();
		}
		index = dong.indexOf("구 ");
		if(index > 0) {
		    dong = dong.substring(index).trim();
		}
		System.out.println("동주소: " + dong);
		
		//3-1 상권dto에 저장된 가게장소로 상권데이터 조회
//		Optional<Salesarea> optionSales= salesareaRepository.findByPlace(salesareaDTO.getPlace());
		List<Salesarea> optionSales= salesareaRepository.findPlaceContainingDongAndDoro(doro,dong,salesareaDTO.getCategory());
		System.out.println("optionSales 옵셔널세일즈 : "+optionSales.get(0));
		System.out.println("3-1 통과");
		
		//3-2 조회한 상권데이터 상권 엔티티에 저장 -> 아래 4-2가 DTO만 돼서 엔티티에서 dto로 변경 => 에러떠서 그냥 엔티티로 다시 해봄 안되면 그때 고쳐보자!!!
		Salesarea salesarea = optionSales.get(0); //-> 조회된 값이 없을때 에러 떠서 일단 주석 ㄱ 아래 방법은 예외처리를 해준다는 데 두고 봐야될듯..
//		Salesarea salesarea = optionSales.orElseThrow(NoSuchElementException::new);
		System.out.println("상권 엔티티 : "+salesarea);
		
		//4-1 flask에 데이터를 json으로 보내기 위한 설정
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("4-1 통과");

		// 4-2
		// HttpEntity에 데이터와 헤더 설정
		HttpEntity<Salesarea> entity = new HttpEntity<>(salesarea, headers);
		System.out.println("4-2 통과");
		
		// 5 flask에 POST 요청 보내기
//		String flaskUrl = "http://localhost:5000/consult";
		String flaskUrl = "http://3.36.133.196:5000/consult";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(flaskUrl, entity, String.class);
		System.out.println("5 통과");
		
		String suc = "플라스크에 요청 시도했음";
		return suc;
	}

	
	public String sendImg(ResultDTO resultDTO) {
		System.out.println("-- flaskService.sendImg -- ");
		Result result = new Result();
		System.out.println("resultDTO : "+resultDTO);
		result.setEmail(resultDTO.getEmail());
		result.setBeforeimg(resultDTO.getBeforeimg());
		result.setStoreName(resultDTO.getStoreName());
		
		resultRepository.save(result); 
		System.out.println("result :"+result);
		
		//flask에 보내기
//		String content = resultDTO.getBeforeimg();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Result> entity = new HttpEntity<>(result, headers);

//		String flaskUrl = "http://localhost:5000/eye";
		String flaskUrl = "http://3.36.133.196:5000/eye";
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.postForObject(flaskUrl, entity, String.class);
		return response;
		
	}

	// 히트맵이 그려진 이미지 firebase URL을 저장하는 함수
	@Transactional
	public void saveAfterImage(ResultDTO resultDTO, String email) {
		
		Optional<Result> r = resultRepository.findFirstByEmailOrderByIdDesc(email);
		

		if (r.isPresent()) {
			Result resultInfo = r.get();
			resultInfo.setHitmap(resultDTO.getHitmap());
			resultRepository.save(resultInfo);
			
		}
		
	}

	//
	public List printImg(ResultDTO resultDTO) {
		System.out.println("resultService.printImg");
		Result result = new Result();
		
		result.setEmail(resultDTO.getEmail());
		
		List<Result> resultList = resultRepository.findByEmailOrderByIdDesc(result.getEmail());
		System.out.println("이미지출력 리스트[0] : "+ resultList.get(0));
		System.out.println("이미지출력 리스트[0] : "+ resultList.get(1));
		System.out.println("이미지출력 리스트[0] : "+ resultList.get(2));
		Result resultOne = resultList.get(0);

		
//		return resultOne; // 마지막 업로드 이미지들만 보냄
		return resultList;
	}
	
	
	
	
	
}
