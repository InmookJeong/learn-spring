package kr.study.dev_mook.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.study.dev_mook.model.RestSampleVO;

/* Spring4부터 제공하는 Annotation */
/* REST 방식의 데이터 처리 가능 */
@RestController
@RequestMapping("/rest-sample")
public class SampleRestController {
	
	/*
	 * RestController는 JSP와 같은 View를 만들지 않는 대신 데이터 자체를 반환.
	 *   - String(text/html), JSON, XML 방식으로 반환
	 * 모든 메소드는 @ResponseBody가 없어도 동일하게 동작.(생략되어 있다고 생각하기)
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(SampleRestController.class);
	
	@RequestMapping("/hello")
	public String sayHello() {
		logger.info("##### Call sayHello");
		return "Hello World";
	}
	
	/* JSON 사용을 위해 pom.xml에 jackson-databind 라이브러리 추가하기 */
	@RequestMapping("/sendVO")
	public RestSampleVO sendVO() {
		RestSampleVO vo = new RestSampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(123);
		return vo;
	}
	
	@RequestMapping("/sendList")
	public List<RestSampleVO> sendList() {
		List<RestSampleVO> list = new ArrayList<RestSampleVO>();
		for(int i=0; i<5; i++) {
			RestSampleVO vo = new RestSampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(123);
			list.add(vo);
		}
		return list;
	}
	
	@RequestMapping("/sendMap")
	public HashMap<Integer, RestSampleVO> sendMap() {
		HashMap<Integer, RestSampleVO> map = new HashMap<Integer, RestSampleVO>();
		for(int i=0; i<5; i++) {
			RestSampleVO vo = new RestSampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			map.put(i, vo);
		}
		return map;
	}
	
	/*
	 * ResponseEntity
	 * - HTPP 상태 코드 제어 및 전달
	 */
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendErrorAuth() {
		// 400 Error
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<RestSampleVO>> sendErrorNot() {
		List<RestSampleVO> list = new ArrayList<RestSampleVO>();
		for(int i=0; i<5; i++) {
			RestSampleVO vo = new RestSampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			list.add(vo);
		}
		return new ResponseEntity<List<RestSampleVO>>(list, HttpStatus.NOT_FOUND);
	}
	
}
