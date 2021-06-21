package com.iam.foodie.shortener.service.util;

import com.iam.foodie.shortener.dao.model.UrlStore;
import com.iam.foodie.shortener.dao.vo.URLRequestVO;

import java.time.LocalDateTime;

public class URLRequestCreator {

    public static UrlStore createURLRequestToBeSaved() {
        return UrlStore.builder()
                .userId("60b0fbe0fba0fc065aef3864")
                .url("https://google.com/api/v1/vendors/address?vendorId=60a291527ffead7557478c5f")
                .tinyUrl("https:google.comapiv1vendors/")
                //.user("Sathish Vasu")
                .version(0L)
              //  .expiresDate(LocalDateTime.now())
                .build();
    }

    public static UrlStore returnSavedURL() {
        return UrlStore.builder()
                .id("60cdf4afd504c553242c8d6e")
                .userId("60b0fbe0fba0fc065aef3864")
                .url("https://google.com/api/v1/vendors/address?vendorId=60a291527ffead7557478c5f")
                .tinyUrl("https:google.comapiv1vendors/")
              //  .expiresDate(LocalDateTime.now())
                .build();
    }


    public static URLRequestVO createValidUrlRequestVO() {
        return URLRequestVO.builder()
                .id("60cdf4afd504c553242c8d6e")
                .userId("60b0fbe0fba0fc065aef3864")
                .url("https://google.com/api/v1/vendors/address?vendorId=60a291527ffead7557478c5f")
                .tinyUrl("https:google.comapiv1vendors/")
              //  .expiresDate(LocalDateTime.now())
                .build();
    }


    /*public static URLRequestVO createValidURLRequest() {
        return URLRequestVO.builder()

                .build();
    }*/


}
