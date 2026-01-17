package com.example.demo.adapter;

import com.example.demo.DTO.QuestionResponseDTO;
import com.example.demo.models.Question;
import lombok.*;
@Builder
public class QuestionAdapter {
    public static QuestionResponseDTO toQuestionResponseDTO(Question question) {

        return QuestionResponseDTO.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .build();
    }
}
