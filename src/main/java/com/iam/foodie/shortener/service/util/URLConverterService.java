package com.iam.foodie.shortener.service.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Sathish Vasu.
 */
@Service
@Slf4j
public class URLConverterService {


    /**
     * @param localURL
     * @return
     */
    public String shortURLtoID(String localURL){
        log.debug("Shortening {}", localURL);
        int id = 0; // initialize result
        // A simple base conversion logic
        for (int i = 0; i < localURL.length(); i++)
        {
            if ('a' <= localURL.charAt(i) &&
                    localURL.charAt(i) <= 'z')
                id = id * 62 + localURL.charAt(i) - 'a';
            if ('A' <= localURL.charAt(i) &&
                    localURL.charAt(i) <= 'Z')
                id = id * 62 + localURL.charAt(i) - 'A' + 26;
            if ('0' <= localURL.charAt(i) &&
                    localURL.charAt(i) <= '9')
                id = id * 62 + localURL.charAt(i) - '0' + 52;
        }
        return String.valueOf(id);
    }
}
