package com.example.demo.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Builder
@Getter
@Setter
public class AnswerResponseDTO {

    private String id;
    private String content;
    private String questionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
