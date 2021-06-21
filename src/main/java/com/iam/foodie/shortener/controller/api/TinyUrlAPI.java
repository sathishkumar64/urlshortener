package com.iam.foodie.shortener.controller.api;


import com.iam.foodie.shortener.dao.vo.URLRequestVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author Sathish Vasu.
 */

@Tag(name = "Shorten Controller", description = "The Shorten API")
@Validated
public interface TinyUrlAPI {

	@Operation(description = "adds an shorten", operationId = "addShorten", summary = "Adding a new Shorten")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "item created"),
			@ApiResponse(responseCode = "400", description = "invalid input, object invalid"),
			@ApiResponse(responseCode = "409", description = "an existing item already exists") })
	@PostMapping(path = "shorten", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<URLRequestVO> newURLRequestVO(@Valid @RequestBody URLRequestVO urlRequestVO);


	@Operation(description = "get all shorten", operationId = "getAllShorten", summary = "Get All Shorten")
	@GetMapping(value = "shorten", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Flux<URLRequestVO> getAllByUserId(@RequestParam String userId);



	@Operation(description = "delete shorten", operationId = "deleteShorten", summary = "Deleting Existing Shorten")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "shorten/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Boolean> deleteById(@PathVariable("id") String id);


	@Operation(description = "get and redirect", operationId = "getAndRedirect", summary = "Get And Redirect")
	@GetMapping(value = "shorten/{shortenUrl}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Mono<String> getAndRedirect(@PathVariable("shortenUrl") String  shortenUrl);

}
