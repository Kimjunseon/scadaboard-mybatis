package com.scada.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scada.Dto.ReviewDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/movie/review")
public class ReviewController {
	
	// 영화에 대한 리뷰 CRUD 하는 컨트롤러
	// 리뷰 생성
	// @PostMapping("/addReview")
	// public ResponseEntity addReview(@Valid @RequestBody ReviewDto reviewDto) {
		// 리뷰테이블에 필요한 데이터 생각해보기
		
	//}
	
	
}
