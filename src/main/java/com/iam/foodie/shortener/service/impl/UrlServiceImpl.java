package com.iam.foodie.shortener.service.impl;

import com.iam.foodie.shortener.dao.model.UrlStore;
import com.iam.foodie.shortener.dao.repositories.URLRepositoryDao;
import com.iam.foodie.shortener.dao.vo.URLRequestVO;
import com.iam.foodie.shortener.exception.GlobalException;
import com.iam.foodie.shortener.service.UrlService;
import com.iam.foodie.shortener.service.util.URLConverterService;
import com.iam.foodie.shortener.util.ShortenerProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * @author Sathish Vasu
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlServiceImpl implements UrlService {

    private final URLRepositoryDao urlRepositoryDao;

    private final URLConverterService urlConverterService;

    private final ShortenerProperties shortenerProperties;


    /**
     * @param userId
     * @return
     */
    @Override
    public Flux<URLRequestVO> getAllByUserId(String userId) {
        return urlRepositoryDao.findByUserId(userId)
                .flatMap(urlStore -> {
                    URLRequestVO urlRequestVO =new URLRequestVO();
                    urlRequestVO.setId(urlStore.getId());
                    urlRequestVO.setUserId(userId);
                    urlRequestVO.setTinyUrl(urlStore.getTinyUrl());
                    urlRequestVO.setUrl(urlStore.getUrl());
                    urlRequestVO.setExpiresDate(urlStore.getExpiresDate());
                    return Mono.just(urlRequestVO);
                 });
    }

    /**
     * @param urlRequestVO
     * @return
     */
    @Override
    public Mono<URLRequestVO> createURL(URLRequestVO urlRequestVO) {
        final UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        log.debug("Printing URL {}",urlRequestVO.getUrl());
        if (!urlValidator.isValid(urlRequestVO.getUrl())) {
            // Invalid url return HTTP 400 bad request.
            throw new GlobalException(HttpStatus.BAD_REQUEST, shortenerProperties.getInvalidURL());
        }
        return urlRepositoryDao.findByUrl(urlRequestVO.getUrl())
                .defaultIfEmpty(mapperObj(urlRequestVO))
                .flatMap(urlStore -> {
                    if(urlStore.getId()==null){
                      return urlRepositoryDao.save(urlStore);
                    }
                    return Mono.just(urlStore);
                })
                .flatMap(urlStore -> {
                    urlRequestVO.setId(urlStore.getId());
                    urlRequestVO.setTinyUrl(urlStore.getTinyUrl());
                    urlRequestVO.setExpiresDate(urlStore.getExpiresDate());
                    return Mono.just(urlRequestVO);
                });
    }

    @Override
    public Mono<String> getAndRedirect(String shortUrl) {
       // var id = conversion.decode(shortUrl);

        log.info("Printing shortUrl......{}",shortUrl);
            return urlRepositoryDao.findByTinyUrl(shortUrl)
                .switchIfEmpty(Mono.error(new GlobalException(HttpStatus.NOT_FOUND,shortenerProperties.getNoDataFound() + shortUrl)))
                .flatMap(entity -> {
                    if (entity.getExpiresDate() != null &&
                            entity.getExpiresDate().isBefore(LocalDateTime.now())){
                        urlRepositoryDao.delete(entity);
                        throw new GlobalException(HttpStatus.INTERNAL_SERVER_ERROR, shortenerProperties.getLinkExpired());
                    }
                    return Mono.just(entity.getUrl());
                });
    }


    private UrlStore mapperObj(URLRequestVO urlRequestVO) {
        UrlStore urlStore = new UrlStore();
        urlStore.setUrl(urlRequestVO.getUrl());
        urlStore.setTinyUrl(urlConverterService.shortURLtoID(urlRequestVO.getUrl()));
        urlStore.setUserId(urlRequestVO.getUserId());
        urlStore.setExpiresDate(LocalDateTime.now().plusDays(Long.valueOf(shortenerProperties.getExpiresDate())));
        return urlStore;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Mono<Boolean> deleteURL(String id) {
        return urlRepositoryDao.findById(id)
                .switchIfEmpty(Mono.error(new GlobalException(HttpStatus.NOT_FOUND, shortenerProperties.getNoDataFound() + id)))
                .flatMap(urlRepositoryDao::delete)
                .flatMap(status -> Mono.just(Boolean.TRUE));
    }
}
