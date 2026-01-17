package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "questions")
public class Question {
    @Id
    private String id;
    @NotBlank(message="title is required")
    @Size(min = 10, max = 100, message = "title must be between 10 and 100 characters")
    private String title;

    @NotBlank(message="content is required")
    @Size(min = 10, max = 100, message = "content must be between 10 and 100000 characters")
    private String content;

    private Integer views=null;

    @CreatedDate
    private LocalDateTime createdAt;
   @LastModifiedDate
    private LocalDateTime updatedAt;


}
