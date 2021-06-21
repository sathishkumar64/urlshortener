package com.iam.foodie.shortener;

import com.iam.foodie.shortener.util.ShortenerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.EnableMongoAuditing;


/**
 * @author Sathish Vasu.
 */
@EnableMongoAuditing
@SpringBootApplication(scanBasePackages = "com.iam.foodie.shortener.*")
@EnableConfigurationProperties({ ShortenerProperties.class})
public class URLShortenerServiceApplication {

    public static void main(String[] args) {
        //   ReactorDebugAgent.init();
        SpringApplication.run(URLShortenerServiceApplication.class, args);
    }

}
