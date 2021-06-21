package com.iam.foodie.shortener.util;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author satvasu
 */
@Component
public class ShortenerAuditor implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Sathish Vasu");
    }

}
