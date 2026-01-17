package com.example.demo.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "answers")
public class Answer {
    @Id
    private String id;

    @NotBlank(message="content is required")
    @Size(min = 10, max = 100, message = "content must be between 10 and 100000 characters")
    private String content;

    @Indexed
    private String questionId;


    @CreatedDate
    private LocalDateTime createdAt=LocalDateTime.now();
    @LastModifiedDate
    private LocalDateTime updatedAt=LocalDateTime.now();



}
