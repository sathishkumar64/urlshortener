package com.iam.foodie.shortener.service.impl;

import com.iam.foodie.shortener.dao.model.UrlStore;
import com.iam.foodie.shortener.dao.repositories.URLRepositoryDao;
import com.iam.foodie.shortener.dao.vo.URLRequestVO;
import com.iam.foodie.shortener.exception.GlobalException;
import com.iam.foodie.shortener.service.util.URLConverterService;
import com.iam.foodie.shortener.service.util.URLRequestCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
class UrlServiceImplTest {

    @InjectMocks
    private UrlServiceImpl urlService;

    @Mock
    private URLRepositoryDao urlRepositoryDao;

    @Mock
    private URLConverterService urlConverterService;

    private final URLRequestVO urlRequestVO = URLRequestCreator.createValidUrlRequestVO();

    private final UrlStore urlStore = URLRequestCreator.returnSavedURL();


    @BeforeEach
    public void setUp() {

       BDDMockito.when(urlRepositoryDao.findByUrl(urlRequestVO.getUrl())).thenReturn(Mono.just(urlStore));

        BDDMockito.when(urlConverterService.shortURLtoID(urlRequestVO.getUrl()))
                .thenReturn("https:google.comapiv1vendors".toString());

        BDDMockito.when(urlRepositoryDao.save(URLRequestCreator.createURLRequestToBeSaved()))
                .thenReturn(Mono.just(urlStore));

        BDDMockito.when(urlRepositoryDao.findByUserId("60b0fbe0fba0fc065aef3864"))
                .thenReturn(Flux.just(urlStore));

        BDDMockito.when(urlRepositoryDao.findById("60ce0ed296ea2924c006ccd5"))
                .thenReturn(Mono.just(urlStore));
    }


    @Test
    @DisplayName("Get All By User Id")
    void getAllByUserId() {
        StepVerifier.create(urlService.getAllByUserId("60b0fbe0fba0fc065aef3864"))
                .expectSubscription()
                .expectNextMatches(saved -> StringUtils.hasText(saved.getId()))
                .verifyComplete();
    }

    @Test
    @DisplayName("Create New URL")
    void createURL() {
        URLRequestVO urlRequestToBeSaved = URLRequestCreator.createValidUrlRequestVO();
        StepVerifier.create(urlService.createURL(urlRequestToBeSaved))
                .expectSubscription()
                .expectNextMatches(saved -> StringUtils.hasText(saved.getId()))
                .verifyComplete();
    }

    @Test
    @DisplayName("Delete URL By Id")
    @Disabled
    void deleteURL() {
        StepVerifier.create(urlService.deleteURL("60ce0ed296ea2924c006ccd5"))
                .expectSubscription()
                .expectNextMatches(deleted -> deleted.compareTo(true)==0)
                .verifyComplete();
    }
}