package com.iam.foodie.shortener.dao.repositories;

import com.iam.foodie.shortener.dao.model.UrlStore;
import com.iam.foodie.shortener.dao.vo.URLRequestVO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Sathish Vasu.
 */
@Repository
public interface URLRepositoryDao extends ReactiveMongoRepository<UrlStore, String> {

    Mono<UrlStore> findByUrl(String url);

    Mono<UrlStore> findByTinyUrl(String tinyUrl);

    Flux<UrlStore> findByUserId(String userId);
}
