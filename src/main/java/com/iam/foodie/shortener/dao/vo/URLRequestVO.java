package com.iam.foodie.shortener.dao.vo;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class URLRequestVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    @NotEmpty(message ="{userId.empty}")
    private String userId;

    @NotEmpty(message ="{url.empty}")
    private String url;

    private String tinyUrl;

    private LocalDateTime expiresDate;

}
