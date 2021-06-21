package com.iam.foodie.shortener.dao.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "urlStore")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TypeAlias("urlStore")
@Builder
@Data
public class UrlStore {

    @Id
    private String id;

    private String userId;

    private String tinyUrl;

    private String url;

    @Indexed(name = "expire_after_days_index",expireAfter = "2d")
    private LocalDateTime expiresDate;

    @JsonIgnore
    @CreatedBy
    private String user;

    @JsonIgnore
    @CreatedDate
    private Instant createdDate;

    @JsonIgnore
    @Version
    private Long version;
}
