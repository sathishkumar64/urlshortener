package com.iam.foodie.shortener.controller;


import com.iam.foodie.shortener.controller.api.TinyUrlAPI;
import com.iam.foodie.shortener.dao.vo.URLRequestVO;
import com.iam.foodie.shortener.exception.GlobalException;
import com.iam.foodie.shortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author satvasu
 *
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class TinyUrlController implements TinyUrlAPI {


	private final UrlService urlService;

	@Override
	public Mono<URLRequestVO> newURLRequestVO(URLRequestVO urlRequestVO) {
		log.info("Creating Shorten URL................");
		return urlService.createURL(urlRequestVO);
	}

	@Override
	public Flux<URLRequestVO> getAllByUserId(String userId) {
		log.debug("Get All Shorten URL.................");
		return urlService.getAllByUserId(userId);
	}

	@Override
	public Mono<Boolean> deleteById(String id) {
		log.debug("Deleting Shorten URL................");
		return urlService.deleteURL(id);
	}

	@Override
	public Mono<String> getAndRedirect(String shortUrl) {
		log.debug("Get And Redirect...............");
		return urlService.getAndRedirect(shortUrl);
	}
}
