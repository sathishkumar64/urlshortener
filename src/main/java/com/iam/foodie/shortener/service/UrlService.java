package com.iam.foodie.shortener.service;

import com.iam.foodie.shortener.dao.vo.URLRequestVO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Sathish Vasu.
 */
public interface UrlService {

    Flux<URLRequestVO> getAllByUserId(String userId);

    Mono<URLRequestVO> createURL(URLRequestVO urlRequestVO);

    Mono<String> getAndRedirect(String shortUrl);

    Mono<Boolean> deleteURL(String id);
}
