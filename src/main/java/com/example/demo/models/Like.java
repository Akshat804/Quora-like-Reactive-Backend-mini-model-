package com.example.demo.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.PrivateKey;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Likes")
public class Like {
    @Id
    private String id;
    private String targetId;
    private String targetType;

    private boolean islike;

    @CreatedDate
    private LocalDateTime createddate;


}
